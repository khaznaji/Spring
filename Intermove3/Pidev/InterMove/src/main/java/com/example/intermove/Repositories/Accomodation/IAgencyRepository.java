package com.example.intermove.Repositories.Accomodation;

import com.example.intermove.Entities.Accomodation.Agency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IAgencyRepository extends CrudRepository<Agency,Integer> {

    Agency getById(Integer integer);
}
