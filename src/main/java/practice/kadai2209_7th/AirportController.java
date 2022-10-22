package practice.kadai2209_7th;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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

    private boolean hasAirportList = false;
    @Autowired
    private PortNumber portNum;

    @Autowired
    private AirportService service;

    @GetMapping("/airports")
    public String getAirportList() {

        if (!hasAirportList) {

            yourAirportMap = service.getAllDataMap();

            hasAirportList = true;
        }

        return "You have the list of airports";
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> getAirports(
            @RequestParam(value = "airportCode"/*, defaultValue = "KIX"*/) @Size(min = 3, max = 3, message = "Number of letters has to be 3")
            String airportCode) {

        if (yourAirportMap.isEmpty()) {

            return ResponseEntity.ok(List.of("At first, go to ", "http://localhost:" + portNum.getPortNum() + "/airports"));
        }

        return ResponseEntity.ok(yourAirportMap.getOrDefault(airportCode, List.of(airportCode, " does not exist")));

    }


    @PostMapping("/create")
    public ResponseEntity<String> createAirport(
            @RequestParam(value = "airportCode", defaultValue = "NKM") @Size(min = 3, max = 3, message = "Number of letters has to be 3") String airportCode,
            @RequestParam(value = "airportName", defaultValue = "NAGOYA") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam(value = "country", defaultValue = "JAPAN") @NotBlank(message = "Country is required field") String country) {

        if (yourAirportMap.isEmpty()) {

            return ResponseEntity.ok("At first, go to " + "http://localhost:" + portNum.getPortNum() + "/airports");
        }

        if (yourAirportMap.get(airportCode) != null) {

            return ResponseEntity.ok(airportCode + " already exists");

        } else {

            yourAirportMap.putIfAbsent(airportCode, Arrays.asList(airportName, country));
        }

        URI url = UriComponentsBuilder.fromUriString("http://localhost:" + portNum.getPortNum())
                .path("/create/" + airportCode)
                .build()
                .toUri();

        return ResponseEntity.created(url).body(airportCode + ", " + airportName + ", " + country + " : successfully created");
    }


    @PatchMapping("/update/{airportCode}")
    public ResponseEntity<Map<String, String>> updateAirport(
            @PathVariable("airportCode") @NotNull String airportCode,
            @RequestParam("airportName") @NotBlank(message = "Airport Name is required field") String airportName,
            @RequestParam("country") @NotBlank(message = "Country is required field") String country) {

        if (yourAirportMap.isEmpty()) {
            return ResponseEntity.ok(Map.of("At first, go to ", "http://localhost:" + portNum.getPortNum() + "/airports"));
        }

        List<String> airportInfo = yourAirportMap.get(airportCode);

        if (airportInfo == null) {

            return ResponseEntity.ok(Map.of(airportCode, "does not exist"));

        } else {

            String airportInfoBefore = String.join(", ", airportInfo);

            yourAirportMap.put(airportCode, Arrays.asList(airportName, country));

            return ResponseEntity.ok(Map.of("Was: " + airportInfoBefore,
                    "IS: " + String.join(", ", yourAirportMap.get(airportCode)) + " Successfully updated"));
        }

    }


    @DeleteMapping("/delete/{airportCode}")
    public ResponseEntity<Map<String, String>> deleteAirport(
            @PathVariable("airportCode") @Size(min = 3, max = 3, message = "Number of letters has to be 3")
            String airportCode) {

        if (yourAirportMap.isEmpty()) {
            return ResponseEntity.ok(Map.of("At first, go to ", "http://localhost:" + portNum.getPortNum() + "/airports in advance"));
        }

        if (yourAirportMap.get(airportCode) == null) {
            return ResponseEntity.ok(Map.of(airportCode, "does not exist"));
        }

        yourAirportMap.remove(airportCode);
        return ResponseEntity.ok(Map.of("Airport Code: ", airportCode + " has been successfully deleted"));
    }
}
