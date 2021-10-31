package com.kubrafelek.jpahibernate.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 2000)
    private String description;

    @Column(name = "app_name", nullable = false)
    private String name;

    private String owner;
    /*--
        @OneToMany(cascade = CascadeType.PERSIST)
        @JoinTable(
                name = "ticket_application",
                joinColumns =  @JoinColumn(name="application_fk"),
                inverseJoinColumns = @JoinColumn(name = "ticket_fk")
        )
        */

    @OneToMany(mappedBy = "application")
    public List<Ticket> tickets;

    // On the target side, we only have to provide the name , which maps the relationship
    @ManyToMany(mappedBy = "deployedApplications")
    private Set<Release> releasesToDeploy;

    public App(String description, String name, String owner) {
        super();
        this.description = description;
        this.name = name;
        this.owner = owner;
    }

    public App(String description, String name, String owner, List<Ticket> tickets,
                       Set<Release> releasesToDeploy) {
        super();
        this.description = description;
        this.name = name;
        this.owner = owner;
        this.tickets = tickets;
        this.releasesToDeploy = releasesToDeploy;
    }

    public App() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<Release> getReleasesToDeploy() {
        return releasesToDeploy;
    }

    public void setReleasesToDeploy(Set<Release> releasesToDeploy) {
        this.releasesToDeploy = releasesToDeploy;
    }

}
