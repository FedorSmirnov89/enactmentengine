package at.enactmentengine.serverless.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Service {

    private static int port = 9898;
    private static boolean running = true;

    static final Logger logger = LoggerFactory.getLogger(Service.class);

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(port);

        logger.info("Server is up and running at " + InetAddress.getLocalHost().getHostAddress() + ":" + port);

        Socket socket = null;
        while (running) {
            logger.info("Waiting for client(s)...");
            socket = serverSocket.accept();

            Thread handler = new Thread(new Handler(socket));
            handler.start();
            logger.info("Handle client in thread " + handler.getId());
        }

        socket.close();
        serverSocket.close();
    }
}