package calculator;

import java.net.*;
import java.util.concurrent.*;

public class Server {

    public static void main(String[] args) throws Exception {
        try {
            // Create a Configuration object to read server settings
            Configuration serverConfig = new Configuration();
            
            // Read server information from the configuration file
            serverConfig.readConfigurationFromFile("server_info.txt");

            // Get the server's IP address and port number
            String serverIp = serverConfig.getServerIp();
            int serverPort = serverConfig.getServerPort();

            // Print server information
            System.out.println("Server IP: " + serverIp);
            System.out.println("Server Port: " + serverPort);

            // Create a thread pool
            ExecutorService executorService = Executors.newCachedThreadPool();
            
            // Create a server socket
            ServerSocket welcomeSocket = new ServerSocket(serverPort);

            // Print server start message
            System.out.println("Server start..\n");

            while (true) {
                // Accept client connection
                Socket clientSocket = welcomeSocket.accept();

                // Create a new instance of ServerRunnable for each client
                Runnable serverRunnable = new ServerRunnable(clientSocket);

                // Submit the runnable to the thread pool
                executorService.submit(serverRunnable);
            }
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
