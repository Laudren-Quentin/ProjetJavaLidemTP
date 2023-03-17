package conversion;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ConversionApp extends JFrame implements ActionListener {

    private ExchangeRates exchangeRates ;
    // Les taux de change
    private double FAHRENHEIT_TO_CELSIUS_RATIO;
    private double MILES_TO_KILOMETERS_RATIO;
    private double EURO_TO_DOLLAR_RATIO;
    private double EURO_TO_STERLING_RATIO;
    private double EURO_TO_ROUBLE_RATIO;


    // Les composants de l'interface graphique

    private JLabel resultLabel;
    private JComboBox<String> conversionList;
    private JPanel inputPanel, buttonPanel, exchangeRatePanel;
    private JTextField inputField;
    private JTextField fahrenheitToCelsiusField;
    private JTextField milesToKilometersField;
    private JTextField euroToDollarField;
    private JTextField euroToSterlingField;
    private JTextField euroToRoubleField;

    public ConversionApp() {

        this.exchangeRates = new ExchangeRates();
        this.FAHRENHEIT_TO_CELSIUS_RATIO = exchangeRates.getFahrenheitToCelsiusRatio();
        this.MILES_TO_KILOMETERS_RATIO = exchangeRates.getMilesToKilometersRatio();
        this.EURO_TO_DOLLAR_RATIO = exchangeRates.getEuroToDollarRatio();
        this.EURO_TO_STERLING_RATIO = exchangeRates.getEuroToSterlingRatio();
        this.EURO_TO_ROUBLE_RATIO = exchangeRates.getEuroToRoubleRatio();

        // Configuration de la fenêtre principale
        setTitle("Conversion App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);

        // Configuration des composants de l'interface graphique
        inputField = new JTextField();
        resultLabel = new JLabel();
        conversionList = new JComboBox<String>(new String[]{
                "Fahrenheit -> Celsius",
                "Miles -> Kilomètres/heure",
                "Euro -> Dollar",
                "Euro -> Livre sterling",
                "Euro -> Rouble"
        });
        inputPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel = new JPanel();

        // Ajout des fields comportant le taux de change à modifier
        fahrenheitToCelsiusField = new JTextField(Double.toString(FAHRENHEIT_TO_CELSIUS_RATIO));
        milesToKilometersField = new JTextField(Double.toString(MILES_TO_KILOMETERS_RATIO));
        euroToDollarField = new JTextField(Double.toString(EURO_TO_DOLLAR_RATIO));
        euroToSterlingField = new JTextField(Double.toString(EURO_TO_STERLING_RATIO));
        euroToRoubleField = new JTextField(Double.toString(EURO_TO_ROUBLE_RATIO));

        inputPanel = new JPanel(new GridLayout(7, 2)); // Augmentez le nombre de lignes pour inclure les champs de saisie
        buttonPanel = new JPanel();

        // Ajout des composants à la fenêtre principale
        inputPanel.add(new JLabel("Entrez une valeur :"));
        inputPanel.add(inputField);
        inputPanel.add(new JLabel("Choisissez une conversion :"));
        inputPanel.add(conversionList);

        // Ajout d'un bouton Convertir
        JButton convertButton = new JButton("Convertir");
        convertButton.addActionListener(this);
        buttonPanel.add(convertButton);

        // Ajout du bouton "Modifier les taux de change"
        JButton editRatesButton = new JButton("Modifier les taux de change");
        editRatesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame editRatesFrame = new JFrame("Modifier les taux de change");
                editRatesFrame.setSize(400, 300);
                editRatesFrame.setLocationRelativeTo(null);

                // Création des composants de la fenêtre de modification des taux de change
                JPanel editRatesPanel = new JPanel(new GridLayout(5, 2));
                JLabel fahrenheitToCelsiusLabel = new JLabel("Fahrenheit -> Celsius : ");
                JTextField fahrenheitToCelsiusField = new JTextField(String.valueOf(exchangeRates.getFahrenheitToCelsiusRatio()));
                JLabel milesToKilometersLabel = new JLabel("Miles -> Kilomètres/heure : ");
                JTextField milesToKilometersField = new JTextField(String.valueOf(exchangeRates.getMilesToKilometersRatio()));
                JLabel euroToDollarLabel = new JLabel("Euro -> Dollar : ");
                JTextField euroToDollarField = new JTextField(String.valueOf(exchangeRates.getEuroToDollarRatio()));
                JLabel euroToSterlingLabel = new JLabel("Euro -> Livre sterling : ");
                JTextField euroToSterlingField = new JTextField(String.valueOf(exchangeRates.getEuroToSterlingRatio()));
                JLabel euroToRoubleLabel = new JLabel("Euro -> Rouble : ");
                JTextField euroToRoubleField = new JTextField(String.valueOf(exchangeRates.getEuroToRoubleRatio()));

                // Ajout des composants à la fenêtre de modification des taux de change
                editRatesPanel.add(fahrenheitToCelsiusLabel);
                editRatesPanel.add(fahrenheitToCelsiusField);
                editRatesPanel.add(milesToKilometersLabel);
                editRatesPanel.add(milesToKilometersField);
                editRatesPanel.add(euroToDollarLabel);
                editRatesPanel.add(euroToDollarField);
                editRatesPanel.add(euroToSterlingLabel);
                editRatesPanel.add(euroToSterlingField);
                editRatesPanel.add(euroToRoubleLabel);
                editRatesPanel.add(euroToRoubleField);
                editRatesFrame.add(editRatesPanel, BorderLayout.CENTER);

                // Création du bouton de validation des modifications
                JButton saveButton = new JButton("Enregistrer");
                saveButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        // Obtenir les nouvelles valeurs des taux de change
                        double newFahrenheitToCelsiusRatio = Double.parseDouble(fahrenheitToCelsiusField.getText());
                        double newMilesToKilometersRatio = Double.parseDouble(milesToKilometersField.getText());
                        double newEuroToDollarRatio = Double.parseDouble(euroToDollarField.getText());
                        double newEuroToSterlingRatio = Double.parseDouble(euroToSterlingField.getText());
                        double newEuroToRoubleRatio = Double.parseDouble(euroToRoubleField.getText());

                        // Enregistrer les nouvelles valeurs des taux de change
                        exchangeRates.setExchangeRate(newFahrenheitToCelsiusRatio, newMilesToKilometersRatio, newEuroToDollarRatio, newEuroToSterlingRatio, newEuroToRoubleRatio);

                        // Fermer la fenêtre de modification des taux de change
                        editRatesFrame.dispose();

                        // Mettre à jour les valeurs des champs de texte correspondants dans la fenêtre principale
                        FAHRENHEIT_TO_CELSIUS_RATIO = newFahrenheitToCelsiusRatio;
                        MILES_TO_KILOMETERS_RATIO = newMilesToKilometersRatio;
                        EURO_TO_DOLLAR_RATIO = newEuroToDollarRatio;
                        EURO_TO_STERLING_RATIO = newEuroToSterlingRatio;
                        EURO_TO_ROUBLE_RATIO = newEuroToRoubleRatio;
                    }
                });
                editRatesFrame.add(saveButton, BorderLayout.SOUTH);

                editRatesFrame.setVisible(true);
            }
        });
        buttonPanel.add(editRatesButton);


        add(inputPanel, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new ConversionApp();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Récupération de la valeur d'entrée et de la conversion sélectionnée
        String inputString = inputField.getText();
        int conversionIndex = conversionList.getSelectedIndex();

        try {
            double input = Double.parseDouble(inputString);
            double result;

            // Calcul de la conversion en fonction de l'index de la conversion sélectionnée
            switch (conversionIndex) {
                case 0: // Fahrenheit -> Celsius
                    result = (input - 32) * FAHRENHEIT_TO_CELSIUS_RATIO;
                    break;
                case 1: // Miles -> Kilomètres/heure
                    result = input * MILES_TO_KILOMETERS_RATIO;
                    break;
                case 2: // Euro -> Dollar
                    result = input * EURO_TO_DOLLAR_RATIO;
                    break;
                case 3: // Euro -> Livre sterling
                    result = input * EURO_TO_STERLING_RATIO;
                    break;
                case 4: // Euro -> Rouble
                    result = input * EURO_TO_ROUBLE_RATIO;
                    break;

                default:
                    result = 0.0;
                    break;
            }

            // Affichage du résultat
            resultLabel.setText(inputString + " équivaut à " + String.format("%.2f", result));

        } catch (NumberFormatException ex) {
            // Gestion de l'erreur si la valeur d'entrée n'est pas un nombre
            resultLabel.setText("Erreur : Entrez une valeur numérique valide");
        }
    }
}
