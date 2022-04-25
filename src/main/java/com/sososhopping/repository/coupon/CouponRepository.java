package com.sososhopping.repository.coupon;

import com.sososhopping.entity.coupon.Coupon;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long>, CouponRepositoryCustom {

    //발행 예정 쿠폰 read
//    List<Coupon> findExceptedByStoreAndIssuedStartDateAfter(Store store, LocalDateTime start);

    //발행중인 쿠폰 read
//    List<Coupon> findBeingByStoreAndIssuedStartDateBeforeAndIssuedDueDateAfter(Store store, LocalDateTime start, LocalDateTime due);

    //code로 쿠폰 탐색
    @EntityGraph(attributePaths = {"store"}, type = EntityGraph.EntityGraphType.FETCH)
    Optional<Coupon> findByCouponCode(String couponCode);

}
