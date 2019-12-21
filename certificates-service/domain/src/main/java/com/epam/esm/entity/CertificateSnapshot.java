package com.epam.esm.entity;

import lombok.*;

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
@Table(name = "certificates_snapshots")
public class CertificateSnapshot implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "certificate_id", referencedColumnName = "id")
    private GiftCertificate certificate;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private BigDecimal price;

    @EqualsAndHashCode.Exclude
    @Column(name = "creation_date")
    private LocalDate creationDate;

    private @NonNull Integer duration;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "certificates_tags",
            joinColumns = @JoinColumn(name = "certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags = new HashSet<>();

    @NonNull
    private Long userId;

    public CertificateSnapshot(GiftCertificate certificate){
        this.certificate = certificate;
        this.name = certificate.getName();
        this.description = certificate.getDescription();
        this.price = certificate.getPrice();
        this.creationDate = certificate.getCreationDate();
        this.duration = certificate.getDuration();
    }
}