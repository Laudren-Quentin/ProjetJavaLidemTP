package voiture;

public class Car {
    private String marque;
    private String modele;
    private int age;
    private String carburant;
    private String etat;

    public Car(String marque, String modele, int age, String carburant, String etat) {
        this.marque = marque;
        this.modele = modele;
        this.age = age;
        this.carburant = carburant;
        this.etat = etat;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCarburant(String carburant) {
        this.carburant = carburant;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }

    public int getAge() {
        return age;
    }

    public String getCarburant() {
        return carburant;
    }

    public String getEtat() {
        return etat;
    }
}
