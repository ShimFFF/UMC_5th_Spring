package umc.spring.converter;

import umc.spring.web.dto.test.TempResponse;

// Converter 역할 : DTO를 Entity로 변환
// OOP에 따라 역할을 분리하여 코드를 작성하면 유지보수가 편리해짐
//따라서  Converter로 역할을 분리하여 코드를 작성
public class TempConverter {
    public static TempResponse.TempRequestDTO toTempTestDTO(){
        return TempResponse.TempRequestDTO.builder()
                .testString("W8 Test!")
                .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag){
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
