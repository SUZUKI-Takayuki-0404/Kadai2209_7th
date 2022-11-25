package practice.kadai22sep7th;

import lombok.AllArgsConstructor;
//import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.util.UriComponentsBuilder;

//import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
//import java.net.URI;
import java.util.*;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/airports")
public class AirportController {

    private final String NOT_FOUND_MESSAGE = "not found";

    private AirportService service;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/airports")
    public AirportCodeResponse getAirportList() {
        return new AirportCodeResponse("You have airport codes listed here", service.getAllAirports());
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, AirportEntity>> getAirport(
            @RequestParam(value = "airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode) {

        Map<String, AirportEntity> searchedAirport = Map.of("airport", service.getAirport(airportCode));
        return ResponseEntity.ok(searchedAirport);
    }

//----Tentative Comment-out----

//    @PostMapping("/create")
//    public ResponseEntity<Map<String, AirportEntity>> createAirport(
//            @RequestParam(value = "airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode,
//            @RequestParam(value = "airportName") @NotBlank(message = "Airport Name is required field") String airportName,
//            @RequestParam(value = "country") @NotBlank(message = "Country is required field") String country,
//            UriComponentsBuilder uriBuilder) {
//
//        Map<String, AirportEntity> createdAirportMap = new HashMap<>();
//        Map<String, List<String>> yourAirportMap = service.getAllDataMap();
//        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));
//
//        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {
//            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));
//            createdAirportMap.put("airport", new AirportEntity(airportCode, airportName, country));
//
//            URI url = uriBuilder
//                    .path("/create/" + airportCode)
//                    .build()
//                    .toUri();
//
//            return ResponseEntity.created(url).body(createdAirportMap);
//        } else {
//            createdAirportMap.put("airport", new AirportEntity(airportCode + "  ** Already exists **",
//                    airportInfoList.get(0), airportInfoList.get(1)));
//
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(createdAirportMap);
//        }
//    }
//
//    @PatchMapping("/update/{airportCode}")
//    public ResponseEntity<Map<String, AirportEntity>> updateAirport(
//            @PathVariable("airportCode") @NotNull String airportCode,
//            @RequestParam("airportName") @NotBlank(message = "Airport Name is required field") String airportName,
//            @RequestParam("country") @NotBlank(message = "Country is required field") String country) {
//
//        Map<String, AirportEntity> updatedAirportMap = new LinkedHashMap<>();
//        Map<String, List<String>> yourAirportMap = service.getAllDataMap();
//        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));
//
//        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {
//            updatedAirportMap.put("airport", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(updatedAirportMap);
//        } else if (airportInfoList.equals(List.of(airportName, country))) {
//            updatedAirportMap.put("airport", new AirportEntity(airportCode,
//                    airportInfoList.get(0) + "  ** The same Airport Name **",
//                    airportInfoList.get(1) + "  ** The same Country **"));
//
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(updatedAirportMap);
//        } else {
//            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));
//            updatedAirportMap.put("before", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));
//            updatedAirportMap.put("after", new AirportEntity(airportCode, airportName, country));
//
//            return ResponseEntity.ok(updatedAirportMap);
//        }
//    }
//
//    @DeleteMapping("/delete/{airportCode}")
//    public ResponseEntity<Map<String, AirportEntity>> deleteAirport(
//            @PathVariable("airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3")
//            String airportCode) {
//
//        Map<String, AirportEntity> deletedAirportMap = new HashMap<>();
//        Map<String, List<String>> yourAirportMap = service.getAllDataMap();
//        List<String> airportInfoList = yourAirportMap.getOrDefault(airportCode, List.of(NOT_FOUND_MESSAGE, NOT_FOUND_MESSAGE));
//
//        if (airportInfoList.get(0).equals(NOT_FOUND_MESSAGE)) {
//            deletedAirportMap.put("airport", new AirportEntity(airportCode, airportInfoList.get(0), airportInfoList.get(1)));
//
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(deletedAirportMap);
//        } else {
//            yourAirportMap.remove(airportCode);
//
//            return ResponseEntity.noContent().build();
//        }
//    }

}
