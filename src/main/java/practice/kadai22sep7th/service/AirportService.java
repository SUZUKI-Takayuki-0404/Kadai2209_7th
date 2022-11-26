package practice.kadai22sep7th.service;

import practice.kadai22sep7th.entity.AirportEntity;

import java.util.List;

public interface AirportService {

    public AirportEntity getAirport(String airportCode);

    public List<AirportEntity> getAllAirports();

}
