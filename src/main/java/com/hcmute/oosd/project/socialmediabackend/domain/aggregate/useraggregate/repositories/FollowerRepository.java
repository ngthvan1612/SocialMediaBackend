package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, Integer>, ExtendFollowerRepository {
    @Override
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Follower u WHERE u.id = :integer AND u.deletedAt is null")
    boolean existsById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Follower u WHERE u.id = :integer AND u.deletedAt is null")
    Optional<Follower> findById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Follower u WHERE u.deletedAt is null")
    List<Follower> findAll();

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Follower u WHERE u.user.id = :userid AND u.follow.id = :followerid AND u.deletedAt is null")
    boolean existsByUserIdAndFollowerId(@Param("userid") Integer userid, @Param("followerid") Integer followerid);

    @Query("SELECT u FROM Follower u WHERE u.user.id = :userid AND u.follow.id = :followerid")
    Optional<Follower> findByUseridAndFollowerId(@Param("userid") Integer userid, @Param("followerid") Integer followerid);
}