package practice.kadai22sep7th;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class AirportCodeResponse {

    private String message;
    private List<String> airportCodes;

}
