package practice.kadai2209_7th;

import java.util.List;
import java.util.Map;


public class AirportDataMemo {

    private Map<String, List<String>> airportAllDataMap;

    public Map<String, List<String>> getAirportAllData(AirportService service) {

        if (this.airportAllDataMap == null) {

            this.airportAllDataMap = service.getAllDataMap();

        }
        return this.airportAllDataMap;

    }


    public void putIntoAirportDataMap(String airportCode, List<String> airportNameAndCountry) {

        this.airportAllDataMap.put(airportCode, airportNameAndCountry);

    }


    public void removeFromAirportDataMap(String airportCode) {

        if (this.airportAllDataMap.get(airportCode) != null) {

            this.airportAllDataMap.remove(airportCode);

        }
    }

}
