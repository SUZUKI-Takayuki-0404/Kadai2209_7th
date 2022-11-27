package practice.kadai22sep7th.service;

import practice.kadai22sep7th.entity.AirportEntity;
import practice.kadai22sep7th.exceptionhandelers.DuplicateAirportCodeException;

import java.util.List;

public interface AirportService {

    public AirportEntity getAirport(String airportCode);

    public List<AirportEntity> getAllAirports();

    public AirportEntity createAirport(String airportCode, String airportName, String country) throws DuplicateAirportCodeException;

}
