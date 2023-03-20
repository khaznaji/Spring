package com.example.intermove.Controllers.Candidacy;

import com.example.intermove.Entities.CandidatesAndCourses.Tags;
import com.example.intermove.Entities.Offer.Offer;
import com.example.intermove.Services.Candidacy.ITagServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    ITagServices tagServices;

    @PostMapping("/create")
    public ResponseEntity<Tags> createCandidacy(@RequestBody Tags tags) {
        // candidacy.setUser(new User(1));
        Tags createdCandidacy = tagServices.createTag(tags);
        System.out.println(tags);
        return new ResponseEntity<>(tags, HttpStatus.CREATED);
    }



    @PostMapping("/offers/{offerId}/tags")
    public ResponseEntity<?> assignTagsToOffer(@PathVariable Integer offerId, @RequestBody List<Integer> tagIds) {
        Offer offer = tagServices.assignTagsToOffer(offerId, tagIds);
        return ResponseEntity.ok().body(offer);
    }
}