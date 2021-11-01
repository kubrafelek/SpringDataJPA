package com.kubrafelek.jpahibernate.entity.dto;

public class ApplicationDTO {

    private String name;
    private String owner;
    private Integer lastTicketId;
    private Integer lastReleaseId;

    public ApplicationDTO(String name, String owner, Integer lastTicketId, Integer lastReleaseId) {
        this.name = name;
        this.owner = owner;
        this.lastTicketId = lastTicketId;
        this.lastReleaseId = lastReleaseId;
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

    public Integer getLastTicketId() {
        return lastTicketId;
    }

    public void setLastTicketId(Integer lastTicketId) {
        this.lastTicketId = lastTicketId;
    }

    public Integer getLastReleaseId() {
        return lastReleaseId;
    }

    public void setLastReleaseId(Integer lastReleaseId) {
        this.lastReleaseId = lastReleaseId;
    }
}
