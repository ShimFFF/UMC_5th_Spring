package umc.spring.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.enums.Status;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl{
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public Optional<Users> findMember(Long id) {
        return memberRepository.findById(id);
    }

    public Page<StoreReview> getReviewList(Long memberId, Integer page) { // 내가 쓴 가게 리뷰 리스트
        Optional<Users> optionalStore = findMember(memberId);
        Users users = optionalStore.get();

        Page<StoreReview> reviewPage = reviewRepository.findAllByUser(users, PageRequest.of(page, 10));
        return reviewPage;
    }

    public Page<MemberMission> getGoingMissionList(Long memberId, Integer page) { // 도전 중인 미션 리스트
        Optional<Users> optionalUser = findMember(memberId);
        Users users = optionalUser.get();

        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByUserAndStatus(users, MissionStatus.GOING, PageRequest.of(page, 10));
        return memberMissionPage;
    }

}
