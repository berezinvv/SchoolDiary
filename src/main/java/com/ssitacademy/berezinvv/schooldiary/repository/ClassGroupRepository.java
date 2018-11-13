package com.ssitacademy.berezinvv.schooldiary.repository;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import com.ssitacademy.berezinvv.schooldiary.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ClassGroupRepository extends JpaRepository<ClassGroup, Long> {

    @Query("select c from ClassGroup c  INNER JOIN c.pupils p where p =:pupil")
    List<ClassGroup> findAllClassGroupByPupil(@Param("pupil") Pupil pupil);

    @Query("select c from ClassGroup c where c.school =:school")
    List<ClassGroup> findAllBySchool(@Param("school") School school);
}
