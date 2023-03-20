package com.example.intermove.Services.Candidacy;

import com.example.intermove.Entities.CandidatesAndCourses.Courses;
import com.example.intermove.Entities.CandidatesAndCourses.Tags;

import java.util.List;

public interface ICourseServices {
    Courses addCourse(Courses courses);
    Iterable<Courses> listAllcourses();


    Courses getCourseById(Integer id);
    Courses updateCourse(Courses courses , Integer idC);
    void deleteCourse(Integer id);

    List<Courses> coursesbytags(Tags tags);
}
