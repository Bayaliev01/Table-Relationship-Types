package peaksoft.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.CascadeType.*;


@Entity
@Table(name = "instructor")
@Setter
@Getter
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firsName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @ManyToMany(cascade = {DETACH, MERGE, PERSIST, REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "instructor_course",
            joinColumns = @JoinColumn(name = "instructor_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id",referencedColumnName = "id"))
    private List<Course> course = new ArrayList<>();

    public Instructor(String firsName, String lastName, String email, String phoneNumber) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber;
    }
}
