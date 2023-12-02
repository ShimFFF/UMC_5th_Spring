package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.Validation.NotChallengingMission;

public class MissionRequest {

    @Getter
    @NotChallengingMission
    public static class challengeDTO{
        Long memberId;
        Long missionId;
    }
}
