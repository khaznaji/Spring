package com.example.intermove.Controllers.Accomodation;

import com.example.intermove.Entities.Accomodation.Agency;
import com.example.intermove.Services.Accomodation.IAccomodationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Agencies")
public class AgencyController {
    @Autowired
    IAccomodationService accomodationService;

    @GetMapping("/AllAgencies")
    @ResponseBody
    public List<Agency> getAllAgencies(){
        return accomodationService.getAllAgencies();
    }
    @PostMapping("/addAgency")
    @ResponseBody
    public int addAgency(@RequestBody Agency agency){

        return accomodationService.addAgency(agency);
    }



    @PutMapping("/updateAgency")
    @ResponseBody
    public Agency updateAgency(@RequestBody Agency agency){
        return accomodationService.updateAgency(agency);
    }


    @DeleteMapping("/deleteAgency/{id}")
    @ResponseBody
    public void deleteAgency(@PathVariable("id")int id){
        accomodationService.deleteAgency(id);
    }

    @GetMapping("/getAgencyById/{id}")
    @ResponseBody
    public Agency getAgencyById(@PathVariable("id")int id){
        return accomodationService.getAgencyById(id);
    }


}
