package umc.spring.service.member;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;
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

    public Optional<Users> findMember(Long id) {
        return memberRepository.findById(id);
    }

    public Page<StoreReview> getReviewList(Long memberId, Integer page) {
        Optional<Users> optionalStore = findMember(memberId);
        Users users = optionalStore.get();

        Page<StoreReview> reviewPage = reviewRepository.findAllByUser(users, PageRequest.of(page, 10));
        return reviewPage;
    }

}
