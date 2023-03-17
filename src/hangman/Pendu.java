package hangman;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Pendu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int MAX_ERRORS = 7;
    private List<String> words;
    private String word;
    private int errors;
    private boolean[] guessedLetters;
    private JLabel wordLabel;
    private JLabel errorsLabel;
    private JButton[] letterButtons;

    public Pendu() {
        super("Jeu du pendu");

        // Charger les mots à partir du fichier CSV
        words = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/hangman/mots.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement des mots.");
            System.exit(1);
        }

        // Initialiser l'interface utilisateur
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel wordPanel = new JPanel(new GridLayout(1, 0));
        wordLabel = new JLabel();
        wordPanel.add(wordLabel);
        mainPanel.add(wordPanel, BorderLayout.CENTER);

        JPanel lettersPanel = new JPanel(new GridLayout(3, 9));
        letterButtons = new JButton[26];
        for (int i = 0; i < 26; i++) {
            char c = (char) ('A' + i);
            letterButtons[i] = new JButton("" + c);
            letterButtons[i].addActionListener(this);
            lettersPanel.add(letterButtons[i]);
        }
        mainPanel.add(lettersPanel, BorderLayout.SOUTH);

        errorsLabel = new JLabel();
        mainPanel.add(errorsLabel, BorderLayout.NORTH);

        getContentPane().add(mainPanel);

        setSize(new Dimension(700, 400));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Nouvelle partie
        nouvellePartie();
    }

    private void nouvellePartie() {
        // Choisis un mot aléatoire
        Random rand = new Random();
        word = words.get(rand.nextInt(words.size()));

        // Initialiser les variables de jeu
        errors = 0;
        guessedLetters = new boolean[word.length()];
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i))) {
                guessedLetters[i] = false;
            } else {
                guessedLetters[i] = true;
            }
        }

        // Afficher le mot à trouver
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (guessedLetters[i]) {
                sb.append(word.charAt(i));
            } else {
                sb.append("_");
            }
        }
        wordLabel.setText(sb.toString());

        // Réinitialiser les boutons des lettres
        for (JButton button : letterButtons) {
            button.setEnabled(true);
            /*button.setBackground(Color.WHITE);*/
        }

        // Réinitialiser le compteur d'erreurs
        errorsLabel.setText("Erreurs : 0/" + MAX_ERRORS);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Vérifier si le bouton appuyé correspond à une lettre du mot
        String letter = e.getActionCommand();
        char c = letter.charAt(0);
        boolean found = false;
        for (int i = 0; i < word.length(); i++) {
            if (Character.toUpperCase(word.charAt(i)) == c) {
                guessedLetters[i] = true;
                found = true;
            }
        }
        // Mettre à jour l'interface utilisateur
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (guessedLetters[i]) {
                stringBuilder.append(word.charAt(i));
            } else {
                stringBuilder.append("_");
            }
        }
        wordLabel.setText(stringBuilder.toString());

        ((JButton) e.getSource()).setEnabled(false);

        if (!found) {
            errors++;
            errorsLabel.setText("Erreurs : " + errors + "/" + MAX_ERRORS);
        }

        if (errors == MAX_ERRORS) {
            // Si perdu
            JOptionPane.showMessageDialog(this, "Vous avez perdu ! Le mot était \"" + word + "\".");
            nouvellePartie();
        } else if (stringBuilder.indexOf("_") == -1) {
            // Si gagné
            JOptionPane.showMessageDialog(this, "Vous avez gagné !");
            nouvellePartie();
        }
    }

    public static void main(String[] args) {
        new Pendu().setVisible(true);
    }
}