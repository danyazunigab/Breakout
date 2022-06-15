package ce.client.Sockets;

import java.io.IOException;
import java.net.*;

/**
 * Socket class
 * Encapsulate a UDP socket
 * <p>
 * Use send fucntion and recieve to interactuate
 * Use port=0 to auto assign a random free port
 */
public class Socket {
    /**
     * UDP Socket
     */
    private final DatagramSocket Socket;
    /**
     * Ip of the socket, if it don't change over differents {@link Socket} make it static;
     */
    public InetAddress ip = InetAddress.getByName("localhost");

    /**
     * Constructor
     *
     * @param port port, use 0 to auto assign to a free one
     * @throws UnknownHostException
     * @throws SocketException
     */
    public Socket(Integer port) throws UnknownHostException, SocketException {
        this.Socket = new DatagramSocket(port, this.ip);

    }

    /**
     * Send a string to server
     * Server use a static port 8080
     * don't check id the server recieve it, only chek if {@link Socket} send it
     *
     * @param toSend String to send
     * @return an integer, -1 when fail to send due to a server error, and 0 when sendend
     */
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

    /**
     * Function that recieve the data from server and transform it on a String
     * Recieve a max of 500 chars
     * Inter Use
     *
     * @return a String that contains the data from server
     * @throws IOException
     */
    private String recieveSocket() throws IOException {
        DatagramPacket data = new DatagramPacket(new byte[500], 500);
        this.Socket.receive(data);

        return new String(data.getData());

    }

    /**
     * Recieve the data from the C Server and delete the "\0" character
     *
     * @return A String with the adecuate data from server
     * @throws IOException
     */

    public String recieve() throws IOException {
        String data = this.recieveSocket();
        return data.substring(0, data.length() - 1);
    }
}
