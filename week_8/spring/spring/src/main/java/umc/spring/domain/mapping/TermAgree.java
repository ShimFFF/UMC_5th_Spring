package umc.spring.domain.mapping;

import lombok.*;
import umc.spring.domain.Member;
import umc.spring.domain.Terms;
import umc.spring.domain.common.BaseTimeEntitiy;
import umc.spring.domain.enums.Decision;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TermAgree extends BaseTimeEntitiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termAgreeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id") //FK
    private Terms terms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //FK
    private Member member;
}
