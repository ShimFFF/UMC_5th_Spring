package umc.spring.web.dto.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//응답 DTO 예시
public class TempResponse {
    @Builder //빌더 패턴이란? : https://johngrib.github.io/wiki/builder-pattern/
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    //static으로 만드는 이유 : 클래스를 생성하지 않고 바로 사용하기 위해
    // DTO 자체는 수많은 곳에서 사용이 될 수 있기 때문에 static으로 선언하여 사용
    public static class TempRequestDTO {
        private String testString;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempExceptionDTO {
        Integer flag;
    }
}
