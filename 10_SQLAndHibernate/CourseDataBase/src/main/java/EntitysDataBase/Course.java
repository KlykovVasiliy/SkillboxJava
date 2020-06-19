package EntitysDataBase;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Subscriptions",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")})
    @Getter
    @Setter
    private List<Student> students;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "PurChaseList",
            joinColumns = {@JoinColumn(name = "student_name")},
            inverseJoinColumns = {@JoinColumn(name = "course_name")})
    @Getter
    @Setter
    private List<PurChaseList> purChaseLists;


    @Override
    public String toString() {
        return "Наименование курса: {" + getName() + "}. На курсе обучается " + getStudentsCount()
                + " студентов.";
    }
}
