package practice.kadai2209_7th;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;


public class AirportAllData {

    public Map<String, List<String>> getAirportAllData(@NotNull Map<String, List<String>> currentAirportAllData, AirportService service) {

        if (currentAirportAllData.isEmpty()) {

            return service.getAllDataMap();

        } else {

            return currentAirportAllData;
        }
    }

}
