package practice.kadai22sep7th.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import practice.kadai22sep7th.entity.AirportEntity;
import practice.kadai22sep7th.exceptionhandler.DuplicateAirportCodeException;
import practice.kadai22sep7th.service.AirportService;

import javax.validation.constraints.Size;
import java.net.URI;

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
    public ResponseEntity<AirportResponse> getAirport(
            @RequestParam(value = "airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode) {

        return ResponseEntity.ok(new AirportResponse("airport", service.getAirport(airportCode)));
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

    @PatchMapping("/update/{airportCode}")
    public ResponseEntity<AirportResponse> updateAirport(@RequestBody @Validated UpdateAirportForm updateAirportForm) {

        AirportEntity airportEntity = service.updateAirport(
                updateAirportForm.getAirportCode(), updateAirportForm.getAirportName(), updateAirportForm.getCountry());
        return ResponseEntity.ok(new AirportResponse("Successfully updated", airportEntity));
    }

//----Tentative Comment-out----
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
