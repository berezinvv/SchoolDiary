package com.ssitacademy.berezinvv.schooldiary.repository;

import com.ssitacademy.berezinvv.schooldiary.model.ClassGroup;
import com.ssitacademy.berezinvv.schooldiary.model.Pupil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PupilRepository extends JpaRepository<Pupil, Long> {

    @Query("select p from Pupil p  left join p.classGroups c where c =:classGroup order by p.lastName, p.firstName")
    List<Pupil> findAllPupilByClassGroup(@Param("classGroup") ClassGroup classGroup);
}
