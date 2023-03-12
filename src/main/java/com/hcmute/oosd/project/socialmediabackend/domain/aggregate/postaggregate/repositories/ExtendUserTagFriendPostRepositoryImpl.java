package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.postaggregate.entities.UserTagFriendPost;
import com.hcmute.oosd.project.socialmediabackend.domain.base.ExtendEntityRepositoryBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ExtendUserTagFriendPostRepositoryImpl extends ExtendEntityRepositoryBase<UserTagFriendPost> implements ExtendUserTagFriendPostRepository {
    private final String ERROR_INVALID_PARAMETER = "Tham số không hợp lệ";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserTagFriendPost> searchUserTagFriendPost(Map<String, String> queries) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserTagFriendPost> criteriaQuery = criteriaBuilder.createQuery(UserTagFriendPost.class);
        Root<UserTagFriendPost> userTagFriendPostRoot = criteriaQuery.from(UserTagFriendPost.class);

        return this.dynamicSearchEntity(
                this.entityManager,
                queries,
                criteriaBuilder,
                criteriaQuery,
                userTagFriendPostRoot,
                UserTagFriendPost.class.getDeclaredFields()
        );
    }
}