package EntitysDataBase;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "subscriptions")
public class Subscription {

    @Getter
    @Setter
    @Column(name = "student_id")
    @Id
    private int studentId;

    @Getter
    @Setter
    @Column(name = "course_id")
    @Id
    @ManyToOne
    private int courseId;

    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
