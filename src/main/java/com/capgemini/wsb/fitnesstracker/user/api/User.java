package com.capgemini.wsb.fitnesstracker.user.api;
import com.capgemini.wsb.fitnesstracker.training.api.Training;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    @Getter
    @Setter
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Getter
    @Setter
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Getter
    @Setter
    @Column(name = "birthdate", nullable = false)
    private LocalDate birthdate;

    @Getter
    @Setter
    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Training> trainings;

    public User(
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.trainings = new ArrayList<>();
    }

    //with id
    public User(
            final Long id,
            final String firstName,
            final String lastName,
            final LocalDate birthdate,
            final String email) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.trainings = new ArrayList<>();
    }

    public Integer getUserAge(){
        LocalDate today = LocalDate.now();
        long daysBetween = birthdate.toEpochDay() - today.toEpochDay();
        return (int) Math.floor(daysBetween / 365.25);
    }

}

