package com.example.intermove.Entities.CandidatesAndCourses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCourse;
    private String Name;
   // private String DomainCourse;
//    @ManyToMany
//    @JsonIgnore
//    List<Tags> tags;

    @OneToMany(mappedBy = "cources")
    List<CourseTag> courseTags;

}