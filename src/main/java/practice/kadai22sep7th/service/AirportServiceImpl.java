package practice.kadai22sep7th.service;

import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import practice.kadai22sep7th.entity.AirportEntity;
import practice.kadai22sep7th.exceptionhandelers.AirportNotFoundException;
import practice.kadai22sep7th.exceptionhandelers.DuplicateAirportCodeException;
import practice.kadai22sep7th.mapper.AirportMapper;

import java.util.List;

@AllArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportMapper airportMapper;

    @Override
    public AirportEntity getAirport(String airportCode) {
        return airportMapper.findById(airportCode).orElseThrow(() -> new AirportNotFoundException(airportCode + " Not Found"));
    }

    @Override
    public List<AirportEntity> getAllAirports() {
        return airportMapper.findAll();
    }

    @Override
    public AirportEntity createAirport(String airportCode, String airportName, String country) throws DuplicateAirportCodeException {
        try {
            airportMapper.create(airportCode, airportName, country);
        } catch (DuplicateKeyException e) {
            throw new DuplicateAirportCodeException("Airport code duplication must be avoided");
        }
        return new AirportEntity(airportCode, airportName, country);
    }

}
