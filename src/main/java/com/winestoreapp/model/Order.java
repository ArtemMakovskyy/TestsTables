package com.winestoreapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "order", fetch = FetchType.EAGER)
    @JoinColumn(name = "shopping_card_id")
    private ShoppingCard shoppingCard;

    @OneToOne(mappedBy = "order", fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_information_id")
    private OrderDeliveryInformation deliveryInformation;

    private LocalDate registrationTime;

    private LocalDate completedTime;

    @Enumerated(EnumType.STRING)
    private OrderPaymentStatus paymentStatus;
}
