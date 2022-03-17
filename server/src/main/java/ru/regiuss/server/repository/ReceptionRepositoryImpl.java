package ru.regiuss.server.repository;

import ru.regiuss.server.dto.ReceptionsDateDTO;
import ru.regiuss.server.model.Reception;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReceptionRepositoryImpl implements ReceptionRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Reception> findAll(Integer status) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Reception> cq = cb.createQuery(Reception.class);
        Root<Reception> root = cq.from(Reception.class);
        List<Predicate> predicates = new ArrayList<>();
        if(status != null)predicates.add(cb.equal(root.get("status"), status));
        cq.select(root)
                .where(cb.and(predicates.toArray(new Predicate[0])))
                .orderBy(cb.asc(root.get("date")));
                //.groupBy(root.get("date"));
        return em.createQuery(cq).getResultList();
    }
}
