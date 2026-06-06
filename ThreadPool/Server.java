
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class Server {
    // final: means can be assigned a value only once 
    // ExecutorService: an interface for managing a pool of threads 
    private final ExecutorService threadPool;

    public Server(int poolSize){
        // Fixes the threadpool size 
        this.threadPool = Executors.newFixedThreadPool(poolSize);
    }

    public void handleClient(Socket clientSocket){
        try(
            Socket socket = clientSocket;
            BufferedInputStream fileIn = new BufferedInputStream(new FileInputStream(("sample.txt")));
            BufferedOutputStream socketOut = new BufferedOutputStream(clientSocket.getOutputStream());
        )
        {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while((bytesRead = fileIn.read(buffer))!=-1){
                socketOut.write(buffer,0,bytesRead);
            }
            socketOut.flush();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        int port = 8010;
        int poolSize=100;
        Server server = new Server(poolSize);
        try{
            ServerSocket serverSocket = new ServerSocket(port); // creates a TCP listening socket on port 
            System.out.println("Server is listening on port: " + port);
            while (true) {
                // serverSocket.accept returns the socket after a client also willingly listens on localhost:port 
                // it creates acceptedSocket representing this specific client connection 
                // and this accept only occurs after the 3 way handshake is complete
                Socket acceptedSocket = serverSocket.accept();
                // now the below thread will take a "runnable task" (below lambda)
                // execute() takes a runnable and does nothing
                server.threadPool.execute(()->server.handleClient(acceptedSocket));
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            server.threadPool.shutdown();
        }
    }
}
