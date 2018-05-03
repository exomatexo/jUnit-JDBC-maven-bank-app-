import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ConsoleApp app = new ConsoleApp();
        try {
            app.menu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}