package beans;

public class WeatherData {
    private int id;
    private String city;
    private String country;
    private double temp;
    private double temp_max;
    private double temp_min;
    private double feels;
    private double humidity;
    private double pressure;
    private double wind_speed;
    private double wind_deg;
    private String condition; // Add condition field

    public WeatherData() {}

    public WeatherData(int id, String city, String country, double temp, double temp_max, double temp_min,
                       double feels, double humidity, double pressure, double wind_speed, double wind_deg, String condition) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.temp = temp;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.feels = feels;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.condition = condition; // Initialize condition
    }

    // Getters and Setters for all fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public double getTemp() { return temp; }
    public void setTemp(double temp) { this.temp = temp; }
    public double getTemp_max() { return temp_max; }
    public void setTemp_max(double temp_max) { this.temp_max = temp_max; }
    public double getTemp_min() { return temp_min; }
    public void setTemp_min(double temp_min) { this.temp_min = temp_min; }
    public double getFeels() { return feels; }
    public void setFeels(double feels) { this.feels = feels; }
    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }
    public double getPressure() { return pressure; }
    public void setPressure(double pressure) { this.pressure = pressure; }
    public double getWind_speed() { return wind_speed; }
    public void setWind_speed(double wind_speed) { this.wind_speed = wind_speed; }
    public double getWind_deg() { return wind_deg; }
    public void setWind_deg(double wind_deg) { this.wind_deg = wind_deg; }
    public String getCondition() { return condition; } // Add getter for condition
}
