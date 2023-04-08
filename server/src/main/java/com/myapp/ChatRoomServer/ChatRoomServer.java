import javafx.application.Application;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.*;
import java.net.*;

public class ChatRoomServer extends Application {
    static Vector<ClientHandler> ar=new Vector<>();
    static int i=0;

    public void run() throws Exception{
        ServerSocket ss=new ServerSocket(2521);
        Socket s;
        while(true){
            s = ss.accept();
            System.out.println("New client request received:"+s);
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            System.out.println("Creating a new handler for the client");

            ClientHandler mtch = new ClientHandler(s,"client" + i,dis,dos);

            Thread t = new Thread(mtch);

            System.out.println("Adding this client to active client list");

            ar.add(mtch);

            t.start();

            System.out.printf("Number: %d\n",i);

            ++i;
        }
    }
    @Override
    public void start(Stage stage) throws Exception{
        run();
    }

    public static void main(String[] args) {
        launch();
    }

}

// ClientHandler class
class ClientHandler implements Runnable
{
    Scanner scn = new Scanner(System.in);
    private String name;
    final DataInputStream dis;
    final DataOutputStream dos;
    Socket s;
    boolean isloggedin;

    // constructor
    public ClientHandler(Socket s, String name,
                         DataInputStream dis, DataOutputStream dos) {
        this.dis = dis;
        this.dos = dos;
        this.name = name;
        this.s = s;
        this.isloggedin=true;
    }

    @Override
    public void run() {

        String received;
        while (true)
        {
            try
            {
                // receive the string
                received = dis.readUTF();

                System.out.println(received);

                if(received.equals("logout")){
                    System.out.println("Client logging out");
                    this.isloggedin=false;
                    this.s.close();
                    break;
                }

                // break the string into message and recipient part
                StringTokenizer st = new StringTokenizer(received, "#");
                String MsgToSend = st.nextToken();
                String recipient = st.nextToken();
                System.out.printf("MTS:%s, recipient:%s\n",MsgToSend,recipient);
                // search for the recipient in the connected devices list.
                // ar is the vector storing client of active users
                for (ClientHandler mc : ChatRoomServer.ar)
                {
                    // if the recipient is found, write on its
                    // output stream
                    System.out.printf("Searching...This Name equals:%s\n",mc.name);
                    if (mc.name.equals(recipient) && mc.isloggedin==true)
                    {
                        mc.dos.writeUTF(this.name+" : "+MsgToSend);
                        break;
                    }
                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}