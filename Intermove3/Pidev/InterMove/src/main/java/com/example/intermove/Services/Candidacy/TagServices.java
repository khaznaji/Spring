package com.example.intermove.Services.Candidacy;

import com.example.intermove.Entities.CandidatesAndCourses.Tags;
import com.example.intermove.Entities.Offer.Offer;
import com.example.intermove.Repositories.Candidacy.ITagReporsitory;
import com.example.intermove.Repositories.Offer.IOffreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServices implements ITagServices{
    @Autowired
    ITagReporsitory tagReporsitory;
    @Autowired
    IOffreRepo offreRepo;
    @Override
    public Tags createTag(Tags tags) {
        return tagReporsitory.save(tags);
    }
    @Override
    public Offer assignTagsToOffer(Integer offerId, List<Integer> tagIds) {
        Offer offer = offreRepo.findById(offerId).orElseThrow(() -> new RuntimeException("Offer not found"));
        List<Tags> tags = tagReporsitory.findAllById(tagIds);
        offer.setTags(tags);
        return offreRepo.save(offer);
    }
}
