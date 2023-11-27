package umc.spring.ApiPayload.code;


// 이를 구체화 하는 Status에서 두 개의 메소드를 반드시 Override 해야 함을 강제
public interface BaseCode {

    // ErrorStatus의 값을 반환
    public ReasonDTO getReason();

    // ErrorStatus의 HttpStatus 값을 반환
    public ReasonDTO getReasonHttpStatus();
}
