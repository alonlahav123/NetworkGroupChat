import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

    private String name;

    public Client() {
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost", 5000);
            Scanner sc = new Scanner(System.in);
            boolean run = true;

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            ReaderThread reader = new ReaderThread(in);
            reader.start();

            System.out.println("Input your name...");
            name = sc.nextLine();

            System.out.println(name + " has connected");
            out.writeUTF(name + " has connected");

            while(run) {
                String text = sc.nextLine();
                out.writeUTF(this.name + ": " + text);

                Thread.sleep((int)(Math.random()*1000) + 1000);
            }

            out.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
