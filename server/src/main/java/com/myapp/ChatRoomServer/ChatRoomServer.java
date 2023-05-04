package com.myapp.ChatRoomServer;

import com.myapp.messages.*;

import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.net.*;
/*
*   Server makes every thread to process the message client sent, and reply to the listener.
*   User is storaged in the hashmap & Alluser list
*   When a user is logged out, delete it from the list & map, declare that fact.
*   Writers storage the stream to each listener.
*/
public class ChatRoomServer extends Application{
    static int i=0;
    Scanner scan;
    private static HashMap<String,User> Userlist=new HashMap<>();
    /*
        Storage Online Users.
    */
    private static Vector<User> AllUser=new Vector<>();
    private static HashSet<ObjectOutputStream>writers=new HashSet<>();
    public void run(){
        System.out.println("Input the port:");
        scan=new Scanner(System.in);
        int port=scan.nextInt();
        try (ServerSocket ss = new ServerSocket(port)) {

            System.out.printf("Server start listening at port %d\n",port);

            Socket s;
            while (true) {
                s = ss.accept();

                if(s==null)throw new IOException("IO occurs error");

                System.out.println("New client request received:" + s);

                ClientHandler mtc = new ClientHandler(s);

                Thread t = new Thread(mtc);

                System.out.println("Adding this client to active client list");

                t.start();

                System.out.printf("Number: %d\n", ++i);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage stage){
        run();
    }

    public static void main(String[] args) {
        launch();
    }
    static class ClientHandler implements Runnable
    {
        private String username;
        private InputStream is;
        private OutputStream os;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private User user;
        Socket s;
        boolean isloggedin;

        public ClientHandler(Socket s) {
            this.s = s;
        }
        // Deal with client sent information (e.g. Transfer Message)
        @Override
        public void run() {
            try {
                // Get the stream.
                is = s.getInputStream();
                os = s.getOutputStream();
                ois = new ObjectInputStream(is);
                oos = new ObjectOutputStream(os);
                // user send the basic information.
                Message first=(Message)ois.readObject();
                UserInitialize(first);
                writers.add(oos);
                System.out.println("Initialized finished");
                Acknowledgelogin();
                while(s.isConnected()){
                    Message info=(Message) ois.readObject();
                    System.out.println("Msg Received");
                    System.out.printf("From %s\n",info.getName());
                    if(info==null)throw new IOException("Fuck");
                    switch (info.getType()){
                        case TEXT:
                            System.out.println("Text Msg Received");
                            writemsg(info);
                            break;
                        case VOICE:
                            writemsg(info);
                            break;
                        case CONNECT:
                            Acknowledgelogin();
                            break;
                    }
                }
            }catch (IOException e) {
                System.out.print("IOException in" + username);
                e.printStackTrace();
            }catch (Exception e) {
                System.out.print("Exception in" + username);
                e.printStackTrace();
            }finally {
                CloseConnection();
            }
        }
        void UserInitialize(Message msg)throws Exception{
            System.out.println("Acknowledge Connected");
            String thisname= msg.getName();
            if(Userlist.containsKey(thisname)){
                System.out.println("Username is occupied or already login, initialize failed.");
                throw new Exception("Username is occupied or already login");
            }
            username=msg.getName();
            user=new User(username,msg.getPicture(), Status.ONLINE);
            AllUser.add(user);
            Userlist.put(thisname,user);
        }
        void writemsg(Message msg) throws IOException{
            System.out.printf("Now write msg, content=%s, Name=%s\n",msg.getMsg(),msg.getName());
            int cnt=0;
            for(ObjectOutputStream writer:writers){
                System.out.printf("Cnt:%d\n",++cnt);
                msg.setUserlist(Userlist);
                msg.setUsers(AllUser);
                writer.writeObject(msg);
                writer.reset();
            }
        }
        Message Acknowledgelogin()throws IOException{
            Message msg = new Message();
            msg.setMsg("Welcome, You have now joined the server! Enjoy chatting!");
            msg.setType(MsgType.CONNECT);
            msg.setName("SERVER");
            writemsg(msg);
            return msg;
        }
        Message Acknowledgelogout()throws IOException{
            Message msg = new Message();
            msg.setMsg("has left the chat.");
            msg.setType(MsgType.DISCONNECT);
            msg.setName("SERVER");
            writemsg(msg);
            return msg;
        }
        private synchronized void CloseConnection(){
            // closing resources
            try {
                is.close();
                os.close();
                ois.close();
                oos.close();
            }
            catch (IOException e){
                System.out.print("IOException in closing" + username);
                e.printStackTrace();
            }
            Userlist.remove(username);
            AllUser.remove(user);
            writers.remove(oos);
            try {
                Acknowledgelogout();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}