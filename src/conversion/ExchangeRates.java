package conversion;

public class ExchangeRates {
    private double fahrenheitToCelsiusRatio;;
    private double milesToKilometersRatio;
    private double euroToDollarRatio;
    private double euroToSterlingRatio;
    private double euroToRoubleRatio;
    public ExchangeRates(double fahrenheitToCelsiusRatio, double milesToKilometersRatio, double euroToDollarRatio, double euroToSterlingRatio, double euroToRoubleRatio) {
        this.fahrenheitToCelsiusRatio = fahrenheitToCelsiusRatio;
        this.milesToKilometersRatio = milesToKilometersRatio;
        this.euroToDollarRatio = euroToDollarRatio;
        this.euroToSterlingRatio = euroToSterlingRatio;
        this.euroToRoubleRatio = euroToRoubleRatio;
    }

    public ExchangeRates(){
        this.fahrenheitToCelsiusRatio = 5.0/9.0;
        this.milesToKilometersRatio = 1.60934;
        this.euroToDollarRatio = 1.17;
        this.euroToSterlingRatio = 0.85;
        this.euroToRoubleRatio = 89.74;
    }

    public double getFahrenheitToCelsiusRatio() {
        return fahrenheitToCelsiusRatio;
    }

    public void setFahrenheitToCelsiusRatio(double fahrenheitToCelsiusRatio) {
        this.fahrenheitToCelsiusRatio = fahrenheitToCelsiusRatio;
    }

    public double getMilesToKilometersRatio() {
        return milesToKilometersRatio;
    }

    public void setMilesToKilometersRatio(double milesToKilometersRatio) {
        this.milesToKilometersRatio = milesToKilometersRatio;
    }

    public double getEuroToDollarRatio() {
        return euroToDollarRatio;
    }

    public void setEuroToDollarRatio(double euroToDollarRatio) {
        this.euroToDollarRatio = euroToDollarRatio;
    }

    public double getEuroToSterlingRatio() {
        return euroToSterlingRatio;
    }

    public void setEuroToSterlingRatio(double euroToSterlingRatio) {
        this.euroToSterlingRatio = euroToSterlingRatio;
    }

    public double getEuroToRoubleRatio() {
        return euroToRoubleRatio;
    }

    public void setEuroToRoubleRatio(double euroToRoubleRatio) {
        this.euroToRoubleRatio = euroToRoubleRatio;
    }

    public void setExchangeRate(double fahrenheitToCelsiusRatio, double milesToKilometersRatio, double euroToDollarRatio, double euroToSterlingRatio, double euroToRoubleRatio) {
        this.fahrenheitToCelsiusRatio = fahrenheitToCelsiusRatio;
        this.milesToKilometersRatio = milesToKilometersRatio;
        this.euroToDollarRatio = euroToDollarRatio;
        this.euroToSterlingRatio = euroToSterlingRatio;
        this.euroToRoubleRatio = euroToRoubleRatio;
    }

}
