package com.tribune.demo.vault.db.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "Country", indexes = {
        @Index(name = "idx_country_name_unq", columnList = "name", unique = true)
})
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;


    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false, columnDefinition = "timestamp default current_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss Z")
    private ZonedDateTime createdDate;


    @UpdateTimestamp
    @Column(name = "last_modified_date", nullable = false, columnDefinition = "timestamp default current_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss Z")
    private ZonedDateTime lastModifiedDate;

}
