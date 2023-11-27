package umc.spring.domain;

import lombok.*;
import umc.spring.domain.enums.Decision;
import umc.spring.domain.mapping.TermAgree;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long termsId;

    @Column(nullable = false, length = 20)
    private String termsName;

    @Column(nullable = false, length = 200)
    private String termsContent;

    //약관 필수 여부
    @Enumerated(EnumType.STRING)
    private Decision mandatory; //(Y / N)

    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
    private List<TermAgree> termAgreeList = new ArrayList<>();

}
