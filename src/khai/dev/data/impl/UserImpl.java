package khai.dev.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import khai.dev.data.dao.UserDAO;
import khai.dev.data.driver.MySQLDriver;
import khai.dev.data.model.User;

public class UserImpl implements UserDAO {

    Connection con = MySQLDriver.getInstance().getConnection();

    @Override
    public boolean insert(User user) {
        // Bỏ cột 'user_id' ra khỏi câu lệnh INSERT vì nó là AUTO_INCREMENT
        String sql = "INSERT INTO users(username, password, email, created_at, updated_at, total_spent) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getCreatedAt());
            stmt.setString(5, user.getUpdatedAt());
            stmt.setDouble(6, user.getTotalSpent());

            stmt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE users SET username = ?, email = ?, password = ?, updated_at = ?, total_spent = ? WHERE user_id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getUpdatedAt());
            stmt.setDouble(5, user.getTotalSpent());
            stmt.setInt(6, user.getUserId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User find(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                double totalSpent = rs.getDouble("total_spent");

                return new User(id, username, password, email, createdAt, updatedAt, totalSpent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                double totalSpent = rs.getDouble("total_spent");

                users.add(new User(id, username, password, email, createdAt, updatedAt, totalSpent));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User find(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                double totalSpent = rs.getDouble("total_spent");
                return new User(id, username, password, email, createdAt, updatedAt, totalSpent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User find(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                double totalSpent = rs.getDouble("total_spent");
                return new User(id, username, password, email, createdAt, updatedAt, totalSpent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
