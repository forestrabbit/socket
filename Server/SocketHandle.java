import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketHandle implements Runnable {

    private Socket socket;

    public SocketHandle(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                handle(reader, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handle(BufferedReader reader, BufferedWriter writer) throws IOException {
        String data = reader.readLine();
        if (data.equals("forest_rabbit")) {
            writer.write("ok\n");
            writer.flush();
            while (true) {
                data = reader.readLine();
                if (data.equals("exit")) {
                    break;
                }
                System.out.println(data);
            }
        } else {
            writer.write("failed\n");
            writer.flush();
        }
    }
    
}
