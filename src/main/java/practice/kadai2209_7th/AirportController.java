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
    private AirportService service;


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/airports")
    public GetAirportCodeResponse getAirportList() {

        return new GetAirportCodeResponse("You have airport codes listed here", service.getAllAirportCodeList());
    }


    @GetMapping("/search")
    public ResponseEntity<Map<String, AirportEntity>> getAirportMap(
            @RequestParam(value = "airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode) {

        //Body of ResponseEntity
        Map<String, AirportEntity> searchedAirportMap = new HashMap<>();

        Map<String, List<String>> yourAirportMap = service.getAllDataMap();

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));

        searchedAirportMap.put("airport", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(searchedAirportMap);

        } else {

            return ResponseEntity.ok(searchedAirportMap);

        }
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String, AirportEntity>> createAirport(
            @RequestParam(value = "airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode,
            @RequestParam(value = "airportName") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam(value = "country") @NotBlank(message = "Country is required field") String country,
            UriComponentsBuilder uriBuilder) {

        //Body of ResponseEntity
        Map<String, AirportEntity> createdAirportMap = new HashMap<>();

        Map<String, List<String>> yourAirportMap = service.getAllDataMap();

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));

        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {

            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));
            createdAirportMap.put("airport", new AirportEntity(airportCode, airportName, country));

            URI url = uriBuilder
                    .path("/create/" + airportCode)
                    .build()
                    .toUri();

            return ResponseEntity.created(url).body(createdAirportMap);

        } else {

            createdAirportMap.put("airport", new AirportEntity(airportCode + "  ** Already exists **",
                    airportInfoList.get(0), airportInfoList.get(1)));

            return ResponseEntity.status(HttpStatus.CONFLICT).body(createdAirportMap);

        }

    }


    @PatchMapping("/update/{airportCode}")
    public ResponseEntity<Map<String, AirportEntity>> updateAirport(
            @PathVariable("airportCode") @NotNull String airportCode,
            @RequestParam("airportName") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam("country") @NotBlank(message = "Country is required field") String country) {

        //Body of ResponseEntity
        Map<String, AirportEntity> updatedAirportMap = new LinkedHashMap<>();

        Map<String, List<String>> yourAirportMap = service.getAllDataMap();

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));

        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {

            updatedAirportMap.put("airport", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedAirportMap);

        } else if (airportInfoList.equals(List.of(airportName, country))) {

            updatedAirportMap.put("airport", new AirportEntity(airportCode,
                    airportInfoList.get(0) + "  ** The same Airport Name **",
                    airportInfoList.get(1) + "  ** The same Country **"));

            return ResponseEntity.status(HttpStatus.CONFLICT).body(updatedAirportMap);

        } else {

            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));

            updatedAirportMap.put("before", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));
            updatedAirportMap.put("after", new AirportEntity(airportCode, airportName, country));

            return ResponseEntity.ok(updatedAirportMap);

        }

    }


    @DeleteMapping("/delete/{airportCode}")
    public ResponseEntity<Map<String, AirportEntity>> deleteAirport(
            @PathVariable("airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3")
            String airportCode) {

        //Body of ResponseEntity
        Map<String, AirportEntity> deletedAirportMap = new HashMap<>();

        Map<String, List<String>> yourAirportMap = service.getAllDataMap();

        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));

        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {

            deletedAirportMap.put("airport", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletedAirportMap);

        } else {

            yourAirportMap.remove(airportCode);

            return ResponseEntity.noContent().build();

        }
    }

}
