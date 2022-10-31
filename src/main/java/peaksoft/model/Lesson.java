package peaksoft.model;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "lesson")
@Setter
@Getter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String videoLink;
    @OneToMany(cascade = ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private List<Task> taskList = new ArrayList<>();

    @ManyToOne(cascade = {DETACH,MERGE,REFRESH,PERSIST},fetch = FetchType.EAGER)
    private Course course;

    public Lesson(String name, String videoLink) {
        this.name = name;
        this.videoLink = videoLink;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", videoLink='" + videoLink + '\'' +
                ", taskList=" + taskList +
                '}';
    }
}
