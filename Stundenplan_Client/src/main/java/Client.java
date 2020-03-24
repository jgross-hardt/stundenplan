import client.StundenplanClient;

import javax.swing.*;
import java.io.IOException;

public class Client {
    public StundenplanClient client;
    public GUI gui;

    public Client() {
        gui = new GUI();
        int n = JOptionPane.showConfirmDialog(null, "Hast du bereits einen Account?", "Anmeldung", JOptionPane.YES_NO_OPTION);
        if((n == JOptionPane.NO_OPTION)) {
            Credentials logData = gui.register();
            client = new StundenplanClient(logData.getUsername(), logData.getPassword());
        }
        else {
            Credentials logData = gui.login();
            client = new StundenplanClient(logData.getUsername(), logData.getPassword());
        }
    }

    public static void main(String[] args) {
        Client obj = new Client();
    }
}