package umc.study.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import umc.study.domain.UserMain;

@Entity
@Getter
@Setter
public class ServiceRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_main_id")
    private UserMain userMain;

    // Add other fields as needed
}
