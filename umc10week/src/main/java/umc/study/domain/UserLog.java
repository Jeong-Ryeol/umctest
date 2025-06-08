package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserLog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    @JoinColumn(name = "user_main_id")
    private UserMain userMain;


    private LocalDateTime loginTime;
    private LocalDateTime signUpTime;
    private LocalDateTime passwordChangeTime;

    private Integer loginCount;
    private Integer paymentCount;
}
