import java.net.*;
import java.io.*;
 
public class client
{
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataInputStream  in_rec  = null;
    private DataOutputStream out     = null;
    public client(String address, int port)
    {
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
            input  = new DataInputStream(System.in);
            in_rec = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        String line = "";
        String answer = "";

        while (!line.equals("Over"))
        {   
            line = "";
            answer = "";
            try
            {   

                line = input.readLine();
                if(line != null){
                    System.out.println("client :- " + line);
                    out.writeUTF(line);
                }
                
                answer = in_rec.readUTF();
                if(answer != null){
                    System.out.println("server :- " + answer);
                }
   
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
 
    public static void main(String args[])
    {
        client client = new client("127.0.0.1", 5000);
    }
}