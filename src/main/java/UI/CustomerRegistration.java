package UI;

import model.User;
import model.enums.Roles;
import service.UserService;
import util.PasswordUtil;

import java.time.LocalDate;
import java.util.Scanner;

public class CustomerRegistration {

    public static void startReg(){
        registration(new Scanner(System.in));
    }
    private static void registration(Scanner s) {
        //Regexs starts
        String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"; //basic email format
        String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$";
        //at least one lowercase letter (a-z), at least one uppercase letter (A-Z), at least one digit (0-9),
        //at least one special character,between length of the password as 8 to 20 characters.
        String DL_REGEX = "^.{8,}$"; // minimum length of the password as 8 characters.

        //Regexs finish
        User user = new User();


        System.out.print(" First Name: ");
        String fname = s.nextLine().trim();


        System.out.print(" Last Name: ");
        String lname = s.nextLine().trim();


        System.out.println("BirthDate(yyyy-MM-dd): ");
        String birthdate = s.nextLine().trim();

        //Regex match check
        String email;
        while (true) {
            System.out.print("Email: ");
            email = s.nextLine().trim();
            user.setEmail(email);
            if (email.matches(EMAIL_REGEX)) break;
            System.out.println("Invalid email format. Please enter a valid one.");
        }

        // Password
        String password;
        while (true) {
            System.out.print("Password: ");
            password = s.nextLine().trim();
            if (password.matches(PASSWORD_REGEX)) break;
            System.out.println("Invalid password format. Please enter a valid one.");
        }

        // Role
        Roles r = null;
        while (true) {

            System.out.print("A for Admin or C for Customer: ");
            String roleInput = s.nextLine().toUpperCase().trim();

            if (roleInput.equals("A")) {
                r = Roles.ADMIN;
                break; // bu noktada döngüden çıkılır
            } else if (roleInput.equals("C")) {
                while (true) {
                    System.out.print("Select User Type: I for Individual or O for Corporate: ");
                    String customerType = s.nextLine().toUpperCase().trim();
                    if (customerType.equals("I")) {
                        r = Roles.INDIVIDUAL_CUSTOMER;
                        break;
                    } else if (customerType.equals("O")) {
                        r = Roles.COOPARATE_CUSTOMER;
                        break;
                    } else {
                        System.out.println("Invalid choice. Please enter 'I' or 'O'.");
                    }
                }
                break; // dıştaki while’dan da çıkılır
            } else {
                System.out.println("Invalid role. Please enter 'A' or 'C'.");
            }
        }

        // Driver License
        String dl;
        while (true) {
            System.out.print("Driver License: ");
            dl = s.nextLine().trim();
            if (dl.matches(DL_REGEX)) break;
            System.out.println("Invalid driver license format. Please enter a valid one.");
        }


            UserService customerService = new UserService();
            customerService.save(fname, lname, birthdate, email,PasswordUtil.hash(password), r, dl);
        System.out.println("Successfully Registered! ");



    }}
