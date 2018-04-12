import java.net.*;
import java.io.*;
 
public class server
{
    
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       = null;
    private DataInputStream input    = null;
    private DataOutputStream out     = null;
 
    public server(int port)
    {
        try
        {
            server = new ServerSocket(port);
            System.out.println("Parent is on line");
            System.out.println("Waiting for a response");
 
            socket = server.accept();
            System.out.println("Connected");
            in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
            out    = new DataOutputStream(socket.getOutputStream());
            input  = new DataInputStream(System.in);
 
            
            String line = "exi";
            while(line!="exit")
            {
                line = "";
                String response = "";
                try
                {
                    line = in.readUTF();
                    if(line != null){
                        System.out.println("client :- " + line);
                    }
                    
                    response = input.readLine();
                    if(response != null){
                        System.out.println("Server :-" + response);
                        out.writeUTF(response); 
                    }

                    
                    
 
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
        
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
 
    public static void main(String args[])
    {
        server server = new server(5000);
    }
}