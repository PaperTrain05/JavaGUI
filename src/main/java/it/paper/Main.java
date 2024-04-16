package it.paper;

/*
* DB:
* Client: 4: Nome, Cognome, Numero di telefono, Numero ombrellone
* */




import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}