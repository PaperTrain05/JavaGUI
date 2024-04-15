package it.paper;

/*
* DB:
* Client: 4: Nome, Cognome, Numero di telefono, Numero ombrellone
* */


import it.paper.objects.Prenotazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main extends JFrame {

    private ArrayList<Prenotazione> prenotazioni;

    private JTextField textFieldNome;
    private JTextField textFieldNumeroOmbrellone;
    private JButton btnPrenota;
    private JButton btnElimina;
    private JTextArea textAreaPrenotazioni;

    private static final String FILE_PATH = "prenotazioni.txt";

    public Main() {
        prenotazioni = new ArrayList<>();

        setTitle("Gestione Prenotazioni Ombrelloni");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel lblNome = new JLabel("Nome:");
        panel.add(lblNome);

        textFieldNome = new JTextField();
        panel.add(textFieldNome);

        JLabel lblNumeroOmbrellone = new JLabel("Numero Ombrellone:");
        panel.add(lblNumeroOmbrellone);

        textFieldNumeroOmbrellone = new JTextField();
        panel.add(textFieldNumeroOmbrellone);

        btnPrenota = new JButton("Prenota");
        btnPrenota.addActionListener(e -> prenotaOmbrellone());
        panel.add(btnPrenota);

        btnElimina = new JButton("Elimina");
        btnElimina.addActionListener(e -> eliminaPrenotazione());
        panel.add(btnElimina);

        textAreaPrenotazioni = new JTextArea();
        textAreaPrenotazioni.setEditable(false);
        panel.add(textAreaPrenotazioni);

        aggiornaListaPrenotazioni();

        add(panel);
    }

    private void prenotaOmbrellone() {
        String nome = textFieldNome.getText();
        int numeroOmbrellone = Integer.parseInt(textFieldNumeroOmbrellone.getText());

        Prenotazione prenotazione = new Prenotazione(nome, numeroOmbrellone);
        prenotazioni.add(prenotazione);

        salvaPrenotazioniSuFile();
        aggiornaListaPrenotazioni();
    }

    private void eliminaPrenotazione() {
        int numeroOmbrellone = Integer.parseInt(textFieldNumeroOmbrellone.getText());

        prenotazioni.removeIf(p -> p.getNumeroOmbrellone() == numeroOmbrellone);

        salvaPrenotazioniSuFile();
        aggiornaListaPrenotazioni();
    }

    private void aggiornaListaPrenotazioni() {
        StringBuilder sb = new StringBuilder();
        for (Prenotazione prenotazione : prenotazioni) {
            sb.append("Nome: ").append(prenotazione.getNome()).append(", Numero Ombrellone: ").append(prenotazione.getNumeroOmbrellone()).append("\n");
        }
        textAreaPrenotazioni.setText(sb.toString());
    }

    private void salvaPrenotazioniSuFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Prenotazione prenotazione : prenotazioni) {
                writer.println(prenotazione.getNome() + "," + prenotazione.getNumeroOmbrellone());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}