import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class ServerThread extends Thread {

    private Socket s;
    private DataInputStream in;
    private DataOutputStream out;
    private ArrayList<Socket> socketList;
    private boolean run;

    public ServerThread(Socket s, ArrayList<Socket> socketList) {
        this.socketList = socketList;
        this.s = s;
        this.run = true;

        try {
            in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            out = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (run) {
            if(s.isClosed()) {
                run = false;
            } else try {
                String msg = in.readUTF();
                System.out.println(msg);
                for(int i = 0; i < socketList.size(); i++) {
                    Socket soc = socketList.get(i);

                    if(!soc.equals(s)) {
                        DataOutputStream out2 = new DataOutputStream(soc.getOutputStream());
                        out2.writeUTF(msg);
                    }
                }
            } catch (SocketException e) {
                run = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
