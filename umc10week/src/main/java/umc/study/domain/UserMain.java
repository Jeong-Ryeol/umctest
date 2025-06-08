package umc.study.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;
import umc.study.domain.UserLog;
import umc.study.domain.Alarm;


@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMain extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String userPwd;
    private String name;
    private Long gender;
    private Long birthday;
    private String address;
    private String status;

    @OneToOne(mappedBy = "userMain", cascade = CascadeType.ALL)
    private UserLog userLog;

    @OneToMany(mappedBy = "userMain")
    private List<Alarm> alarms = new ArrayList<>();

    @OneToMany(mappedBy = "userMain")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "userMain")
    private List<Store> stores = new ArrayList<>();

    @OneToMany(mappedBy = "userMain")
    private List<ServiceAgree> serviceAgreements = new ArrayList<>();

    @OneToMany(mappedBy = "userMain")
    private List<PreferFood> preferFoods = new ArrayList<>();
}
