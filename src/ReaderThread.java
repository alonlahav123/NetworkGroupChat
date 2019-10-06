import java.io.DataInputStream;
import java.io.IOException;

public class ReaderThread extends Thread {

    private DataInputStream in;

    public ReaderThread(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        while(true) {
            try {
                String msg = in.readUTF();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
