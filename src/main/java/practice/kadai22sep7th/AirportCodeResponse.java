package practice.kadai22sep7th;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AirportCodeResponse {

    private String message;
    private List<String> airportCodes;

    public AirportCodeResponse(String message, List<AirportEntity> allAirports) {
        this.message = message;
        this.airportCodes = allAirports.stream()
                .map(airportEntity -> airportEntity.getAirportCode())
                .toList();
    }
}
