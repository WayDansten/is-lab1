package repository;

import java.util.List;

import entity.LabWork;
import entity.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class LabWorkRepository extends AbstractRepository<LabWork, Integer> {
    public LabWorkRepository(EntityManager em) {
        super(em, LabWork.class);
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

    public void deleteByAuthor(String authorName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<LabWork> cd = cb.createCriteriaDelete(LabWork.class);
        Root<LabWork> root = cd.from(LabWork.class);
        Join<LabWork, Person> join = root.join("author");
        
        cd.where(cb.equal(join.get("name"), authorName));
        em.createQuery(cd).executeUpdate();
    }
}
