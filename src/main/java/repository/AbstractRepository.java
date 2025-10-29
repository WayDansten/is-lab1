package repository;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class AbstractRepository<T, K> {
    @PersistenceContext(unitName = "persistence-unit")
    protected EntityManager em;

    private Class<T> entityClass;

    protected AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        em.merge(entity);
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

    public Optional<T> getByKey(K key) {
        T entity = em.find(entityClass, key);
        return Optional.ofNullable(entity);
    }

    public void deleteByKey(K key) {
        T entity = this.getByKey(key).orElseThrow(() -> new EntityNotFoundException());
        em.remove(entity);
    }
}
