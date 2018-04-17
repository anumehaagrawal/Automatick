import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Server {

    private Socket           socket   = null;
    private ServerSocket     server   = null;
    private DataOutputStream out      = null;

    public Server(int port) {

        try {

            server = new ServerSocket(port);
            System.out.println("Server up on port " + Integer.toString(port));
            System.out.println("Waiting for a client...");

            socket = server.accept();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
            });
            System.out.println("Successfuly Connected to the client!\n");

            out = new DataOutputStream(socket.getOutputStream());
            String response  = new String("Its timeeee");

            while (true) {

                SimpleDateFormat compareDateFormat = new SimpleDateFormat("HH:mm:ss"),
                displayDateFormat = new SimpleDateFormat("E dd/MM/yyyy 'at' hh:mm:ss a");
                Date date = new Date();
                compareDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
                displayDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));


                if ((Integer.parseInt(compareDateFormat.format(date).substring(0, 2)) == 5) && 
                    (Integer.parseInt(compareDateFormat.format(date).substring(3, 5)) == 24) &&
                    (Integer.parseInt(compareDateFormat.format(date).substring(6, 8)) == 10)) {

                    try {

                        System.out.println(displayDateFormat.format(date) + "\n\tBot: " + response + '\n');
                        out.writeUTF(response); 

                    } catch (IOException e) {
                        
                        System.out.println(e);
                    
                    }
                } else if ((Integer.parseInt(compareDateFormat.format(date).substring(0, 2)) == 5) && 
                    (Integer.parseInt(compareDateFormat.format(date).substring(3, 5)) == 24) &&
                    (Integer.parseInt(compareDateFormat.format(date).substring(6, 8)) == 30)) {

                    try {

                        System.out.println(displayDateFormat.format(date) + "\n\tBot: " + response + '\n');
                        out.writeUTF(response); 

                    } catch (IOException e) {
                        
                        System.out.println(e);
                    
                    }
                }

                try {

                    Thread.sleep(1000);
                
                } catch (Exception e) {
                
                    System.out.println(e);
                
                }

            }

        } catch(IOException e) {

            System.out.println(e);
        
        }

    }

    public static void main(String args[]) {
        Server server = new Server(5000);
    }

}
