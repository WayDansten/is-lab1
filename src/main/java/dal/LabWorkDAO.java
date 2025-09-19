package dal;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import objects.LabWork;

@RequiredArgsConstructor
public class LabWorkDAO extends AbstractDAO<LabWork> {
    public List<LabWork> findGreaterThanAveragePoint(float averagePoint) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LabWork> cq = cb.createQuery();
        Root<LabWork> root = cq.from(LabWork.class);

        cq.where(cb.greaterThan(root.get("averagePoint"), averagePoint));
        return em.createQuery(cq).getResultList();
    }

    public List<LabWork> findDescriptionThanStartsWith(String prefix) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<LabWork> cq = cb.createQuery();
        Root<LabWork> root = cq.from(LabWork.class);

        cq.where(cb.like(root.get("description"), prefix + "%"));
        return em.createQuery(cq).getResultList();
    }

    public void deleteByAuthor(String authorName) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaDelete<LabWork> cd = cb.createCriteriaDelete(LabWork.class);
            Root<LabWork> root = cd.from(LabWork.class);
            Join<LabWork, Person> join = root.join("author");
            
            cd.where(cb.equal(join.get("name"), authorName));
            em.createQuery(cd).executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
