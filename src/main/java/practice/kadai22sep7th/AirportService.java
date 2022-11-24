package practice.kadai22sep7th;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import practice.kadai22sep7th.exceptionhandelers.AirportNotFoundException;
import practice.kadai22sep7th.mapper.AirportMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class AirportService {

    private AirportMapper airportMapper;

    public AirportEntity getAirportEntity(String airportCode) {
        return airportMapper.findById(airportCode).orElseThrow(() -> new AirportNotFoundException(airportCode + " Not Found"));
    }

    public List<String> getAllAirportCodeList() {
        return airportMapper.findAllCode();
    }

    public Map<String, List<String>> getAllDataMap() {
        List<AirportEntity> allDataList = airportMapper.findAll();
        Map<String, List<String>> allDataMap = new HashMap<>();
        allDataList.forEach(airportEntity -> {
            allDataMap.put(airportEntity.getAirportCode(),
                    Arrays.asList(airportEntity.getAirportName(), airportEntity.getCountry()));
        });
        return allDataMap;
    }

}
