package com.drzewek.wfrp_npc_generator.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PACKAGE)
    private Long id;

    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private LocalDateTime joinDate;
    private LocalDateTime lastUpdate;

    @OneToMany()
    @JoinColumn(name = "user_id")
    private List<Npc> savedNpcs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;
        if (!email.equals(user.email)) return false;
        if (!password.equals(user.password)) return false;
        if (!Objects.equals(joinDate, user.joinDate)) return false;
        if (!Objects.equals(lastUpdate, user.lastUpdate)) return false;
        return Objects.equals(savedNpcs, user.savedNpcs);
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (joinDate != null ? joinDate.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        result = 31 * result + (savedNpcs != null ? savedNpcs.hashCode() : 0);
        return result;
    }
}
