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
    Map<String, List<String>> yourAirportMap = new HashMap<>();

    private final String notFoundMessage = "not found";

    private boolean hasAirportList = false;

    @Autowired
    private PortNumber portNum;

    @Autowired
    private AirportService service;

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @GetMapping("/airports")
    public String getAirportList() {

        if (!hasAirportList) {

            yourAirportMap = service.getAllDataMap();

            hasAirportList = true;
        }

        return "You have the list of airports";
    }


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getAirportMap(
            @RequestParam(value = "airportCode", defaultValue = "NRT") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode) {

        List<String> airportInfoList;

        Map<String, Object> searchedAirportMap = new LinkedHashMap<>();

        if (yourAirportMap.isEmpty()) {

            searchedAirportMap.put("message : ", "At first, go to http://localhost:" + portNum.getPortNum() + "/airports");

            return ResponseEntity.ok(searchedAirportMap);
        }

        airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(notFoundMessage, notFoundMessage));

        searchedAirportMap.put("message : ", "A search completed");
        searchedAirportMap.put("airport : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

        return ResponseEntity.ok(searchedAirportMap);

    }


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAirport(
            @RequestParam(value = "airportCode", defaultValue = "NKM") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode,
            @RequestParam(value = "airportName", defaultValue = "NAGOYA") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam(value = "country", defaultValue = "JAPAN") @NotBlank(message = "Country is required field") String country) {

        List<String> airportInfoList;

        Map<String, Object> createdAirportMap = new LinkedHashMap<>();

        if (yourAirportMap.isEmpty()) {

            createdAirportMap.put("message : ", "At first, go to http://localhost:" + portNum.getPortNum() + "/airports");

            return ResponseEntity.ok(createdAirportMap);
        }

        airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(notFoundMessage, notFoundMessage));

        if (airportInfoList.get(0).equals(notFoundMessage)) {

            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));

            createdAirportMap.put("message : ", "Successfully created");
            createdAirportMap.put("airport : ", new AirportEntity(airportCode, airportName, country));

            URI url = UriComponentsBuilder.fromUriString("http://localhost:" + portNum.getPortNum())
                    .path("/create/" + airportCode)
                    .build()
                    .toUri();

        } else {

            createdAirportMap.put("message : ", "Already exists as below, Input an unused Airport Code");
            createdAirportMap.put("airport : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

        }

        return ResponseEntity.ok(createdAirportMap);
    }


    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping("/update/{airportCode}")
    public ResponseEntity<Map<String, Object>> updateAirport(
            @PathVariable("airportCode") @NotNull String airportCode,
            @RequestParam("airportName") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam("country") @NotBlank(message = "Country is required field") String country) {

        List<String> airportInfoList;

        Map<String, Object> updatedAirportMap = new LinkedHashMap<>();

        if (yourAirportMap.isEmpty()) {

            updatedAirportMap.put("message : ", "At first, go to http://localhost:" + portNum.getPortNum() + "/airports");

            return ResponseEntity.ok(updatedAirportMap);

        }

        airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(notFoundMessage, notFoundMessage));

        if (airportInfoList.get(0).equals(notFoundMessage)) {

            updatedAirportMap.put("message : ", "Input an existing Airport Code in the list of data");
            updatedAirportMap.put("airport : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

        } else {

            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));

            updatedAirportMap.put("message : ", "Successfully created");
            updatedAirportMap.put("before  : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));
            updatedAirportMap.put("after   : ", new AirportEntity(airportCode, airportName, country));

        }

        return ResponseEntity.ok(updatedAirportMap);

    }


    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{airportCode}")
    public ResponseEntity<Map<String, Object>> deleteAirport(
            @PathVariable("airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3")
            String airportCode) {

        List<String> airportInfoList;

        Map<String, Object> deletedAirportMap = new LinkedHashMap<>();

        if (yourAirportMap.isEmpty()) {

            deletedAirportMap.put("message : ", "At first, go to http://localhost:" + portNum.getPortNum() + "/airports");

            return ResponseEntity.ok(deletedAirportMap);

        }

        airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(notFoundMessage, notFoundMessage));

        if (airportInfoList.get(0).equals(notFoundMessage)) {

            deletedAirportMap.put("message : ", "Input an existing Airport Code in the list of data");
            deletedAirportMap.put("airport : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

        } else {

            yourAirportMap.remove(airportCode);

            deletedAirportMap.put("message : ", "Successfully deleted");

        }

        return ResponseEntity.ok(deletedAirportMap);

    }
}
