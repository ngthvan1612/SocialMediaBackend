package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Integer>, ExtendReactionRepository {
    @Override
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Reaction u WHERE u.id = :integer AND u.deletedAt is null")
    boolean existsById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Reaction u WHERE u.id = :integer AND u.deletedAt is null")
    Optional<Reaction> findById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Reaction u WHERE u.deletedAt is null")
    List<Reaction> findAll();


}