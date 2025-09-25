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

    private static void logInPart(Scanner s) throws SystemException {
       // String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"; //basic email format
        //String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";

        UserService customerService = new UserService();
        UserDAO userDAO = new UserDAO();

        System.out.print("Email: ");
        String email = s.nextLine().trim().toLowerCase();

        System.out.print("Password: ");
        String password = s.nextLine().trim();



        try {
            //customerService.login(email, password);
            User user = customerService.login(email, PasswordUtil.hash(password));

            if (user == null) {
                throw new SystemException(ExceptionMessages.LOGIN_FAILED);
            }
            boolean loggedIn = true;

            while (loggedIn) {

                if (Roles.ADMIN.equals(user.getRole())) {
                    try {
                        AdminLogIn.adminLogInPanel(s, user);
                    } catch (Exception e) {
                        System.out.println("Admin Panel Error: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;
                }

                if (Roles.COOPARATE_CUSTOMER.equals(user.getRole())) {
                    try {
                        CustomerLogin.customerLoginPage(s,user);
                    } catch (Exception e) {
                        System.out.println("Login Panel Error: " + e.getMessage());
                    }
                    break;

                }

                if (Roles.INDIVIDUAL_CUSTOMER.equals(user.getRole())) {
                    try {
                        CustomerLogin.customerLoginPage(s,user);
                    } catch (Exception e) {
                        System.out.println("Login Panel Error: " + e.getMessage());
                    }
                    break;
                }
            }
        }catch (Exception e) {
          throw e;
        }
        }

    public static void adminLogIn(Scanner s){

    }
}

