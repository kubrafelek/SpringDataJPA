package com.kubrafelek.jpahibernate.dao.impl;

import com.kubrafelek.jpahibernate.dao.ReleaseDAO;
import com.kubrafelek.jpahibernate.entity.Release;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository
public class ReleaseDAOImpl implements ReleaseDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addRelease(Release release) {
        entityManager.persist(release);
    }

    @Override
    public Release getReleaseById(int releaseId) {
        return entityManager.find(Release.class, releaseId);
    }
}
