package edu.uca.swe.Game.Connection;

import ocsf.server.*;

import java.io.IOException;

public class Server extends AbstractServer {

    public Server(int port) {
        super(port);
    }

    @Override
    protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
        System.out.println("Received: " + msg.toString());

        if (msg.equals("host")) {
            client.setInfo("role", "white");
            try {
                client.sendToClient("You are white");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (msg.equals("client")) {
            client.setInfo("role", "black");
            try {
                client.sendToClient("You are black");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        this.sendToAllClients(msg);
    }


    @Override
    protected void serverStarted() {
        System.out.println("Server started and waiting for clients...");
    }

    @Override
    protected void serverStopped() {
        System.out.println("Server stopped.");
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
