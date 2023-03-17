package voiture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVFileReader {
    private String csvFile;

    public CSVFileReader(String csvFile) {
        this.csvFile = csvFile;
    }

    public List<Car> readCars() {
        List<Car> cars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Car car = new Car(values[0], values[1], Integer.parseInt(values[2]), values[3], values[4]);
                cars.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cars;
    }
}
