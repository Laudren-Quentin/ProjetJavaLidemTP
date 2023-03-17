package voiture;

import voiture.Car;
import voiture.CarTableModel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

public class CarStockApp extends JFrame {
    private List<Car> cars;
    private CarTableModel carTableModel;
    private JTable carTable;

    public CarStockApp() {
        super("Stock de voitures");
        cars = loadCarsFromCSV("src/voiture/cars.csv");
        carTableModel = new CarTableModel(cars);
        carTable = new JTable(carTableModel);
        JScrollPane scrollPane = new JScrollPane(carTable);
        add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        JButton addButton = new JButton("Ajouter");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CarDialog dialog = new CarDialog(CarStockApp.this);
                dialog.setVisible(true);
                if (dialog.getResult() != null) {
                    cars.add(dialog.getResult());
                    carTableModel.fireTableDataChanged();
                }
            }
        });
        JButton editButton = new JButton("Modifier");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carTable.getSelectedRow();
                if (selectedRow >= 0) {
                    CarDialog dialog = new CarDialog(CarStockApp.this, cars.get(selectedRow));
                    dialog.setVisible(true);
                    if (dialog.getResult() != null) {
                        cars.set(selectedRow, dialog.getResult());
                        carTableModel.fireTableDataChanged();
                    }
                } else {
                    JOptionPane.showMessageDialog(CarStockApp.this, "Veuillez sélectionner une voiture à modifier.",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton deleteButton = new JButton("Supprimer");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = carTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int option = JOptionPane.showConfirmDialog(CarStockApp.this,
                            "Êtes-vous sûr de vouloir supprimer cette voiture ?", "Confirmation",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        cars.remove(selectedRow);
                        carTableModel.fireTableDataChanged();
                    }
                } else {
                    JOptionPane.showMessageDialog(CarStockApp.this, "Veuillez sélectionner une voiture à supprimer.",
                            "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton saveButton = new JButton("Enregistrer");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Enregistre dans le csv
                saveCarsToCSV(cars, "src/voiture/cars.csv");
                JOptionPane.showMessageDialog(CarStockApp.this, "Les voitures ont été enregistrées avec succès.",
                        "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        CarStockApp app = new CarStockApp();
        app.setVisible(true);
    }

    private List<Car> loadCarsFromCSV(String fileName) {
        List<Car> cars = new ArrayList<Car>();
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String brand = parts[0];
                    String model = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String fuelType = parts[3];
                    String condition = parts[4];
                    cars.add(new Car(brand, model, age, fuelType, condition));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // Fichier non trouvé, on retourne une liste vide
        }
        return cars;
    }

    private void saveCarsToCSV(List<Car> cars, String fileName) {
        try {
            FileWriter writer = new FileWriter(new File(fileName));
            for (Car car : cars) {
                writer.write(car.getMarque() + ";" + car.getModele() + ";" + car.getAge() + ";" + car.getCarburant()
                        + ";" + car.getEtat() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Impossible d'enregistrer les voitures.", "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private class CarDialog extends JDialog {
        private Car result;
        private JTextField brandField;
        private JTextField modelField;
        private JTextField ageField;
        private JTextField fuelTypeField;
        private JTextField conditionField;

        public CarDialog(Component parent) {
            super(JOptionPane.getFrameForComponent(parent), "Ajouter une voiture", true);
            initComponents();
        }

        public CarDialog(Component parent, Car car) {
            super(JOptionPane.getFrameForComponent(parent), "Modifier une voiture", true);
            initComponents();
            brandField.setText(car.getMarque());
            modelField.setText(car.getModele());
            ageField.setText(Integer.toString(car.getAge()));
            fuelTypeField.setText(car.getCarburant());
            conditionField.setText(car.getEtat());
        }

        public Car getResult() {
            return result;
        }

        private void initComponents() {
            JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panel.add(new JLabel("Marque :"));
            brandField = new JTextField();
            panel.add(brandField);
            panel.add(new JLabel("Modèle :"));
            modelField = new JTextField();
            panel.add(modelField);
            panel.add(new JLabel("Âge :"));
            ageField = new JTextField();
            panel.add(ageField);
            panel.add(new JLabel("Carburant :"));
            fuelTypeField = new JTextField();
            panel.add(fuelTypeField);
            panel.add(new JLabel("État :"));
            conditionField = new JTextField();
            panel.add(conditionField);
            add(panel, BorderLayout.CENTER);
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (brandField.getText().isEmpty() || modelField.getText().isEmpty()
                            || ageField.getText().isEmpty() || fuelTypeField.getText().isEmpty()
                            || conditionField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(CarDialog.this, "Veuillez remplir tous les champs.", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            String brand = brandField.getText();
                            String model = modelField.getText();
                            int age = Integer.parseInt(ageField.getText());
                            String fuelType = fuelTypeField.getText();
                            String condition = conditionField.getText();
                            result = new Car(brand, model, age, fuelType, condition);
                            setVisible(false);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(CarDialog.this, "L'âge doit être un entier.", "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            });
            JButton cancelButton = new JButton("Annuler");
            cancelButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                }
            });
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            add(buttonPanel, BorderLayout.SOUTH);
            pack();
            setLocationRelativeTo(null);
        }
    }
}

