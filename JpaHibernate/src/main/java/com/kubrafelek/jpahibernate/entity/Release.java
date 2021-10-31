package com.kubrafelek.jpahibernate.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "apprelease")
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private LocalDateTime releaseDateTime;

    @ManyToMany
    @JoinTable(name = "apprelease_application",
            joinColumns = @JoinColumn(name = "release_fk"),
            inverseJoinColumns = @JoinColumn(name = "application_fk")
    )
    private Set<App> deployedApplications;

    //Constructors
    public Release() {
        super();
    }

    public Release(String name, LocalDateTime releaseDateTime) {
        super();
        this.name = name;
        this.releaseDateTime = releaseDateTime;
    }

    public Release(String name, LocalDateTime releaseDateTime, Set<App> deployedApplications) {
        super();
        this.name = name;
        this.releaseDateTime = releaseDateTime;
        this.deployedApplications = deployedApplications;
    }

    //Getter & Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getReleaseDateTime() {
        return releaseDateTime;
    }

    public void setReleaseDateTime(LocalDateTime releaseDateTime) {
        this.releaseDateTime = releaseDateTime;
    }

    public Set<App> getDeployedApplications() {
        return deployedApplications;
    }

    public void setDeployedApplications(Set<App> deployedApplications) {
        this.deployedApplications = deployedApplications;
    }
}
