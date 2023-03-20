package com.example.intermove.Repositories.Accomodation;

import com.example.intermove.Entities.Accomodation.House;
import com.example.intermove.Entities.Accomodation.TypeHouses;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHousesRepository extends CrudRepository<House,Integer> {
    @Query("select h from House h where (:country is null or h.country = :country) " +
            "and (:region is null or h.region = :region)" +
            "and (:available is null or h.available = :available)" +
            "and (:TypeHouses is null or h.TypeHouses = :TypeHouses)" +
            "and (:Loyer is null or h.Loyer <= :Loyer)")
    List<House> findByCountryAndRegion(@Param("country") String country
            ,@Param("region") String region
            ,@Param("available") Boolean available
            ,@Param("TypeHouses") TypeHouses TypeHouses
            ,@Param("Loyer") Integer Loyer);

}
