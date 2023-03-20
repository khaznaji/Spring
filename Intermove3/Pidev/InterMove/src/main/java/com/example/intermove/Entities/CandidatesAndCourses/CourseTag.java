package com.example.intermove.Entities.CandidatesAndCourses;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class CourseTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCT;

    @ManyToOne
    @JoinColumn(name = "idCourse")
    Courses cources;
    @ManyToOne
    @JoinColumn(name = "idTag")
    Tags tags;


}
