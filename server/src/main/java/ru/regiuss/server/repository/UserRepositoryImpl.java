package ru.regiuss.server.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.regiuss.server.model.Role;
import ru.regiuss.server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

public class UserRepositoryImpl implements UserRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public PageImpl<User> findAll(Pageable pageable, String sort, String role) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(userRoles(Role.get(role)).toPredicate(root, cr, cb));
        TypedQuery<User> query = em.createQuery(cr);
        query.setFirstResult(pageable.getPageSize() * pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());


        CriteriaQuery<Long> totalCQ = cb.createQuery(Long.class);
        root = totalCQ.from(User.class);
        totalCQ.select(cb.count(root)).where(userRoles(Role.get(role)).toPredicate(root, totalCQ, cb));
        long total = em.createQuery(totalCQ).getSingleResult();
        return new PageImpl<User>(query.getResultList(), pageable, total);
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
