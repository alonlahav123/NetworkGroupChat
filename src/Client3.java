public class Client3 {

    public static void main(String[] args) {
        Client c = new Client();
        c.start();

        try {
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
