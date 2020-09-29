import EntitysDataBase.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.List;

public class Main {
    private static int sizeTable;
    public static void main(String[] args) {
        Session session = createSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Course course = session.get(Course.class, 1);
        System.out.println(course.getStudents().size());

        List<Student> studentList = course.getStudents();
        for (Student student : studentList) {
            System.out.println(student.getName());
        }

        transaction.commit();
    }

    //Как делать sessionFactory.close() если объект sessionFactory перенесен в метод и не исполняется
    //в методе main, отличие от примера Даниила
    private static SessionFactory createSessionFactory() {
        sizeTable = getSizeTable();
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    private static int getSizeTable() {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "Aa1111";
        int sizeTable = 0;
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select count(name) as count_courses" +
                     " from courses;")) {
            while (resultSet.next()) {
                sizeTable = resultSet.getInt("count_courses");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizeTable;
    }
}