package ru.regiuss.server.repository.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.regiuss.server.model.Reception;
import ru.regiuss.server.model.User;
import ru.regiuss.server.repository.custom.ReceptionRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ReceptionRepositoryImpl implements ReceptionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Reception> findAll(Pageable pageable, int[] laboratories, Long afterDate, Integer status, Long beforeDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Reception> cq = cb.createQuery(Reception.class);
        Root<Reception> root = cq.from(Reception.class);
        List<Predicate> predicates = new ArrayList<>();
        if(status != null)predicates.add(cb.equal(root.get("status"), status));
        if(afterDate != null)predicates.add(cb.greaterThanOrEqualTo(root.get("date"), Instant.ofEpochSecond(afterDate)));
        if(beforeDate != null)predicates.add(cb.lessThanOrEqualTo(root.get("date"), Instant.ofEpochSecond(beforeDate)));
        cq.select(root)
                .where(cb.and(predicates.toArray(new Predicate[0])))
                .orderBy(cb.asc(root.get("date")));
        TypedQuery<Reception> query = em.createQuery(cq);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(Reception.class))).where(cb.and(predicates.toArray(new Predicate[0])));
        long total = em.createQuery(countQuery).getSingleResult();
        System.out.println("TOTAL: " + total);
        System.out.println("PAGE: " + query.getResultList());
        return new PageImpl<>(query.getResultList(), pageable, total);
    }
}
