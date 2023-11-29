package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.domain.enums.Gender;

import java.util.List;


public class MemberRequestDTO {

    @Getter
    public static class SignUpDTO{
        String name;
        String email;
        Gender gender;
        String brith;
        String phone;
        String address;
        List<Long> foodPerferIdList;

    }

}
