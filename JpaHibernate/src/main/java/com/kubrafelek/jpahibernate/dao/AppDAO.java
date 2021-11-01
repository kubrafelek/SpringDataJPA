package com.kubrafelek.jpahibernate.dao;

import com.kubrafelek.jpahibernate.entity.App;
import com.kubrafelek.jpahibernate.entity.dto.ApplicationDTO;

import java.util.List;

public interface AppDAO {

    void addApp(App application);

    App getApplicationById(int applicationId);

    List<App> getByNameOwner(String name, String owner);

    boolean applicationExists(String name, String owner);

    boolean applicationReallyExists(String name, String owner);

    boolean updateNameAndOwnerById(Integer id, String newName, String newOwner);

    App getApplicationWithTicketsAndReleases(int applicationId);

    App getApplicationWithTicketsAndReleasesV2(int applicationId);

    ApplicationDTO getApplicationWithTicketsAndReleasesV3(int applicationId);
}
