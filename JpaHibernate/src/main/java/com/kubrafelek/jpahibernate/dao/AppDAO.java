package com.kubrafelek.jpahibernate.dao;

import com.kubrafelek.jpahibernate.entity.App;

public interface AppDAO {

    void addApp(App application);

   App getApplicationById(int applicationId);

    boolean applicationExists(String name, String owner);

    boolean applicationReallyExists(String name, String owner);

    boolean updateNameAndOwnerById(Integer id, String newName, String newOwner);

    App getApplicationWithTicketsAndReleases(int applicationId);

    App getApplicationWithTicketsAndReleasesV2(int applicationId);

   // AppDTO getApplicationWithTicketsAndReleasesV3(int applicationId);
}
