package com.example.intermove.Services.Candidacy;

import com.example.intermove.Entities.CandidatesAndCourses.Tags;
import com.example.intermove.Entities.Offer.Offer;

import java.util.List;

public interface ITagServices {
    Tags createTag(Tags tags);

    public Offer assignTagsToOffer(Integer offerId, List<Integer> tagIds);
}
