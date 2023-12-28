package com.example.demospring3.dao.repository;

import com.example.demospring3.dao.entity.SeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeriesRepository extends JpaRepository<SeriesEntity, UUID> {

    List<SeriesEntity> findAllByIsActiveTrueAndIsDeletedFalse();
    Optional<SeriesEntity> findByIdAndIsActiveTrueAndIsDeletedFalse(UUID idSeries);

}
