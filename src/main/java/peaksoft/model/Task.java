package peaksoft.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "task")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String deadLine;
    private String task;


    public Task(String name, String deadLine, String task) {
        this.name = name;
        this.deadLine = deadLine;
        this.task = task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deadLine='" + deadLine + '\'' +
                ", task='" + task +
                '}';
    }
}
