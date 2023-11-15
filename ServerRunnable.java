package calculator;

import java.io.*;
import java.net.Socket;

public class ServerRunnable implements Runnable {
    private Socket clientSocket;

    // Constructor to initialize ServerRunnable with a client socket
    public ServerRunnable(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Setting up input and output streams for communication with the client
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(clientSocket.getOutputStream());

            // Continuous loop to handle client requests
            while (true) {
                // Read client input
                String clientSentence = inFromClient.readLine();

                // Check if the client wants to end the connection
                if (clientSentence.equalsIgnoreCase("end")) {
                    System.out.println("== Client entered END ==");
                    return;
                }

                // Split the client input into components
                String[] split = clientSentence.split(" ");
                String represent = "";
                String answerSentence;

                try {
                    // Arithmetic operation handling logic
                    if (split.length < 3) {
                        System.out.println("Incorrect: " + Error.SHORT.get());
                        throw new Exception(Error.SHORT.get());
                    } else if (split.length > 3) {
                        System.out.println("Incorrect: " + Error.MANY.get());
                        throw new Exception(Error.MANY.get());
                    } else {
                        switch (split[0].toUpperCase()) {
                            case "ADD":
                                int addResult = Integer.parseInt(split[1]) + Integer.parseInt(split[2]);
                                answerSentence = "Answer: " + addResult;
                                represent = split[1] + " + " + split[2] + " = " + addResult;
                                break;
                            case "SUB":
                                int subResult = Integer.parseInt(split[1]) - Integer.parseInt(split[2]);
                                answerSentence = "Answer: " + subResult;
                                represent = split[1] + " - " + split[2] + " = " + subResult;
                                break;
                            case "MUL":
                                int mulResult = Integer.parseInt(split[1]) * Integer.parseInt(split[2]);
                                answerSentence = "Answer: " + mulResult;
                                represent = split[1] + " * " + split[2] + " = " + mulResult;
                                break;
                            case "DIV":
                                if (split[2].equals("0")) {
                                    System.out.println("Incorrect: " + Error.DIVIDED.get());
                                    throw new Exception(Error.DIVIDED.get());
                                } else {
                                    int divResult = Integer.parseInt(split[1]) / Integer.parseInt(split[2]);
                                    answerSentence = "Answer: " + divResult;
                                    represent = split[1] + " / " + split[2] + " = " + divResult;
                                    break;
                                }
                            default:
                                System.out.println("Incorrect: " + Error.INVALID_OP.get());
                                throw new Exception(Error.INVALID_OP.get());
                        }
                    }
                } catch (NumberFormatException e) {
                    // Handle the case when the input is not a valid number
                    System.out.println("Incorrect: " + Error.INVALID_NUM.get());
                    answerSentence = Error.INVALID_NUM.get();
                } catch (Exception e) {
                    // Handle other exceptions and provide appropriate error messages
                    answerSentence = e.getMessage();
                }

                // Print the representation of the operation
                System.out.println(represent);

                // Send the result back to the client
                outToClient.writeBytes(answerSentence + "\n");
            }
        } catch (IOException e) {
            // Handle IOExceptions by printing the stack trace
            e.printStackTrace();
        }
    }
}
