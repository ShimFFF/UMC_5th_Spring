package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.StoreReview;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;
import umc.spring.web.dto.ReviewRequest;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    @Transactional
    public MemberMission add(MissionRequest.addDTO request) {
        return memberMissionRepository.save(MissionConverter.toMemberMission(request));
    }
}
