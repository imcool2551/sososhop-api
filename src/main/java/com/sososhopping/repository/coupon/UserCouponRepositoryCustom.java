package com.sososhopping.repository.coupon;

import com.sososhopping.entity.coupon.UserCoupon;
import com.sososhopping.entity.member.User;

import java.util.List;

public interface UserCouponRepositoryCustom {

    List<UserCoupon> findUsableCouponsByUser(User user);
}
