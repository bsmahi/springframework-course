# Spring JDBC

Prior to Spring Boot, for performing CRUD operation with database, we used to follow the below steps.

Let's look at an example that demonstrates the usage of Spring JDBC for data access.

1. First, you'll need to set up the necessary dependencies in your project. If you're using Maven, you can add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <!-- Spring JDBC -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>5.3.9</version>
    </dependency>

    <!-- Database Driver (e.g., MySQL) -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.27</version>
    </dependency>
</dependencies>
```
2. Now, let's assume you have a simple `Course` entity class representing a course in your application:
```java
public class Course {
    private int id;
    private String courseName;

    // Constructors, getters, and setters
}
```

3. Next, create a CourseDAO interface that defines the contract for data access operations on the Course entity:

```java
public interface CourseDAO {
    void save(Course course);
    void update(Course course);
    void delete(int courseId);
    Customer getById(int courseId);
    List<Course> getAll();
}
```

4. Implement the CourseDAO interface using Spring JDBC. You'll need a JdbcTemplate provided by Spring for executing SQL queries:

```java
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class JdbcCourseDAO implements CourseDAO {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO course (coursename) VALUES (?)";
        jdbcTemplate.update(sql, customer.getCourseName());
    }

    @Override
    public void update(Course course) {
        String sql = "UPDATE course SET coursename=? WHERE id=?";
        jdbcTemplate.update(sql, course.getCourseName(), course.getId());
    }

    @Override
    public void delete(int courseId) {
        String sql = "DELETE FROM course WHERE id=?";
        jdbcTemplate.update(sql, courseId);
    }

    @Override
    public Course getById(int courseId) {
        String sql = "SELECT * FROM course WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{courseId}, new CourseRowMapper());
    }

    @Override
    public List<Course> getAll() {
        String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }

    // RowMapper implementation for mapping ResultSet to Course an object
    private static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setCourseName(rs.getString("courseName"));
            return course;
        }
    }
}
```

In this example, we use the `JdbcTemplate` to execute SQL statements. The RowMapper interface is used to map the result set to the Course object.

Finally, configure Spring to manage the dependencies and create an instance of the `JdbcCourseDAO`:

```xml
<bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
    <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
    <property name="username" value="username"/>
    <property name="password" value="password"/>
</bean>

<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <property name="dataSource" ref="dataSource"/>
</bean>

<bean id="courseDAO" class="com.learnspring.JdbcCourseDAO">
    <property name="jdbcTemplate" ref="jdbcTemplate"/>
</bean>
```

Make sure to adjust the database connection details (url, username, password) based on your database configuration.

Now, you can use the `CourseDAO` in your application:

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CourseDAO courseDAO = context.getBean("courseDAO", CourseDAO.class);

        // Create a new course
        Course course = new Course();
        course.setCourseName("John Doe");
        courseDAO.save(course);

        // Retrieve and update a course
        Course retrievedCourse = courseDAO.getById(1);
        retrievedCourse.setCourseName("Learn Spring Data Jpa");
        customerDAO.update(retrievedCourse);

        // Delete a course
        courseDAO.delete(1);

        // Get all courses
        List<Course> allCourses = courseDAO.getAll();
        for (Course c : allCourses) {
            System.out.println(c.getCourseName());
        }
    }
}
```

That's it! This example demonstrates how to use Spring JDBC for basic CRUD operations using the DAO pattern. The Spring framework takes care of managing the database connections, transactions, and other low-level details, allowing you to focus on writing your application logic.

> **Note:** However, using Spring Data Jpa, we will reduce the above steps by using implementation with the concept of @Autowiring and AutoConfiguration