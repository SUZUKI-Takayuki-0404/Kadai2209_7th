package practice.kadai22sep7th;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import practice.kadai22sep7th.exceptionhandelers.AirportNotFoundException;
import practice.kadai22sep7th.mapper.AirportMapper;

import java.util.List;

@AllArgsConstructor
@Service
public class AirportService {

    private final AirportMapper airportMapper;

    public AirportEntity getAirport(String airportCode) {
        return airportMapper.findById(airportCode).orElseThrow(() -> new AirportNotFoundException(airportCode + " Not Found"));
    }

    public List<AirportEntity> getAllAirports() {
        return airportMapper.findAll();
    }

}
