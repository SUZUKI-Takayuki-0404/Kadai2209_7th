package practice.kadai2209_7th;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AirportService {

    @Autowired
    private AirportRepository repository;

    public AirportEntity getAirportEntity(String airport_code) {

        Map<String, Object> map = repository.findByCode(airport_code);

        String airportCode = (String) map.get("airportCode");
        String airportName = (String) map.get("airportName");
        String country = (String) map.get("country");

        return new AirportEntity(airportCode, airportName, country);
    }

    public List<String> getAllAirportCode() {

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
