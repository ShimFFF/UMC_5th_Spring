package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.ApiPayload.code.status.ErrorStatus;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.exception.handler.MemberHandler;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MissionRequest;

import javax.transaction.Transactional;
import javax.validation.ConstraintValidatorContext;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberMission challenge(MissionRequest.challengeDTO request) {
        Users users = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MISSION_NOT_FOUND));

        return memberMissionRepository.save(MissionConverter.toMemberMission(request, mission, users));
    }

    public boolean ismemberMissionchallengeValid(MissionRequest.challengeDTO request) {
        boolean isExist = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(request.getMemberId(), request.getMissionId());
        return !isExist;
    }
}