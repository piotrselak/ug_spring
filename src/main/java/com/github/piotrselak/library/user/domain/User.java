package com.github.piotrselak.library.user.domain;

import com.github.piotrselak.library.reservation.Reservation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="user_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")})
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reservation> reservation = new HashSet<>();
}
