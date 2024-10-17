package khai.dev;

import khai.dev.data.impl.CategoryImpl;
import khai.dev.data.impl.UserImpl;
import khai.dev.data.faker.FakeCategoryGenerator;
import khai.dev.data.faker.FakeUserGenerator;
import khai.dev.data.model.Category;
import khai.dev.data.model.User;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
//        // Khởi tạo UserImpl và FakeUserGenerator
//        UserImpl userDAO = new UserImpl();  // `UserImpl` là lớp hiện thực của `UserDAO`
//        FakeUserGenerator fakeUserGenerator = new FakeUserGenerator();
//
//        // Sinh ra 100 người dùng ảo
//        List<User> fakeUsers = fakeUserGenerator.generateFakeUsers(100);
//
//        // Thêm từng người dùng vào cơ sở dữ liệu
//        for (User user : fakeUsers) {
//            boolean inserted = userDAO.insert(user);
//            if (inserted) {
//                System.out.println("Đã thêm người dùng: " + user.getUsername());
//            } else {
//                System.out.println("Lỗi khi thêm người dùng: " + user.getUsername());
//            }
//        }
//
//        System.out.println("Hoàn thành việc thêm dữ liệu ảo.");
     // Khởi tạo CategoryImpl và FakeCategoryGenerator
        CategoryImpl categoryDAO = new CategoryImpl();  // `CategoryImpl` là lớp hiện thực của `CategoryDAO`
        FakeCategoryGenerator fakeCategoryGenerator = new FakeCategoryGenerator();

        // Sinh ra 10 danh mục ảo
        List<Category> fakeCategories = fakeCategoryGenerator.generateFakeCategories(10);

        // Thêm từng danh mục vào cơ sở dữ liệu
        for (Category category : fakeCategories) {
            boolean inserted = categoryDAO.insert(category);
            if (inserted) {
                System.out.println("Đã thêm danh mục: " + category.getName());
            } else {
                System.out.println("Lỗi khi thêm danh mục: " + category.getName());
            }
        }

        System.out.println("Hoàn thành việc thêm dữ liệu danh mục ảo.");
        
    }
}
