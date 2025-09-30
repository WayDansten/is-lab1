package repository;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AbstractRepository<T, K> {
    protected final EntityManager em;
    private final Class<T> entityClass;

    public void save(T entity) {
        em.persist(entity);
    }

    public List<T> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);

        cq.select(root);
        return em.createQuery(cq).getResultList();
    }

    public void update(T entity) {
        em.merge(entity);
    }

    public void delete(T entity) {
        em.remove(entity);
    }

    public T getByKey(K key) {
        return em.find(entityClass, key);
    }

    public void deleteByKey(K key) {
        T entity = this.getByKey(key);
        em.remove(entity);
    }
}
