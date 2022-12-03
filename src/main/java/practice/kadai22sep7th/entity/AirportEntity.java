package practice.kadai22sep7th.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AirportEntity {

    private final String airportCode;
    private String airportName;
    private String country;

}
