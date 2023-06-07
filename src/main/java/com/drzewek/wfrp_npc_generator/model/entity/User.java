package com.drzewek.wfrp_npc_generator.model.entity;

import com.drzewek.wfrp_npc_generator.model.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity(name = "user_table")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PACKAGE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @ElementCollection(targetClass = Role.class)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    @CreationTimestamp
    private LocalDateTime joinDate;
    @UpdateTimestamp
    private LocalDateTime lastUpdate;

    private boolean isConfirmed = false;

    @OneToMany()
    @JoinColumn(name = "user_id")
    private List<Npc> savedNpcs;

    public void addRole(Role role) {
        roles.add(role);
    }

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
