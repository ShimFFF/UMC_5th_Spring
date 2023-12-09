package umc.spring.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.enums.Status;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.exception.handler.MemberMissionHandler;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl{
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Optional<Users> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Page<StoreReview> getReviewList(Long memberId, Integer page) { // 내가 쓴 가게 리뷰 리스트
        Users users = findMember(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<StoreReview> reviewPage = reviewRepository.findAllByUser(users, PageRequest.of(page, 10));
        return reviewPage;
    }

    @Transactional(readOnly = true)
    public Page<MemberMission> getGoingMissionList(Long memberId, Integer page) { // 도전 중인 미션 리스트
        Users users = findMember(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByUserAndStatus(users, MissionStatus.GOING, PageRequest.of(page, 10));
        return memberMissionPage;
    }

}
