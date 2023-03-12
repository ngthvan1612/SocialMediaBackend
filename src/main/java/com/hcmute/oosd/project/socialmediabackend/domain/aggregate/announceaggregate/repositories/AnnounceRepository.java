package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.entities.Announce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnnounceRepository extends JpaRepository<Announce, Integer>, ExtendAnnounceRepository {
    @Override
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Announce u WHERE u.id = :integer AND u.deletedAt is null")
    boolean existsById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Announce u WHERE u.id = :integer AND u.deletedAt is null")
    Optional<Announce> findById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Announce u WHERE u.deletedAt is null")
    List<Announce> findAll();


}