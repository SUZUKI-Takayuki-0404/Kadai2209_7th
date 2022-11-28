package practice.kadai22sep7th.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import practice.kadai22sep7th.entity.AirportEntity;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AirportMapper {

    @Select("SELECT * FROM airports")
    List<AirportEntity> findAll();

    @Select("SELECT * FROM airports WHERE airportCode = #{airportCode}")
    Optional<AirportEntity> findById(String airportCode);

    @Insert("INSERT INTO airports (airportCode, airportName, Country) VALUES (#{airportCode}, #{airportName}, #{country})")
    void create(String airportCode, String airportName, String country);

}
