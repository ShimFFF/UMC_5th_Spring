package umc.spring.ApiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.ApiPayload.code.BaseErrorCode;
import umc.spring.ApiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),

    //멤버 관련 응답
    MEMBER_NOT_FOUND(HttpStatus.BAD_REQUEST , "MEMBER4001", "존재하지 않는 회원입니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),
    NICKNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4003", "이미 존재하는 닉네임 입니다."),
    PASSWARD_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4004", "비밀번호는 필수 입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST, "MEMBER4005", "비밀번호가 일치하지 않습니다."),
    ID_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4006", "아이디는 필수 입니다."),
    ID_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4007", "이미 존재하는 아이디 입니다."),
    ID_NOT_MATCH(HttpStatus.BAD_REQUEST, "MEMBER4008", "아이디가 일치하지 않습니다."),

    // For test
    //Query String에 flag를 받아오는데, 해당 flag가 1인 경우 exception을 발생시킬 예정
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트 exception 발생!"),

    // Region 관련
    REGION_NOT_FOUND(HttpStatus.BAD_REQUEST, "REGION4001", "존재하지 않는 지역 입니다."),
    REGION_NAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "REGION4003", "지역 이름은 필수 입니다."),
    REGION_NAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "REGION4004", "이미 존재하는 지역 이름 입니다."),
    REGION_NAME_NOT_MATCH(HttpStatus.BAD_REQUEST, "REGION4005", "지역 이름이 일치하지 않습니다."),

    // Store 관련
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4001", "존재하지 않는 가게 입니다."),
    STORE_NAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "STORE4003", "가게 이름은 필수 입니다."),
    STORE_NAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "STORE4004", "이미 존재하는 가게 이름 입니다."),
    STORE_NAME_NOT_MATCH(HttpStatus.BAD_REQUEST, "STORE4005", "가게 이름이 일치하지 않습니다."),

    // StoreReview 관련
    STORE_REVIEW_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE_REVIEW4001", "존재하지 않는 가게 리뷰 입니다."),
    STORE_REVIEW_CONTENT_NOT_EXIST(HttpStatus.BAD_REQUEST, "STORE_REVIEW4003", "가게 리뷰 내용은 필수 입니다."),
    STORE_REVIEW_CONTENT_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "STORE_REVIEW4004", "이미 존재하는 가게 리뷰 내용 입니다."),
    STORE_REVIEW_CONTENT_NOT_MATCH(HttpStatus.BAD_REQUEST, "STORE_REVIEW4005", "가게 리뷰 내용이 일치하지 않습니다."),

    // Mission 관련
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "존재하지 않는 미션 입니다."),
    MISSION_NAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MISSION4003", "미션 이름은 필수 입니다."),
    MISSION_NAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "MISSION4004", "이미 존재하는 미션 이름 입니다."),
    MISSION_NAME_NOT_MATCH(HttpStatus.BAD_REQUEST, "MISSION4005", "미션 이름이 일치하지 않습니다."),

    //memberMission 관련
    MISSION_ALREADY_CHALLENGED(HttpStatus.BAD_REQUEST, "MISSION4006", "이미 도전한 미션 입니다."),
    MISSION_NOT_CHALLENGED(HttpStatus.BAD_REQUEST, "MISSION4007", "도전하지 않은 미션 입니다."),
    MISSION_NOT_CHALLENGED_YET(HttpStatus.BAD_REQUEST, "MISSION4008", "아직 도전하지 않은 미션 입니다."),
    MISSION_ALREADY_COMPLETED(HttpStatus.BAD_REQUEST, "MISSION4009", "이미 완료한 미션 입니다."),
    MEMBER_MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4010", "존재하지 않는 회원 미션 입니다."),
    MEMBER_MISSION_NOT_MATCH(HttpStatus.BAD_REQUEST, "MISSION4011", "회원 미션 정보가 일치하지 않습니다."),

    // 음식 카테고리 관련
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.BAD_REQUEST, "FOOD_CATEGORY4001", "존재하지 않는 음식 카테고리 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder() // ErrorReasonDTO 객체 생성
                .isSuccess(false)
                .message(message)
                .code(code)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder() // ErrorReasonDTO 객체 생성
                .isSuccess(false)
                .message(message)
                .code(code)
                .httpStatus(httpStatus)
                .build();
    }
}
