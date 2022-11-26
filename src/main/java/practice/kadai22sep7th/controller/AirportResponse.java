package practice.kadai22sep7th.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import practice.kadai22sep7th.entity.AirportEntity;

@AllArgsConstructor
@Data
public class AirportResponse {

    private String message;
    private AirportEntity airportEntity;

}
