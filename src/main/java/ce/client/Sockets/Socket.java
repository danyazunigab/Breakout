package ce.client.Sockets;

import java.io.IOException;
import java.net.*;

public class Socket {
    public InetAddress ip = InetAddress.getByName("localhost");
    private DatagramSocket Socket;
    private Integer port;

    public Socket(Integer port) throws UnknownHostException, SocketException {
        this.port = port;
        this.Socket = new DatagramSocket(port, this.ip);

    }

    public Integer send(String toSend) {
        DatagramPacket data = new DatagramPacket(toSend.getBytes(), toSend.length(), this.ip, 8080);
        try {
            this.Socket.send(data);
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    private String recieveSocket() throws IOException, SocketException {
        DatagramPacket data = new DatagramPacket(new byte[500], 500);
        this.Socket.receive(data);

        return new String(data.getData());

    }

    public String recieve() throws IOException, SocketException {
        String data = this.recieveSocket();
        return data.substring(0, data.length() - 1);
    }
}
