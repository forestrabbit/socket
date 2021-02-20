import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(6000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println(socket.getInetAddress());
                new Thread(new SocketHandle(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
