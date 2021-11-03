package com.sososhopping.server.entity.store;

import com.sososhopping.server.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @NotNull
    private String name;

    private String description;

    private String purchaseUnit;

    private String imgUrl;

    @NotNull
    private Integer price;

    @NotNull
    @Type(type = "numeric_boolean")
    @Column(nullable = false, columnDefinition = "TINYINT", length = 1)
    private Boolean saleStatus;

    // 생성자 + 빌더
    @Builder
    public Item(Store store,
                String name,
                String description,
                String purchaseUnit,
                String imgUrl,
                Integer price,
                Boolean saleStatus
    ) {
        setStore(store);
        this.name = name;
        this.description = description;
        this.purchaseUnit = purchaseUnit;
        this.imgUrl = imgUrl;
        this.price = price;
        this.saleStatus = saleStatus;
    }

    // 연관 관계 편의 메서드
    private void setStore(Store store) {
        this.store = store;
        this.store.getItems().add(this);
    }
}
