package com.myapp.client.chat;

import com.myapp.client.login.LoginController;
import com.myapp.messages.*;

import java.io.*;
import java.net.Socket;

import static com.myapp.messages.MsgType.CONNECT;


public class Listener implements Runnable{

    private static final String HASCONNECTED = "has connected";
    private Socket socket;
    public String hostname;
    public int port;
    public static String username;
    public ChatController controller;
    private static ObjectOutputStream oos;
    private InputStream is;
    private ObjectInputStream input;
    private OutputStream outputStream;

    public Listener(String hostname, int port, String username,  ChatController controller) {
        this.hostname = hostname;
        this.port = port;
        Listener.username = username;
        this.controller = controller;
    }

    public void run() {
        try {
            socket = new Socket(hostname, port);
            outputStream = socket.getOutputStream();
            oos = new ObjectOutputStream(outputStream);
            is = socket.getInputStream();
            input = new ObjectInputStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("CON+");
            connect();
            System.out.println("CON-");
            while (socket.isConnected()) {
                Message message = null;
                message = (Message) input.readObject();
                System.out.printf("Received Msg from %s\n",message.getName());
                if (message != null) {
                    switch (message.getType()) {
                        case TEXT:
                            System.out.println("Text: addToChat");
                            controller.addToChat(message);
                            break;
//                        case VOICE:
//                            controller.addToChat(message);
//                            break;
//                        case NOTIFICATION:
//                            controller.newUserNotification(message);
//                            break;
                        case CONNECT:
                            controller.setUserList(message);
                            break;
                        case DISCONNECT:
                            controller.setUserList(message);
                            break;
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            controller.logoutScene();
        }
    }

    public static void send(String msg) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MsgType.TEXT);
//        createMessage.setStatus(Status.AWAY);
        createMessage.setMsg(msg);
        oos.writeObject(createMessage);
        oos.flush();
    }
    public static void sendVoiceMessage(byte[] audio) throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(MsgType.VOICE);
//        createMessage.setStatus(Status.AWAY);
        createMessage.setVoiceMsg(audio);
        oos.writeObject(createMessage);
        oos.flush();
    }
    public static void connect() throws IOException {
        Message createMessage = new Message();
        createMessage.setName(username);
        createMessage.setType(CONNECT);
        createMessage.setMsg(HASCONNECTED);
        oos.writeObject(createMessage);
    }
}
