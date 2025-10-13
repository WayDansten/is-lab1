package repository;

import java.util.List;

import entity.LabWork;
import entity.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

@ApplicationScoped
public class LabWorkRepository extends AbstractRepository<LabWork, Integer> {
    @Inject
    public LabWorkRepository() {
        super(LabWork.class);
    }

    public List<LabWork> findGreaterThanAveragePoint(float averagePoint) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LabWork> cq = cb.createQuery(LabWork.class);
        Root<LabWork> root = cq.from(LabWork.class);

        cq.where(cb.greaterThan(root.get("averagePoint"), averagePoint));
        return em.createQuery(cq).getResultList();
    }

    public List<LabWork> findDescriptionThanStartsWith(String prefix) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LabWork> cq = cb.createQuery(LabWork.class);
        Root<LabWork> root = cq.from(LabWork.class);

        cq.where(cb.like(root.get("description"), prefix + "%"));
        return em.createQuery(cq).getResultList();
    }

    public List<LabWork> getByAuthor(String author) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LabWork> cq = cb.createQuery(LabWork.class);
        Root<LabWork> root = cq.from(LabWork.class);
        Join<LabWork, Person> join = root.join("author");

        cq.where(cb.equal(join.get("name"), author));
        return em.createQuery(cq).getResultList();
    }

    public Long countGreaterThanAveragePoint(Float averagePoint) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<LabWork> root = cq.from(LabWork.class);

        cq.select(cb.count(root));
        cq.where(cb.greaterThan(root.get("averagePoint"), averagePoint));

        return em.createQuery(cq).getSingleResult();
    }
}
