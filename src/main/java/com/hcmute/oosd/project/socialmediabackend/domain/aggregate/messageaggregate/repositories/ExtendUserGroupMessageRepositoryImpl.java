package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.UserGroupMessage;
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
public class ExtendUserGroupMessageRepositoryImpl extends ExtendEntityRepositoryBase<UserGroupMessage> implements ExtendUserGroupMessageRepository {
    private final String ERROR_INVALID_PARAMETER = "Tham số không hợp lệ";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserGroupMessage> searchUserGroupMessage(Map<String, String> queries) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<UserGroupMessage> criteriaQuery = criteriaBuilder.createQuery(UserGroupMessage.class);
        Root<UserGroupMessage> userGroupMessageRoot = criteriaQuery.from(UserGroupMessage.class);

        return this.dynamicSearchEntity(
                this.entityManager,
                queries,
                criteriaBuilder,
                criteriaQuery,
                userGroupMessageRoot,
                UserGroupMessage.class.getDeclaredFields()
        );
    }
}