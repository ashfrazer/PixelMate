package edu.uca.swe.Game.Connection;

import edu.uca.swe.GUI.Panels.HostPanel;
import edu.uca.swe.GUI.Panels.JoinPanel;
import ocsf.client.AbstractClient;

import javax.swing.*;

public class Client extends AbstractClient {

    private String username;
    private HostPanel hostPanel;
    private JoinPanel joinPanel;

    public Client(String host, int port, String username) {
        super(host, port);
        this.username = username;
    }

    public void handleMessageFromServer(Object msg) {
        System.out.println("Message from server: " + msg.toString());

        String message = msg.toString();

        if (message.contains("white")) {
            System.out.println("Client is white!");
        } else if (message.contains("black")) {
            System.out.println("Client is black!");
        } else if (message.startsWith("USERNAMES:")) {
            String[] usernames = message.substring("USERNAMES:".length()).split(",");
            String host = usernames.length > 0 ? usernames[0] : "Unknown";
            String clientUser = usernames.length > 1 ? usernames[1] : "Unknown";

            SwingUtilities.invokeLater(() -> {
                if (hostPanel != null) {
                    hostPanel.updateWaitingLabel(clientUser);
                }
                if (joinPanel != null) {
                    joinPanel.updateWaitingLabel(host);
                }
            });
        }
    }

    public void setHostPanel(HostPanel hostPanel) {
        this.hostPanel = hostPanel;
    }

    public void setJoinPanel(JoinPanel joinPanel) {
        this.joinPanel = joinPanel;
    }

    public void sendMove(Object move) {
        try {
            sendToServer(move);
        } catch (Exception e) {
            System.err.println("Error sending move: " + e.getMessage());
        }
    }
}
