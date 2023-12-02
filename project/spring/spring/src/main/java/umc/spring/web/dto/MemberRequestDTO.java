package umc.spring.web.dto;

import lombok.Getter;
import umc.spring.Validation.ExistCategories;
import umc.spring.domain.enums.Gender;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


public class MemberRequestDTO {

    @Getter
    public static class SignUpDTO{
        @NotNull
        String name;
        @Size(min = 5, max = 40)
        @NotNull
        String email;
        Gender gender;
        @NotNull
        String brith;
        String phone;
        @Size(min = 5, max = 100)
        String address;
        Integer socialType;
        @ExistCategories
        List<Long> foodPerferIdList;

    }

}
