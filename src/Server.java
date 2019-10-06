import javax.xml.crypto.Data;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) {

        try {
            ArrayList<Socket> socketList = new ArrayList<>();
            ArrayList<ServerThread> serverThreads = new ArrayList<>();
            ServerSocket serverSocket = new ServerSocket(5000);
            Scanner sc = new Scanner(System.in);
            boolean run = true;
            int size = 2;


            while (run) {
                Socket s = serverSocket.accept();
                System.out.println("New client connected");
                socketList.add(s);

                ServerThread st = new ServerThread(s, socketList);
                st.start();
                serverThreads.add(st);

                Thread.sleep(1000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
