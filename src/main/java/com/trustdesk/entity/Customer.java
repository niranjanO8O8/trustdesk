package com.trustdesk.entity;

import com.trustdesk.enums.CustomerTier;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.AnyDiscriminatorImplicitValues;

import java.security.PrivateKey;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

/**
 *{
 *     "customer_id": "cus_1001",
 *     "name": "Aisha Rao",
 *     "email": "aisha.rao@example.com",
 *     "tier": "gold",
 *     "country": "IN",
 *     "created_at": "2024-05-14",
 *     "verified": true,
 *     "tags": ["loyal_customer", "high_lifetime_value"]
 *   }
 *
 **/

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customer_id")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CustomerTier tier;

    @Column(nullable = false)
    private String country;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column()
    private boolean verified;

    @ElementCollection
    @CollectionTable(
            name = "customer_tags",
            joinColumns = @JoinColumn(name = "customer_id")
    )
    @Column(name = "tag", nullable = false)
    private List<String> tags;

}
