package practice.kadai22sep7th;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import practice.kadai22sep7th.exceptionhandelers.AirportNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AirportService {

    @Autowired
    private AirportRepository repository;

    public AirportEntity getAirportEntity(String airport_code) {

        String airportCode;
        String airportName;
        String country;

        if (repository.findByCode(airport_code).isPresent()) {

            Map<String, Object> map = repository.findByCode(airport_code).get();
            airportCode = (String) map.get("airportCode");
            airportName = (String) map.get("airportName");
            country = (String) map.get("country");

            return new AirportEntity(airportCode, airportName, country);

        } else {

            throw new AirportNotFoundException(airport_code + " Not Found");

        }
    }

    public List<String> getAllAirportCodeList() {

        return repository.findAllCode();
    }

    public Map<String, List<String>> getAllDataMap() {

        Map<String, List<String>> allDataMap = new HashMap<>();

        List<AirportEntity> allDataList = repository.findAllData();

        allDataList.forEach(airportEntity -> {
            allDataMap.put(airportEntity.getAirportCode(),
                    Arrays.asList(airportEntity.getAirportName(), airportEntity.getCountry()));
        });

        return allDataMap;
    }

}
