package practice.kadai22sep7th.controller;

import lombok.Data;
import practice.kadai22sep7th.entity.AirportEntity;

import java.util.List;

@Data
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
