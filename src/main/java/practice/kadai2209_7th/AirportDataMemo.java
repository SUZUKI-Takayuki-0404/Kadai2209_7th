package practice.kadai2209_7th;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class AirportDataMap {

    private Map<String, List<String>> airportAllDataMap;

    public Map<String, List<String>> getAirportAllData(@NotNull Map<String, List<String>> currentAirportAllData, AirportService service) {

        if (currentAirportAllData.isEmpty()) {

            return service.getAllDataMap();

        } else {

            return currentAirportAllData;

        }
    }


    public Map<String, List<String>> getAirportDataMap(AirportService service) {

        if (this.airportAllDataMap == null) {

            this.airportAllDataMap = service.getAllDataMap();

        }

        return this.airportAllDataMap;

    }


    public void putIntoAirportDataMap(String airportCode, List<String> airportNameAndCountry) {

        this.airportAllDataMap.put(airportCode, airportNameAndCountry);

    }


    public boolean removeFromAirportDataMap(String airportCode, List<String> airportNameAndCountry) {

        if (this.airportAllDataMap.get(airportCode) == null) {

            return false;

        } else {

            this.airportAllDataMap.remove(airportCode);

            return true;

        }
    }

}
