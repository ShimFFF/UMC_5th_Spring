package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.domain.Users;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.memberMission.MemberMissionRequest;
import umc.spring.web.dto.memberMission.MemberMissionResponse;

public class MissionConverter {

    public static MemberMissionResponse.challengeDTO toResponseAddDTO (MemberMission memberMission){
        return MemberMissionResponse.challengeDTO.builder()
                .memberMissionId(memberMission.getMemberMissionId())
                .build();
    }

    public static MemberMission toMemberMission(MemberMissionRequest.challengeDTO request, Mission mission, Users users){

        return MemberMission.builder()
                .mission(mission)
                .user(users)
                .build();
    }
}
