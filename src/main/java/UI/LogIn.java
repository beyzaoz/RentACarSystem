package UI;

import dao.UserDAO;
import exception.ExceptionMessages;
import exception.SystemException;
import model.User;
import model.enums.Roles;
import service.UserService;
import util.PasswordUtil;

import java.util.Scanner;

public class LogIn {

    public static void logInstart() throws SystemException {
        logInPart(new Scanner(System.in));
    }

    private static void logInPart(Scanner scanner) throws SystemException {
        UserService userService = new UserService();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim().toLowerCase();

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        try {
            User user = userService.login(email, PasswordUtil.hash(password));
            if (user == null) {
                throw new SystemException(ExceptionMessages.LOGIN_FAILED);
            }

            if (Roles.ADMIN.equals(user.getRole())) {
                AdminLogIn.adminLogInPanel(scanner, user);
            } else if (Roles.COOPARATE_CUSTOMER.equals(user.getRole()) || Roles.INDIVIDUAL_CUSTOMER.equals(user.getRole())) {
                CustomerLogin.customerLoginPage(scanner, user);
            } else {
                System.out.println("Unknown role. Cannot proceed.");
            }

        } catch (Exception e) {
            throw new SystemException("Login failed: " + e.getMessage());
        }
    }
}
