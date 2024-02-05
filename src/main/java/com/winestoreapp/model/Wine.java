package com.winestoreapp.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@SQLDelete(sql = "UPDATE wines SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Getter
@Setter
@Entity
@Table(name = "wines")
@ToString
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "wine_ratings", joinColumns = @JoinColumn(name = "wine_id"))
    @Column(name = "rating")
    private List<Integer> ratings = new ArrayList<>();

    private BigDecimal averageRatingScore;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String grape;

    @Column(nullable = false)
    private Boolean decantation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private WineType wineType;

    @Column(nullable = false)
    private BigDecimal strengthFrom;

    @Column(nullable = false)
    private BigDecimal strengthTo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private WineColor wineColor;

    private String colorDescribing;
    private String taste;
    private String aroma;
    private String gastronomy;
    private String description;

    @Lob
    @Column(name = "picture", columnDefinition = "LONGBLOB")
    private byte[] picture;

    private URL pictureLink;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;
}
