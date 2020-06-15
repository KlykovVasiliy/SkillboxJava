import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Courses")
public class Course {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int duration;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private CourseType type;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    @Column(name = "teacher_id")
    private int teacherId;

    @Getter
    @Setter
    @Column(name = "students_count")
    private int studentsCount;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    @Column(name = "price_per_hour")
    private float pricePerHour;

    @Override
    public String toString() {
        return "Наименование курса: {" + getName() + "}. На курсе обучается " + getStudentsCount()
                + " студентов.";
    }
}
