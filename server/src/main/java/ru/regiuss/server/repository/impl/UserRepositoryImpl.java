package ru.regiuss.server.repository.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.regiuss.server.model.Role;
import ru.regiuss.server.model.User;
import ru.regiuss.server.repository.custom.UserRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public PageImpl<User> findAll(Pageable pageable, int[] laboratories) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root);
        TypedQuery<User> query = em.createQuery(cr);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        countQuery.select(cb.count(countQuery.from(User.class)));
        long total = em.createQuery(countQuery).getSingleResult();
        return new PageImpl<>(query.getResultList(), pageable, total);
    }

    public static Specification<User> userRoles(Role role) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                if(role != null){
                    SetJoin<?, ?> userRoles = root.joinSet("roles");
                    p = cb.and(p, userRoles.in(role));
                }
                return p;
            }
        };
    }
}
