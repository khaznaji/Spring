package com.example.intermove.Repositories.Candidacy;

import com.example.intermove.Entities.CandidatesAndCourses.Courses;
import com.example.intermove.Entities.CandidatesAndCourses.Tags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<Courses, Integer> {
    //Iterable<Courses> findALLByDomainCourse(String domainCourse);
List<Courses> findCoursesByCourseTags(Tags tags);

}
