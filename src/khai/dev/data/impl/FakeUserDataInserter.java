package khai.dev.data.impl;

import khai.dev.data.dao.UserDAO;
import khai.dev.data.faker.FakeUserGenerator;
import khai.dev.data.model.User;

import java.util.List;

public class FakeUserDataInserter {
    public static void main(String[] args) {
        // Khởi tạo UserDAO và FakeUserGenerator
        UserDAO userDAO = new UserImpl();  // `UserImpl` là lớp hiện thực của `UserDAO`
        FakeUserGenerator fakeUserGenerator = new FakeUserGenerator();

        // Sinh ra 100 người dùng ảo
        List<User> fakeUsers = fakeUserGenerator.generateFakeUsers(100);

        // Thêm từng người dùng vào cơ sở dữ liệu
        for (User user : fakeUsers) {
            boolean inserted = userDAO.insert(user);
            if (inserted) {
                System.out.println("Đã thêm người dùng: " + user.getUsername());
            } else {
                System.out.println("Lỗi khi thêm người dùng: " + user.getUsername());
            }
        }

        System.out.println("Hoàn thành việc thêm dữ liệu ảo.");
    }
}
