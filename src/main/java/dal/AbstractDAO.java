package dal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractDAO<T> {
    protected final EntityManager em;
    private final Class<T> entityClass;

    public T save(T entity) {
        em.persist(entity);
        return entity;
    }

    public T findByKey(K key) {
        return em.find(entityClass, key);
    }

    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery();
        Root<T> root = cq.from(entityClass)

        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    public T update(T entity) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void delete(T entity) {
        em.remove(entity);
    }
}
