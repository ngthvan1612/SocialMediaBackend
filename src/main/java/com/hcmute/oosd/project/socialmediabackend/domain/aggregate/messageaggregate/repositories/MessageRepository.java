package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.useraggregate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Integer>, ExtendMessageRepository {
    @Override
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Message u WHERE u.id = :integer AND u.deletedAt is null")
    boolean existsById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Message u WHERE u.id = :integer AND u.deletedAt is null")
    Optional<Message> findById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Message u WHERE u.deletedAt is null")
    List<Message> findAll();

    @Query("SELECT u FROM Message u WHERE ((u.sender.id = :user1 AND u.receiver.id = :user2) OR (u.sender.id = :user2 AND u.receiver.id = :user1) ) AND u.deletedAt is null ORDER BY u.createdAt")
    List<Message> sendByOneToOne(@Param("user1") Integer user1, @Param("user2") Integer user2);

//    @Query(value = "SELECT DISTINCT u.receiver FROM Message u WHERE u.sender.id = :userId OR u.receiver.id = :userId AND u.deletedAt is null  ",  nativeQuery = true)
//    List<User> getChatFriendsFromUser(@Param("userId") Integer userId);





}