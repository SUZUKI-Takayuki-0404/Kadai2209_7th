package practice.kadai2209_7th;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class AirportResponse {

    private String message;
    private String airportCode;
    private List<String> airportCodes;
    private String airportName;
    private String country;

    private Map<String, List<String>> airports;


    AirportResponse(@NotNull AirportEntity airportEntity) {
        this.airportCode = airportEntity.getAirportCode();
        this.airportName = airportEntity.getAirportName();
        this.country = airportEntity.getCountry();
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public List<String> getAirportCodes() {
        return airportCodes;
    }

    public void setAirportCodes(List<String> airportCodes) {
        this.airportCodes = airportCodes;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Map<String, List<String>> getAirports() {
        return airports;
    }

    public void setAirports(Map<String, List<String>> airports) {
        this.airports = airports;
    }

}
