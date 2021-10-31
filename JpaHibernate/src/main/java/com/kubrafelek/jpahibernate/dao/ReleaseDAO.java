package com.kubrafelek.jpahibernate.dao;

import com.kubrafelek.jpahibernate.entity.Release;

public interface ReleaseDAO {

    void addRelease(Release release);

    Release getReleaseById(int releaseId);
}
