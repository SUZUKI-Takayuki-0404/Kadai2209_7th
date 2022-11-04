package practice.kadai2209_7th;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Table(name = "airports")
public class AirportEntity {

    private String airportCode;

    private String airportName;

    private String country;

    public AirportEntity() {
    }

    public AirportEntity(String airportCode, String airportName, String country) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.country = country;
    }

}
