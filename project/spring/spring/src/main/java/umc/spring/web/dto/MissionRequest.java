package umc.spring.web.dto;

import lombok.Getter;

public class MissionRequest {

    @Getter
    public static class challengeDTO{
        Long memberId;
        Long missionId;
    }
}
