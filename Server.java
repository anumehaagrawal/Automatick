import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;

public class Server {

    private Socket           socket   = null;
    private ServerSocket     server   = null;
    private DataOutputStream out      = null;

    public Server(int port, ArrayList<ArrayList<String>> msgList) {

        try {

            server = new ServerSocket(port);
            System.out.println("\nServer up on port " + Integer.toString(port));
            System.out.println("CTRL + C to stop the server\nWaiting for a client...");

            socket = server.accept();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    try {
                        socket.close();
                        System.out.println("\nGracefully closed connection with Client.");
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

                for (int msg = 0; msg < msgList.size(); msg++) {

                    if ((compareDateFormat.format(date).substring(0, 2).equals(msgList.get(msg).get(1).substring(0, 2))) &&
                        (compareDateFormat.format(date).substring(3, 5).equals(msgList.get(msg).get(1).substring(3, 5))) &&
                        (compareDateFormat.format(date).substring(6, 8).equals(msgList.get(msg).get(1).substring(6, 8)))) {

                        try {

                            System.out.println(displayDateFormat.format(date) + "\n\tBot: " + msgList.get(msg).get(0) + '\n');
                            out.writeUTF(msgList.get(msg).get(0));

                        } catch (IOException e) {

                            System.out.println(e);

                        }
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

        Scanner numScanner = new Scanner(System.in);
        Scanner stringScanner = new Scanner(System.in);

        System.out.print("Enter number of messages: ");

        int numMessages = numScanner.nextInt();
        ArrayList<ArrayList<String>> messageList = new ArrayList<ArrayList<String>>();
        ArrayList<String> tempList;
        String message, dateAndTime;

        for (int i = 0; i < numMessages; i++) {

            tempList = new ArrayList<String>();
            System.out.print("\nEnter the message: ");
            message = stringScanner.nextLine();
            System.out.print("\nEnter the date in HH:mm:ss format: ");
            dateAndTime = stringScanner.nextLine();

            tempList.add(message);
            tempList.add(dateAndTime);
            messageList.add(tempList);

        }

        Server server = new Server(Integer.parseInt(args[0], 10), messageList);

    }

}
