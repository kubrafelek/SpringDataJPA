package com.kubrafelek.jpahibernate.dao.impl;

import com.kubrafelek.jpahibernate.dao.AppDAO;
import com.kubrafelek.jpahibernate.entity.App;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public boolean applicationExists(String name, String owner) {
        return false;
    }

    @Override
    public boolean applicationReallyExists(String name, String owner) {
        return false;
    }

    @Override
    public boolean updateNameAndOwnerById(Integer id, String newName, String newOwner) {
        return false;
    }

    @Override
    public App getApplicationWithTicketsAndReleases(int applicationId) {
        return null;
    }

    @Override
    public App getApplicationWithTicketsAndReleasesV2(int applicationId) {
        return null;
    }
}
