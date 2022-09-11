package com.akropoliprishtine.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "daily_job")
@Table(name = "daily_job")
@SQLDelete(sql = "UPDATE daily_job SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class DailyJob extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String link;
}
