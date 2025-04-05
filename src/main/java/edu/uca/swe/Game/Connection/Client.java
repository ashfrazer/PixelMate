package edu.uca.swe.Game.Connection;

import ocsf.client.*;

public class Client extends AbstractClient {

    public Client(String host, int port) {
        super(host, port);
    }

    @Override
    protected void handleMessageFromServer(Object msg) {
        System.out.println("Message from server: " + msg.toString());
    }

    public void sendMove(Object move) {
        try {
            sendToServer(move);
        } catch (Exception e) {
            System.err.println("Error sending move: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 5555);
        try {
            client.openConnection();
            client.sendToServer("Hello server!");
            System.out.println("Connected to server!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
