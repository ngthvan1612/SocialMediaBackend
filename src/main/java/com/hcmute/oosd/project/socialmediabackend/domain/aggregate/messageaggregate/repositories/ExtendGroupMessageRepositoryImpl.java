package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.messageaggregate.entities.GroupMessage;
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
public class ExtendGroupMessageRepositoryImpl extends ExtendEntityRepositoryBase<GroupMessage> implements ExtendGroupMessageRepository {
    private final String ERROR_INVALID_PARAMETER = "Tham số không hợp lệ";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<GroupMessage> searchGroupMessage(Map<String, String> queries) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<GroupMessage> criteriaQuery = criteriaBuilder.createQuery(GroupMessage.class);
        Root<GroupMessage> groupMessageRoot = criteriaQuery.from(GroupMessage.class);

        return this.dynamicSearchEntity(
                this.entityManager,
                queries,
                criteriaBuilder,
                criteriaQuery,
                groupMessageRoot,
                GroupMessage.class.getDeclaredFields()
        );
    }
}