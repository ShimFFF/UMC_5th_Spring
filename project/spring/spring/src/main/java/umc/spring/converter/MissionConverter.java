package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.User;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MissionConverter {

    private static MissionRepository missionRepository;
    private static MemberRepository memberRepository;

    //의존성 주입
    public MissionConverter(MissionRepository missionRepository, MemberRepository memberRepository) {
        this.missionRepository = missionRepository;
        this.memberRepository = memberRepository;
    }

    public static MissionResponse.addDTO toResponseAddDTO (MemberMission memberMission){
        return MissionResponse.addDTO.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .build();
    }

    public static MemberMission toMemberMission(MissionRequest.addDTO request){

        User user = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 회원이 없습니다."));

        Mission mission = (Mission) missionRepository.findById(request.getMissionId())
                .orElseThrow(() -> new EntityNotFoundException("해당하는 미션을 찾을 수 없습니다."));

        return MemberMission.builder()
                .mission(mission)
                .user(user)
                .build();
    }
}
