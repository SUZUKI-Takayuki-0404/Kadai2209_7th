package practice.kadai22sep7th;

import java.util.List;

public class GetAirportCodeResponse {

    private String message;
    private List<String> airportCodes;


    public GetAirportCodeResponse(String message, List<String> airportCodes) {
        this.message = message;
        this.airportCodes = airportCodes;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public List<String> getAirportCodes() {
        return airportCodes;
    }

    public void setAirportCodes(List<String> airportCodes) {
        this.airportCodes = airportCodes;
    }

}
