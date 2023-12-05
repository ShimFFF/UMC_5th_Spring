package umc.spring.web.dto.memberMission;

import lombok.Getter;
import umc.spring.Validation.NotChallengingMission;


public class MemberMissionRequest {

    @Getter
    @NotChallengingMission
    public static class challengeDTO{
        Long memberId;
        Long missionId;
    }
}
