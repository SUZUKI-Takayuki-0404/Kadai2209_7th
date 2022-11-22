package practice.kadai22sep7th;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@Data
public class GetAirportCodeResponse {

    private String message;
    private List<String> airportCodes;

}
