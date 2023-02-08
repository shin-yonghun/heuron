package com.org.heuron.application.service.model.repository;

import com.org.heuron.application.service.model.entity.PatientMt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientMtRepository extends JpaRepository<PatientMt, Long> {
}
