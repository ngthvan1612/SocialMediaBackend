package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.UserTagFriendPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserTagFriendPostRepository extends JpaRepository<UserTagFriendPost, Integer>, ExtendUserTagFriendPostRepository {
    @Override
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserTagFriendPost u WHERE u.id = :integer AND u.deletedAt is null")
    boolean existsById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM UserTagFriendPost u WHERE u.id = :integer AND u.deletedAt is null")
    Optional<UserTagFriendPost> findById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM UserTagFriendPost u WHERE u.deletedAt is null")
    List<UserTagFriendPost> findAll();


}