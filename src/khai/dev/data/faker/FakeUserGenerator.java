package khai.dev.data.faker;

import khai.dev.data.model.User;
import com.github.javafaker.Faker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FakeUserGenerator {
    private Faker faker;

    public FakeUserGenerator() {
        this.faker = new Faker();
    }

    // Phương thức tạo một người dùng ảo
    public User generateFakeUser() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String username = faker.name().username();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress();
        String createdAt = LocalDateTime.now().format(formatter);
        String updatedAt = LocalDateTime.now().format(formatter);
        double totalSpent = faker.number().randomDouble(2, 100, 10000);

        return new User(0, username, password, email, createdAt, updatedAt, totalSpent);
    }

    // Phương thức tạo danh sách người dùng ảo
    public List<User> generateFakeUsers(int numberOfUsers) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; i++) {
            users.add(generateFakeUser());
        }
        return users;
    }
}
