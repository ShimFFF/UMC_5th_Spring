package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.ReviewConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.StoreReview;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;
import umc.spring.web.dto.ReviewRequest;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberMission challenge(MissionRequest.challengeDTO request) {
        Users users = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission =  missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MISSION_NOT_FOUND));

        return memberMissionRepository.save(MissionConverter.toMemberMission(request, mission, users));
    }
}
