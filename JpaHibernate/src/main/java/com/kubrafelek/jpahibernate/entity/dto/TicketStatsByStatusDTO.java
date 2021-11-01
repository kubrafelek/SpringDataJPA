package com.kubrafelek.jpahibernate.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketStatsByStatusDTO {

    private String status;

    private Long count;

    private LocalDate minCreateDateTime;

    private LocalDate maxCreateDateTime;

    public TicketStatsByStatusDTO(String status, Long count, LocalDate minCreateDateTime, LocalDate maxCreateDateTime) {
        super();
        this.status = status;
        this.count = count;
        this.minCreateDateTime = minCreateDateTime;
        this.maxCreateDateTime = maxCreateDateTime;
    }

    public String getStatus() {
        return status;
    }

    public Long getCount() {
        return count;
    }

    public LocalDate getMinCreateDateTime() {
        return minCreateDateTime;
    }

    public LocalDate getMaxCreateDateTime() {
        return maxCreateDateTime;
    }

    @Override
    public String toString() {
        return "TicketStatsByStatusDTO{" +
                "status='" + status + '\'' +
                ", count=" + count +
                ", minCreateDateTime=" + minCreateDateTime +
                ", maxCreateDateTime=" + maxCreateDateTime +
                '}';
    }
}
