package EntitysDataBase;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Students")
public class Student {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany
    private int id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int age;

    @Getter
    @Setter
    @Column(name = "registration_date")
    private Date registrationDate;
}
