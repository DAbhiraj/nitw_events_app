package com.project.profile_service.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "favorite_events",
        uniqueConstraints = @UniqueConstraint(columnNames = {"userId", "eventId"}))
public class FavoriteEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long eventId;


    public void setUserId(Long userId) {
        this.userId=userId;
    }

    public void setEventId(Long eventId) {
        this.eventId=eventId;
    }
}
