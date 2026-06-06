import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.print.event.PrintEvent;

public class Client {

    public Runnable getRunnable() {
        return new Runnable() {
            @Override
            public void run(){
                int port = 8010;
                try{
                Socket socket = new Socket("localhost",port);
                PrintWriter toSocket = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                toSocket.println("Hello from client " + socket.getLocalAddress()+socket.getPort());
                String line = fromSocket.readLine();
                System.out.println("Response from the server "+line);
                toSocket.close();
                fromSocket.close();
                socket.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        };
    }
    public static void main(String[] args) {
        Client client = new Client();
        for(int i=0;i<100;i++)
        {
            try{
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

   
}
