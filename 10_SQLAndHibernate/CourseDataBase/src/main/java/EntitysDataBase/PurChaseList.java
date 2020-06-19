package EntitysDataBase;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Purchaselist")
public class PurChaseList {

    @Getter
    @Setter
    @Column(name = "student_name")
    private String studentName;

    @Getter
    @Setter
    @Column(name = "course_name")
    private String courseName;

    @Getter
    @Setter
    private int price;

    @Getter
    @Setter
    @Column(name = "subscription_date")
    private Date subscriptionDate;
}