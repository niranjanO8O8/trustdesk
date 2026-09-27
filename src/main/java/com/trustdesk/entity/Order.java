package com.trustdesk.entity;

import com.trustdesk.enums.OrderStatus;
import com.trustdesk.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *{
 *     "order_id": "ord_5001",
 *     "customer_id": "cus_1001",
 *     "status": "delivered",
 *     "placed_at": "2026-06-20",
 *     "delivered_at": "2026-06-24",
 *     "eligible_return_until": "2026-07-01",
 *     "total": 8999,
 *     "currency": "INR",
 *     "payment_status": "paid",
 *     "tracking_number": "BLUETRK10001",
 *     "items": [
 *       {"sku": "BG-AIRPODS-01", "name": "BlueBuds Air", "quantity": 1, "category": "audio", "final_sale": false}
 *     ]
 *   }
 *
 **/

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "order_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(name = "placed_at", nullable = false)
    private LocalDate placedAt;

    @Column(name = "delivered_at")
    private LocalDate deliveredAt;

    @Column(name = "eligible_return_until")
    private LocalDate eligibleReturnUntil;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal total;

    @Column(nullable = false)
    private String currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

    public void addItem(OrderItem orderItem) {
        this.items.add(orderItem );
        orderItem.setOrder(this);
    }


}
