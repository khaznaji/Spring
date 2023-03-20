package com.example.intermove.Services.Accomodation;

import com.example.intermove.Entities.Accomodation.Agency;
import com.example.intermove.Entities.Accomodation.Agent;
import com.example.intermove.Entities.Accomodation.House;
import com.example.intermove.Entities.Accomodation.TypeHouses;
import com.example.intermove.Repositories.Accomodation.IAgencyRepository;
import com.example.intermove.Repositories.Accomodation.IAgentRepository;
import com.example.intermove.Repositories.Accomodation.IHousesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Slf4j
@Service
public class AccomodationService implements IAccomodationService {
    @Autowired
    IAgencyRepository agencyRepository;
    @Autowired
    IAgentRepository agentRepository;
    @Autowired
    IHousesRepository housesRepository;


    //Agency
    @Override
    public int addAgency(Agency agency) {
        return agencyRepository.save(agency).getId();
    }
    @Override
    public List<Agency> getAllAgencies() {
        return (List<Agency>) agencyRepository.findAll();
    }

    @Override
    public Agency getAgencyById(int id) {
        return agencyRepository.findById(id).orElse(null);
    }

    @Override
    public Agency updateAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    @Override
    public void deleteAgency(int id) {
        agencyRepository.deleteById(id);
    }

    //Agent
    @Transactional
    @Override
    public int addAgent(Agent a, int idAgenc) {
        Agency agency = agencyRepository.getById(idAgenc);
        a.setAgency(agency);
        return agentRepository.save(a).getId();
    }
    @Override
    public List<Agent> getAllAgents() {
        return (List<Agent>) agentRepository.findAll();
    }

    @Override
    public Agent getAgentById(int id) {
        return agentRepository.findById(id).orElse(null);
    }

    @Override
    public Agent updateAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public void deleteAgent(int id) {
        agencyRepository.deleteById(id);
    }

    //House
    @Transactional
    @Override
    public int addHouses(House h, int idAgenc) {
        Agency agency = agencyRepository.getById(idAgenc);
        h.setAgency(agency);
        return housesRepository.save(h).getId();
    }
    @Override
    public List<House> getAllHouses() {
        return (List<House>) housesRepository.findAll();
    }

    @Override
    public House getHouseById(int id) {
        return housesRepository.findById(id).orElse(null);
    }

    @Override
    public House updateHouses(House house) {
        return housesRepository.save(house);
    }

    @Override
    public void deleteHouse(int id) {
        housesRepository.deleteById(id);
    }
    @Override
    public  List<House> findByCountryAndRegion(String country, String region, Boolean available, TypeHouses typeHouses,Integer Loyer) {
        return housesRepository.findByCountryAndRegion(country,region,available,typeHouses,Loyer);
    }
}
