package umc.spring.web.dto;

import lombok.Getter;

public class MissionRequest {

    @Getter
    public static class addDTO{
        Long memberId;
        Long missionId;
    }
}
