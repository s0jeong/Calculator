package calculator;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;

public class Client {

    public static void main(String[] args) {
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

            // Create the client socket outside the loop
            Socket clientSocket = new Socket(serverIp, serverPort);
            ExecutorService executorService = Executors.newCachedThreadPool();

            // Print a message for the user
            System.out.println("When you want to end, enter END\n");

            // Move input and output streams outside the loop
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Continuous loop for client interaction
            while (true) {
                // Read user input
                String userInput = inFromUser.readLine();

                // Send the user input to the server along with a newline character
                outToServer.writeBytes(userInput + '\n');
                outToServer.flush(); // Ensure the data is sent immediately

                // Receive the result from the server
                String sentence = inFromServer.readLine();

                // Check if the user wants to end the connection
                if (userInput.equalsIgnoreCase("end")) {
                    System.out.println("Good bye!");
                    clientSocket.close();
                    break;
                }
                // Handle different error messages received from the server
                else if (sentence.equals(Error.SHORT.get()))
                    System.out.println("Error message: " + sentence);
                else if (sentence.equals(Error.MANY.get()))
                    System.out.println("Error message: " + sentence);
                else if (sentence.equals(Error.DIVIDED.get()))
                    System.out.println("Error message: " + sentence);
                else if (sentence.equals(Error.INVALID_OP.get()))
                    System.out.println("Error message: " + sentence);
                else
                    System.out.println(sentence);
            }

            // Shutdown executor service outside the loop
            executorService.shutdown();
        } catch (Exception e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
