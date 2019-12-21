package com.epam.esm.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "certificates")
public class GiftCertificate implements Serializable {
    @EqualsAndHashCode.Exclude
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private BigDecimal price;

    @EqualsAndHashCode.Exclude
    @Column(name = "creation_date")
    private LocalDate creationDate;

    @EqualsAndHashCode.Exclude
    @Column(name = "modification_date")
    private LocalDate modificationDate;

    private @NonNull Integer duration;

    @Fetch(FetchMode.SUBSELECT)
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL, CascadeType.MERGE})
    @JoinTable(
            name = "certificates_tags",
            joinColumns = @JoinColumn(name = "certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();
}
