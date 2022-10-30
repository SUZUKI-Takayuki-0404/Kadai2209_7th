package practice.kadai2209_7th;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.net.URI;
import java.util.*;


@Validated
@RestController
@RequestMapping("/airports")
public class AirportController {


    private final String NOT_FOUND_MESSAGE = "not found";

    @Autowired
    private PortNumber portNum;

    @Autowired
    private AirportService service;


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/airports")
    public Map<String, Object> getAirportList() {

        List<String> airportCodeList = service.getAllAirportCodeList();

        Map<String, Object> airportCodeMap = new LinkedHashMap<>();
        airportCodeMap.put("message", "You have airport codes listed here");
        airportCodeMap.put("airport", airportCodeList);

        return airportCodeMap;
    }


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getAirportMap(
            @RequestParam(value = "airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode) {

        AirportAllData airportAllData = new AirportAllData();
        Map<String, List<String>> yourAirportMap = new HashMap<>();
        yourAirportMap = airportAllData.getAirportAllData(yourAirportMap, service);

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));

        Map<String, Object> searchedAirportMap = new LinkedHashMap<>();
        searchedAirportMap.put("message", "A search completed");
        searchedAirportMap.put("airport", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)).toString());

        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(searchedAirportMap);

        } else {

            return ResponseEntity.ok(searchedAirportMap);

        }
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAirport(
            @RequestParam(value = "airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode,
            @RequestParam(value = "airportName") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam(value = "country") @NotBlank(message = "Country is required field") String country,
            UriComponentsBuilder uriBuilder) {

        AirportAllData airportAllData = new AirportAllData();
        Map<String, List<String>> yourAirportMap = new HashMap<>();
        yourAirportMap = airportAllData.getAirportAllData(yourAirportMap, service);

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));

        Map<String, Object> createdAirportMap = new LinkedHashMap<>();

        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {

            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));

            createdAirportMap.put("message", "Successfully created");
            createdAirportMap.put("airport", new AirportEntity(airportCode, airportName, country));

            URI url = uriBuilder
                    .path("/create/" + airportCode)
                    .build()
                    .toUri();

            return ResponseEntity.created(url).body(createdAirportMap);

        } else {

            createdAirportMap.put("message", "Already exists as below, Input an unused Airport Code");
            createdAirportMap.put("airport", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

            return ResponseEntity.status(HttpStatus.CONFLICT).body(createdAirportMap);

        }

    }


    @PatchMapping("/update/{airportCode}")
    public ResponseEntity<Map<String, Object>> updateAirport(
            @PathVariable("airportCode") @NotNull String airportCode,
            @RequestParam("airportName") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam("country") @NotBlank(message = "Country is required field") String country) {

        Map<String, List<String>> yourAirportMap = new HashMap<>();
        AirportAllData airportAllData = new AirportAllData();
        yourAirportMap = airportAllData.getAirportAllData(yourAirportMap, service);

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));

        Map<String, Object> updatedAirportMap = new LinkedHashMap<>();

        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {

            updatedAirportMap.put("message", "Input an existing Airport Code in the list of data");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedAirportMap);

        } else if (airportInfoList.equals(List.of(airportName, country))) {

            updatedAirportMap.put("message", "The same Airport Name and Country as they are now");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(updatedAirportMap);

        } else {

            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));

            updatedAirportMap.put("message", "Successfully updated");
            updatedAirportMap.put("before", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));
            updatedAirportMap.put("after", new AirportEntity(airportCode, airportName, country));

            return ResponseEntity.ok(updatedAirportMap);

        }

    }


    @DeleteMapping("/delete/{airportCode}")
    public ResponseEntity<Map<String, Object>> deleteAirport(
            @PathVariable("airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3")
            String airportCode) {

        Map<String, List<String>> yourAirportMap = new HashMap<>();
        AirportAllData airportAllData = new AirportAllData();
        yourAirportMap = airportAllData.getAirportAllData(yourAirportMap, service);

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));

        Map<String, Object> deletedAirportMap = new LinkedHashMap<>();

        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {

            deletedAirportMap.put("message", "Input an existing Airport Code in the list of data");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletedAirportMap);

        } else {

            yourAirportMap.remove(airportCode);

            return ResponseEntity.noContent().build();

        }
    }

}
