package com.example.intermove.Repositories.Accomodation;

import com.example.intermove.Entities.Accomodation.Agent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAgentRepository extends CrudRepository<Agent,Integer> {
}
