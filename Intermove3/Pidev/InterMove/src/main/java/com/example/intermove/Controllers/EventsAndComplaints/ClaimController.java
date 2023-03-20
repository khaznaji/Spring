package com.example.intermove.Controllers.EventsAndComplaints;

import com.example.intermove.Entities.EventsAndComplaints.*;
import com.example.intermove.Services.EventsAndComplaints.ClaimService;
import com.example.intermove.Services.EventsAndComplaints.DuplicateComplainers;
import com.example.intermove.Services.EventsAndComplaints.MostComplainer;
import com.example.intermove.Services.Files.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/Claim")
public class ClaimController {
    @Autowired
    ClaimService reclamationService;
    @Autowired
    FileStorageService fileStorageService ;

    @PostMapping("/reclamation/{id}")

    public Claim addReclamation(@RequestParam("objet") String objet, @RequestParam ("message") String message,
                                @RequestParam ("typeClaim") TypeClaim typeClaim
            , @RequestParam("upload") MultipartFile upload ,@PathVariable int id) throws IOException, URISyntaxException
    {

        Claim claim = new Claim() ;
        claim.setObjet(objet);
        claim.setMessage(message);
        claim.setTypeClaim(typeClaim);

        claim.setImage(fileStorageService.storeFile(upload));
        return reclamationService.addClaim(claim , id);
    }
    //@GetMapping("/claims/status/{status}")
    //public List<Claim> getStatusOfClaim(@PathVariable String status) {
    //   return this.reclamationService.getStatusOfClaim(status);
  // }

    @PutMapping ("/updateComplaintAdmin/{id}")

    public ResponseEntity<Void> UpdateComplaintAdmin(@PathVariable int id, @RequestParam boolean status) {
        reclamationService.updateStatus(id, status);
        return ResponseEntity.ok().build();
    }
    @PutMapping ("/updateComplaint/{id}")
    public Claim UpdateComplaint( @PathVariable Integer id , @RequestParam("objet") String objet, @RequestParam ("message") String message,
                                  @RequestParam ("typeClaim") TypeClaim typeClaim
            , @RequestParam("upload") String upload ) throws IOException, URISyntaxException
    {

        Claim claim = new Claim() ;
        claim.setObjet(objet);
        claim.setMessage(message);
        claim.setTypeClaim(typeClaim);
        claim.setImage(upload);
        return reclamationService.UpdateClaim(claim, id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Claim>>  getAllComplaintsdetails()
    {
        List<Claim> list = reclamationService.getAllComplaints();
        return new ResponseEntity<List<Claim>>(list, HttpStatus.OK);}

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        reclamationService.deleteComplaints(id);
    }

    @GetMapping("/getClaimbyid/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable("id") Integer id)
    {
        Claim ev= reclamationService.getComplaintsById(id);
        return new ResponseEntity<Claim>(ev,HttpStatus.OK);

    }
    @GetMapping("/getStatus/{status}")
    public ResponseEntity<List<Claim>> getClaimsByStatus(@PathVariable boolean status) {
        List<Claim> claims = reclamationService.getClaimsByStatus(status);
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }
    @GetMapping("/mostcomplainer")
    public List<MostComplainer> mostComplainer(){
        return reclamationService.mostComplainer();
    }
    @GetMapping("/duplicatecomplainers")
    public List<DuplicateComplainers> duplicateComplainer(){
        return reclamationService.duplicateComplainers();
    }
    @GetMapping("/room")
    public ModelAndView room() {
        return new ModelAndView("index");
    }
    @PostMapping("/{id}/send-email")
    public ResponseEntity<String> sendEmail(@PathVariable int id) {
        reclamationService.sendEmail(id);
        return ResponseEntity.ok("E-mail envoyé avec succès.");
    }

}
