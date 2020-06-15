import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String pass = "Aa1111";

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT course_name, " +
                     "(count(*) / 8) AS " +
                     "avg_count_in_month\n" +
                     "FROM PURCHASELIST\n" +
                     "WHERE MONTH(subscription_date) < 9\n" +
                     "GROUP BY course_name\n" +
                     "order by course_name;")) {
            while (resultSet.next()) {
                String nameCourse = resultSet.getString("course_name");
                String avgCountByInMonth = resultSet.getString("avg_count_in_month");
                System.out.printf("Наименование курса: {%s} - среднее число покупок в месяц {%s}%n",
                        nameCourse, avgCountByInMonth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
