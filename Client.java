import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Client {

    private Socket           socket  = null;
    private DataInputStream  input   = null;
    private DataInputStream  in_rec  = null;
    private DataOutputStream out     = null;

    public Client(String address, int port) {
        
        try {

            socket = new Socket(address, port);
            System.out.println("Successfully Connected to the server!\n");

            input  = new DataInputStream(System.in);
            in_rec = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

        } catch(UnknownHostException e) {

            System.out.println(e);
        
        } catch(IOException i) {
        
            System.out.println(i);
        
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    input.close();
                    socket.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });

        String response;

        while (true) {

        	SimpleDateFormat displayDateFormat = new SimpleDateFormat("E dd/MM/yyyy 'at' hh:mm:ss a");
            Date date = new Date();
            displayDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));

            try {

                response = in_rec.readUTF();
                if (response != null) {
                    System.out.println(displayDateFormat.format(date) + "\n\tBot: " + response + '\n');
                }

            } catch(IOException e) {

                System.out.println(e);
            
            }

        }

    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }

}
