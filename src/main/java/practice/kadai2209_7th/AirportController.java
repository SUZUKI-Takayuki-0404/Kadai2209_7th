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

    List<String> airportCodeList = new ArrayList<>();

    Map<String, List<String>> yourAirportMap = new HashMap<>();

    private final String notFoundMessage = "not found";

    @Autowired
    private PortNumber portNum;

    @Autowired
    private AirportService service;

    AirportAllData airportAllData = new AirportAllData();


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/airports")
    public Map<String, Object> getAirportList() {

        airportCodeList = service.getAllAirportCode();

        Map<String, Object> airportCodeMap = new LinkedHashMap<>();
        airportCodeMap.put("message : ", "You have airport codes listed below");
        airportCodeMap.put("airport : ", airportCodeList);

        return airportCodeMap;
    }


    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> getAirportMap(
            @RequestParam(value = "airportCode", defaultValue = "NRT") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode) {

        yourAirportMap = airportAllData.getAirportAllData(yourAirportMap, service);

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(notFoundMessage, notFoundMessage));

        Map<String, Object> searchedAirportMap = new LinkedHashMap<>();

        if (airportInfoList.get(0).equals(notFoundMessage)) {

            return ResponseEntity.noContent().build();

        } else {

            searchedAirportMap.put("message : ", "A search completed");
            searchedAirportMap.put("airport : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

            return ResponseEntity.ok(searchedAirportMap);

        }

    }


    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createAirport(
            @RequestParam(value = "airportCode", defaultValue = "NKM") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode,
            @RequestParam(value = "airportName", defaultValue = "NAGOYA") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam(value = "country", defaultValue = "JAPAN") @NotBlank(message = "Country is required field") String country) {

        yourAirportMap = airportAllData.getAirportAllData(yourAirportMap, service);

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(notFoundMessage, notFoundMessage));

        Map<String, Object> createdAirportMap = new LinkedHashMap<>();

        if (airportInfoList.get(0).equals(notFoundMessage)) {

            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));

            createdAirportMap.put("message : ", "Successfully created");
            createdAirportMap.put("airport : ", new AirportEntity(airportCode, airportName, country));

            URI url = UriComponentsBuilder.fromUriString("http://localhost:" + portNum.getPortNum())
                    .path("/create/" + airportCode)
                    .build()
                    .toUri();

            return ResponseEntity.created(url).body(createdAirportMap);

        } else {

//            createdAirportMap.put("message : ", "Already exists as below, Input an unused Airport Code");
//            createdAirportMap.put("airport : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));
//
//            return ResponseEntity.ok(createdAirportMap);

            return ResponseEntity.noContent().build();

        }

    }


    @PatchMapping("/update/{airportCode}")
    public ResponseEntity<Map<String, Object>> updateAirport(
            @PathVariable("airportCode") @NotNull String airportCode,
            @RequestParam("airportName") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam("country") @NotBlank(message = "Country is required field") String country) {

        yourAirportMap = airportAllData.getAirportAllData(yourAirportMap, service);

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(notFoundMessage, notFoundMessage));

        Map<String, Object> updatedAirportMap = new LinkedHashMap<>();

        if (airportInfoList.get(0).equals(notFoundMessage) || airportInfoList.equals(List.of(airportName, country))) {

//            updatedAirportMap.put("message : ", "Input an existing Airport Code in the list of data");
//            updatedAirportMap.put("airport : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

//            return ResponseEntity.ok(updatedAirportMap);

            return ResponseEntity.noContent().build();

        } else {

            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));

            updatedAirportMap.put("message : ", "Successfully created");
            updatedAirportMap.put("before  : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));
            updatedAirportMap.put("after   : ", new AirportEntity(airportCode, airportName, country));

            return ResponseEntity.ok(updatedAirportMap);

        }

    }


    @DeleteMapping("/delete/{airportCode}")
    public ResponseEntity<Map<String, Object>> deleteAirport(
            @PathVariable("airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3")
            String airportCode) {

        yourAirportMap = airportAllData.getAirportAllData(yourAirportMap, service);

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(notFoundMessage, notFoundMessage));

        Map<String, Object> deletedAirportMap = new LinkedHashMap<>();

        if (airportInfoList.get(0).equals(notFoundMessage)) {

//            deletedAirportMap.put("message : ", "Input an existing Airport Code in the list of data");
//            deletedAirportMap.put("airport : ", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));
//
//            return ResponseEntity.ok(deletedAirportMap);
            return ResponseEntity.noContent().build();

        } else {

            yourAirportMap.remove(airportCode);

            deletedAirportMap.put("message : ", "Successfully deleted");

            return ResponseEntity.ok(deletedAirportMap);

        }
    }

}
