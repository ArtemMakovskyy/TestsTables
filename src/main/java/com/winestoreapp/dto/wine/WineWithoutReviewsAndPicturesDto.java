package com.winestoreapp.dto.wine;

import com.winestoreapp.model.Review;
import com.winestoreapp.model.WineColor;
import com.winestoreapp.model.WineType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class WineWithoutReviewsAndPicturesDto {
        private Long id;
        private String vendorCode;
        private String name;
        private String shortName;
        private BigDecimal averageRatingScore;
        private BigDecimal price;
        private String grape;
        private Boolean decantation;
        private WineType wineType;
        private BigDecimal strengthFrom;
        private BigDecimal strengthTo;
        private WineColor wineColor;
        private String colorDescribing;
        private String taste;
        private String aroma;
        private String gastronomy;
        private String description;
        private String pictureLink;
}