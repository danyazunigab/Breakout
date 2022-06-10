package ce.client.Sockets;

import java.io.IOException;
import java.net.*;

/**
 * Abstract factory of sockets
 * Choose to build a Viewer socket or a client socket
 */
public abstract class SocketFactory {

    static public Socket clientSocket(Integer port) throws SocketException, UnknownHostException {
        Socket clientsocket = new Socket(port);

        Integer errorcode = clientsocket.send("Client_add");
        return clientsocket;

    }

    static public Socket viewerSocket(Integer port) throws SocketException, UnknownHostException {
        Socket viewersocket = new Socket(port);
        Integer errorcode = viewersocket.send("Viewer_add");
        return viewersocket;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Init");
            Socket client = SocketFactory.clientSocket(5555);
            System.out.println("client inited");
            Socket viewer1 = SocketFactory.viewerSocket(5556);
            System.out.println("viewer 1 inited");
            Socket viewer2 = SocketFactory.viewerSocket(5550);
            System.out.println("viewer 2 inited");
            while (true) {
                System.out.println("waiting for msg");
                String clientMsg = null;
                String viewerMsg = null;
                try {
                    System.out.println("waiting for msg for client");
                    clientMsg = client.recieve();
                    System.out.println("waiting for msg for viewer1");
                    viewerMsg = viewer1.recieve();
                    System.out.println("waiting for msg for viewer2");
                    viewerMsg = viewer2.recieve();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("clientMsg");
                System.out.println(clientMsg.toString());
                System.out.println("viewerMsg");
                System.out.println(viewerMsg.toString());

                System.out.println("sending msg for server");
                client.send("hello server");

            }
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
