package practice.kadai22sep7th.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class UpdateAirportForm {

    @Size(min = 3, max = 3, message = "Number of letters has to be 3")
    private String airportCode;

    @NotBlank(message = "Airport Name is required field")
    private String airportName;

    @NotBlank(message = "Country is required field")
    private String country;

}
