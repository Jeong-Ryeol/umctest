package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Alarm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long targetId;

    private LocalDateTime readTime;

    @ManyToOne
    @JoinColumn(name = "user_main_id") // ERD 기준 FK 명칭
    private UserMain userMain;


    private Integer status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
