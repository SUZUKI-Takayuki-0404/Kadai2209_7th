package practice.kadai22sep7th.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import practice.kadai22sep7th.entity.AirportEntity;
import practice.kadai22sep7th.exceptionhandelers.DuplicateAirportCodeException;
import practice.kadai22sep7th.service.AirportService;

import javax.validation.constraints.Size;
import java.net.URI;
import java.util.Map;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/airports")
public class AirportController {

    private final String NOT_FOUND_MESSAGE = "not found";

    private final AirportService service;

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

    @PostMapping("/create")
    public ResponseEntity<AirportResponse> createAirport(
            @RequestBody @Validated CreateAirportForm createAirportForm, UriComponentsBuilder uriBuilder)
            throws DuplicateAirportCodeException {

        String airportCode = createAirportForm.getAirportCode();
        AirportEntity airportEntity = service.createAirport(airportCode, createAirportForm.getAirportName(), createAirportForm.getCountry());
        URI url = uriBuilder.path("/create/" + airportCode)
                .build()
                .toUri();
        return ResponseEntity.created(url).body(new AirportResponse("Successfully created", airportEntity));
    }

//----Tentative Comment-out----
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
