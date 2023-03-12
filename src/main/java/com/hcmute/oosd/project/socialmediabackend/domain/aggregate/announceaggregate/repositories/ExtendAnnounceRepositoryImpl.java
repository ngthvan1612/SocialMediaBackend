package com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.repositories;

import com.hcmute.oosd.project.socialmediabackend.domain.aggregate.announceaggregate.entities.Announce;
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
public class ExtendAnnounceRepositoryImpl extends ExtendEntityRepositoryBase<Announce> implements ExtendAnnounceRepository {
    private final String ERROR_INVALID_PARAMETER = "Tham số không hợp lệ";
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Announce> searchAnnounce(Map<String, String> queries) {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Announce> criteriaQuery = criteriaBuilder.createQuery(Announce.class);
        Root<Announce> announceRoot = criteriaQuery.from(Announce.class);

        return this.dynamicSearchEntity(
                this.entityManager,
                queries,
                criteriaBuilder,
                criteriaQuery,
                announceRoot,
                Announce.class.getDeclaredFields()
        );
    }
}