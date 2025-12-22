package com.rgarcia.controlvisitas.repository;

import com.rgarcia.controlvisitas.entity.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {

}
