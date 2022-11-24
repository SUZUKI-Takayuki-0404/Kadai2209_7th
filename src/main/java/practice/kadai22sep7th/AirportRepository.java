package practice.kadai22sep7th;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AirportRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<Map<String, Object>> findByCode(String airportCode) {
        String query = "SELECT * FROM airports WHERE airportCode=?";
        Map<String, Object> foundAirport;
        try {
            foundAirport = jdbcTemplate.queryForMap(query, airportCode);
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            foundAirport = null;
        }
        return Optional.ofNullable(foundAirport);
    }

    public List<String> findAllCode() {
        String sql = "SELECT airportCode FROM airports";
        List<String> foundCodeList = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            foundCodeList.add(rowSet.getString("airportCode"));
        }
        return foundCodeList;
    }

    public List<AirportEntity> findAllData() {
        String sql = "SELECT * FROM airports";
        List<AirportEntity> allDataList = new ArrayList<>();
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            allDataList.add(new AirportEntity(
                    rowSet.getString("airportCode"),
                    rowSet.getString("airportName"),
                    rowSet.getString("country")));
        }
        return allDataList;
    }

}
