package practice.kadai22sep7th.mapper;

import org.apache.ibatis.annotations.*;
import practice.kadai22sep7th.AirportEntity;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AirportMapper {

    @Select("SELECT * FROM airports")
    List<AirportEntity> findAll();

    @Select("SELECT * FROM airports WHERE airportCode = #{airportCode}")
    Optional<AirportEntity> findById(String airportCode);

    @Insert("INSERT INTO airports (airportCode, airportName, Country) VALUES (#{airportCode}, #{airportName}, #{Country})")
    void create(String airportCode, String airportName, String Country);

    @Update("UPDATE airports SET airportName = #{airportName}, Country = #{Country} WHERE airportCode = #{airportCode}")
    boolean update(String airportCode, String airportName, String Country);

    @Delete("DELETE FROM airports WHERE airportCode = #{airportCode}")
    boolean deleteById(String airportCode);
}
