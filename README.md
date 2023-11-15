# Calculator

A calculator program that supports four basic arithmetic operations: addition, subtraction, multiplication, and division,
The server interprets and calculates the arithmetic operation formula received from the Client and sends the result back to the Client. The client receives an arithmetic operation equation from the User, transmits it to the server, and receives the result from the server and outputs it. 
If an incorrect input occurs, the server generates an error message for the client's incorrect input, defines a meaningful error type for it, and sends it to the client.
At this time, the communication protocol between the client and the server using a message format based on ASCII code is used for communication between programs.
The server is implemented using ThreadPool and Runnable interfaces to handle multiple clients simultaneously, and the client reads the server information from the configuration file (server_into.txt). Use the default value if the file does not exist.
