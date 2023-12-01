package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MissionRequest;
import umc.spring.web.dto.MissionResponse;

import javax.persistence.EntityNotFoundException;

public class MissionConverter {

    public static MissionResponse.challengeDTO toResponseAddDTO (MemberMission memberMission){
        return MissionResponse.challengeDTO.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .build();
    }

    public static MemberMission toMemberMission(MissionRequest.challengeDTO request, Mission mission, Users users){

        return MemberMission.builder()
                .mission(mission)
                .user(users)
                .build();
    }
}
