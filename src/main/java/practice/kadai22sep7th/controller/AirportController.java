package practice.kadai22sep7th.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

    @DeleteMapping("/airports/{airportCode}")
    public ResponseEntity<Void> deleteAirport(
            @PathVariable("airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode) {

        service.deleteAirport(airportCode);
        return ResponseEntity.noContent().build();
    }

}
