package com.sososhopping.server.domain.entity.orders;

import com.sososhopping.server.domain.entity.BaseTimeEntity;
import com.sososhopping.server.domain.entity.coupon.Coupon;
import com.sososhopping.server.domain.entity.member.User;
import com.sososhopping.server.domain.entity.store.Store;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Table(name = "orders")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @CreatedDate
    private LocalDateTime orderDate;

    @NotNull
    @Column(name = "orderer_name")
    private String ordererName;

    @Column(name = "orderer_phone")
    private String ordererPhone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "orderer_type")
    private OrderType orderType;

    private LocalDateTime visitDate;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    //반정규화용
    private String storeName;

    private Integer deliveryCharge;

    private String deliveryStreetAddress;

    private String deliveryDetailedAddress;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @NotNull
    private Integer orderPrice;

    private Integer usedPoint;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @NotNull
    private Integer finalPrice;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //List
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY, mappedBy = "order")
    private Payment payment;
}
