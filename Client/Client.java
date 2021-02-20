import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public Client() {
        try (Socket socket = new Socket("127.0.0.1", 6000)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                    handle(reader, writer);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle(BufferedReader reader, BufferedWriter writer) throws IOException {
        writer.write("forest_rabbit\n");
        writer.flush();
        String data = reader.readLine();
        if (data.equals("ok")) {
            System.out.println("Identify successfully");
            try (Scanner scanner = new Scanner(System.in)) {
                while (true) {
                    data = scanner.nextLine();
                    writer.write(data + "\n");
                    writer.flush();
                    if (data.equals("exit")) {
                        break;
                    }
                }
            }
        } else {
            System.out.println("Identify failed");
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}