package com.sososhopping.server.entity.member;

import com.sososhopping.server.entity.BaseTimeEntity;
import com.sososhopping.server.entity.store.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @NotNull
    private Integer quantity;

    @Builder
    public Cart(User user, Item item, Integer quantity) {
        this.user = user;
        this.item = item;
        this.quantity = quantity;
    }

    // 연관 관계 편의 메서드
    public void setUser(User user) {
        this.user = user;
        this.user.getCart().add(this);
    }

    // Business Logic
    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}
