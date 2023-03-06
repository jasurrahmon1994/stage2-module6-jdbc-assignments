package jdbc;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleJDBCRepository {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private Statement st = null;

    private static final String createUserSQL = "INSERT INTO myusers (firstname, lastname, age) VALUES (?, ?, ?)";
    private static final String updateUserSQL = "update myysers set firstname = ?, lastName = ?, age = ? where id = ?";
    private static final String deleteUser = "delete from myysers where id = ?";
    private static final String findUserByIdSQL = "select * from myusers where id = ?";
    private static final String findUserByNameSQL = "select * from myusers where firstname = ?";
    private static final String findAllUserSQL = "select * from myusers";

    public Long createUser() {
        int res;
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        try {
            ps = connection.prepareStatement(createUserSQL);
            ps.setString(1, "Jasur");
            ps.setString(2, "Rahmonov");
            ps.setInt(3, 27);
            res = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return (long) res;
    }

    public User findUserById(Long userId) {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        User user = new User();
        try {
            ps = connection.prepareStatement(findUserByIdSQL);
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                int age = rs.getInt("age");
                int id = rs.getInt("id");
                user.setId((long) id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAge(age);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User findUserByName(String userName) {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        User user = new User();
        try {
            ps = connection.prepareStatement(findUserByNameSQL);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                int age = rs.getInt("age");
                int id = rs.getInt("id");
                user.setId((long) id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAge(age);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<User> findAllUser() {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        List<User> users = new ArrayList<>();
        try {
            ps = connection.prepareStatement(findAllUserSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                int age = rs.getInt("age");
                int id = rs.getInt("id");
                user.setId((long) id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setAge(age);
                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public User updateUser(Long userId) {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        User user = new User();
        return user;
    }

    private void deleteUser(Long userId) {
        connection = new CustomConnector().getConnection(CustomDataSource.getInstance().getUrl(), CustomDataSource.getInstance().getName(), CustomDataSource.getInstance().getPassword());
        try {
            ps = connection.prepareStatement(deleteUser);
            ps.setLong(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
