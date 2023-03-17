package voiture;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CarTableModel extends AbstractTableModel {
    private List<Car> cars;
    private String[] columnNames = {"Marque", "Modèle", "Âge", "Carburant", "État"};

    public CarTableModel(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public int getRowCount() {
        return cars.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Car car = cars.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return car.getMarque();
            case 1:
                return car.getModele();
            case 2:
                return car.getAge();
            case 3:
                return car.getCarburant();
            case 4:
                return car.getEtat();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}

