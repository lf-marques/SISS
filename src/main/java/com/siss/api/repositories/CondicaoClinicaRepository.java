package com.siss.api.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.siss.api.entities.CondicaoClinica;

public interface CondicaoClinicaRepository extends JpaRepository<CondicaoClinica, Integer> {

}
