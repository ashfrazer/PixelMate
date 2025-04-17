package edu.uca.swe.Game.Connection;

import ocsf.server.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Server extends AbstractServer {
    private final Map<String, ConnectionToClient> players = new HashMap<>();
    private String hostUsername;
    private String clientUsername;

    public Server(int port) {
        super(port);
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        System.out.println("Received: " + msg.toString());

        if (msg.equals("host")) {
            client.setInfo("role", "white");
            players.put("host", client);
            try {
                client.sendToClient("You are white");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (msg.equals("client")) {
            client.setInfo("role", "black");
            players.put("client", client);
            try {
                client.sendToClient("You are black");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (msg instanceof String) {
            String message = (String) msg;
            if (message.startsWith("H_USERNAME: ")) {
                setHostUsername(message.substring("H_USERNAME: ".length()).trim());
            } else if (message.startsWith("C_USERNAME: ")) {
                setClientUsername(message.substring("C_USERNAME: ".length()).trim());
            } else if (message.contains("[")){
                if(client.getName().equals("Thread-1")){
                    try {
                        players.get("client").sendToClient(msg);
                        //System.out.println(msg);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if(client.getName().equals("Thread-2")){
                    try {
                        players.get("host").sendToClient(msg);
                    System.out.println(msg);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            } else{sendToAllClients(msg);}
        }
        //sendToAllClients(msg);

        // Connection -- share usernames
        //if (players.containsKey("host") && players.containsKey("client")) {
          //  System.out.println("Host and Client are now connected!");
            //sendToAllClients("H_USERNAME: " + getHostUsername());
        //    sendToAllClients("C_USERNAME: " + getClientUsername());
          //  try {
            //    ConnectionToClient hostConn = players.get("host");
              //  ConnectionToClient clientConn = players.get("client");

                //hostConn.sendToClient("Both players connected. Game can start!");
             //   clientConn.sendToClient("Both players connected. Game can start!");

            //    String host = (hostUsername != null) ? hostUsername : "Unknown";
              //  String clientName = (clientUsername != null) ? clientUsername : "Unknown";
                //String userMessage = "USERNAMES:" + host + "," + clientName;

        //        hostConn.sendToClient(userMessage);
          //      clientConn.sendToClient(userMessage);
            //} catch (IOException e) {
              //  e.printStackTrace();
            //}
        //}
    }

    @Override
    protected void serverStarted() {
        System.out.println("Server started and waiting for clients...");
    }

    @Override
    protected void serverStopped() {
        System.out.println("Server stopped.");
    }

    public void setHostUsername(String username) {
        this.hostUsername = username;
    }

    public String getHostUsername() {
        return hostUsername;
    }

    public void setClientUsername(String username) {
        this.clientUsername = username;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public static void main(String[] args) {
        Server server = new Server(5555);
        try {
            server.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}