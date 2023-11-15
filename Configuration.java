package calculator;

import java.io.*;

public class Configuration {
    private String serverIp;
    private int serverPort;

    // Constructor to set default values for server IP and port
    public Configuration() {
        this.serverIp = "localhost";
        this.serverPort = 8888;
    }

    // Getter method for server IP
    public String getServerIp() {
        return serverIp;
    }

    // Getter method for server port
    public int getServerPort() {
        return serverPort;
    }

    // Method to read server configuration from a file
    public void readConfigurationFromFile(String fileName) {
        try {
            // Create a BufferedReader to read from the file
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            
            // Read the first line from the file
            String line = in.readLine();

            // Check if the line is not null
            if (line != null) {
                // Split the line into an array of strings using space as the delimiter
                String[] serverInfo = line.split(" ");
                
                // Check if the array has exactly two elements
                if (serverInfo.length == 2) {
                    // Set the server IP based on the first element
                    this.serverIp = serverInfo[0];
                    
                    // Parse the second element as an integer for the server port
                    int port = Integer.parseInt(serverInfo[1]);
                    
                    // Check if the port is within a valid range
                    if (port >= 1 && port <= 65535) {
                        this.serverPort = port;
                    } else {
                        // Print an error message for an invalid port number
                        System.err.println("Invalid port number: " + port);
                    }
                }
            }
            
            // Close the BufferedReader
            in.close();
        } catch (IOException | NumberFormatException e) {
            // Print the stack trace if an exception occurs
            e.printStackTrace();
        }
    }
}
