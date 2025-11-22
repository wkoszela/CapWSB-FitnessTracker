package pl.wsb.fitnesstracker.user.api;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Training> trainings;

    public User(String firstName, String lastName, LocalDate birthdate, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
    }
}