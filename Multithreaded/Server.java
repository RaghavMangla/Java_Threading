import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

import javax.swing.InputMap;

public class Server {

    public Consumer<Socket> getConsumer(){
        // return new Consumer<Socket>(){
        //     public void accept(Socket clientSocket){
        //         try{
        //     PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
        //     toClient.print("Hello from the server");
        //     toClient.close();
        //     clientSocket.close();
        // }catch(IOException ex){
        //     ex.printStackTrace();
        // }
        //     }
        // }
        return (clientSocket)->{
        try{
            PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            toClient.println("Hello from the server");
            // String line = fromClient.readLine();
            // System.out.println("Response from the client: "+line);
            toClient.close();
            fromClient.close();
            clientSocket.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    };
}

    public static void main(String[] args) {
        int port = 8010;
        Server server = new Server();

        try {
            ServerSocket serverSocket = new ServerSocket(port); // creates a TCP listening socket on port 
            // serverSocket.setSoTimeout(10000);
            System.out.println("Server is listening on port: " + port);
            while (true) {
                // serverSocket.accept returns the socket after a client also willingly listens on localhost:port 
                // it creates acceptedSocket representing this specific client connection 
                // and this accept only occurs after the 3 way handshake is complete
                Socket acceptedSocket = serverSocket.accept();
                // now the below thread will take a "runnable task" (below lambda)
                Thread thread = new Thread(()->server.getConsumer().accept(acceptedSocket));
                thread.start();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}