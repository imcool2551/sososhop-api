package com.sososhopping.service.owner;

import com.sososhopping.common.dto.owner.request.UserReportRequestDto;
import com.sososhopping.common.error.Api400Exception;
import com.sososhopping.entity.user.User;
import com.sososhopping.entity.admin.UserReport;
import com.sososhopping.entity.owner.Store;
import com.sososhopping.domain.auth.repository.UserAuthRepository;
import com.sososhopping.repository.report.UserReportRepository;
import com.sososhopping.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StoreUserReportService {

    private final StoreRepository storeRepository;
    private final UserAuthRepository userRepository;
    private final UserReportRepository userReportRepository;

    //유저 신고
    @Transactional
    public void createUserReport(Long storeId, UserReportRequestDto dto) {
        Store store = storeRepository.findById(storeId).orElseThrow(() ->
                new Api400Exception("존재하지 않는 점포입니다"));

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() ->
                new Api400Exception("존재하지 않는 고객입니다"));

        UserReport userReport = new UserReport(store, user, dto.getContent(), false);

        userReportRepository.save(userReport);
    }
}
