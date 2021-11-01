package com.kubrafelek.jpahibernate.dao.impl;

import com.kubrafelek.jpahibernate.dao.AppDAO;
import com.kubrafelek.jpahibernate.entity.App;
import com.kubrafelek.jpahibernate.entity.dto.ApplicationDTO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class AppDAOImpl implements AppDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addApp(App application) {
        entityManager.persist(application);
    }

    @Override
    public App getApplicationById(int applicationId) {
        return entityManager.find(App.class, applicationId);
    }

    @Override
    public List<App> getByNameOwner(String name, String owner) {
        return entityManager.createQuery("FROM App as a WHERE a.name = :a_name and a.owner = :a_owner", App.class)
                .setParameter("a_name", name)
                .setParameter("a_owner", owner)
                .getResultList();
    }

    @Override
    public boolean applicationExists(String name, String owner) {

/*        String a_name_param = "a_name_param";
        String a_owner_param = "a_owner_param";

        // note application is the class name; not the table name;
        // class name is case sensitive; use class field names - column names
        String jpql = "from " + App.class.getSimpleName() + " as a "
                + "WHERE "
                + "a." + Application_.name.getName() + " = :" + a_name_param + " "
                + "and a." + Application_.owner.getName() + " = :" + a_owner_param;

        // from Application as a WHERE a.name = ? and a.owner = ?
        int count = entityManager
                .createQuery(jpql)
                .setParameter(a_name_param, name)
                .setParameter(a_owner_param, owner)
                .getResultList()
                .size();
        return count > 0;*/
        return false;
    }

    @Override
    public boolean applicationReallyExists(String name, String owner) {

/*        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<App> criteriaQuery = cb.createQuery(App.class);

        Root<App> root = criteriaQuery.from(App.class);

        criteriaQuery.select(root).where(
                cb.and(
//                        cb.equal(root.get(App_.name), name),
//                        cb.equal(root.get(App_.owner), owner)
                )
        );

        int count = entityManager.createQuery(criteriaQuery).getResultList().size();

        return count > 0;*/
        return true;
    }

    @Override
    public boolean updateNameAndOwnerById(Integer id, String newName, String newOwner) {
        App app = getApplicationById(id);
        app.setName(newName);
        app.setOwner(newOwner);
        entityManager.flush();
        return true;
    }

    @Override
    public App getApplicationWithTicketsAndReleases(int applicationId) {
       return null;
    }

    @Override
    public App getApplicationWithTicketsAndReleasesV2(int applicationId) {
        return null;
    }

    @Override
    public ApplicationDTO getApplicationWithTicketsAndReleasesV3(int applicationId) {
        return null;
    }
}
