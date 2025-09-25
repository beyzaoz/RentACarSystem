import UI.CustomerRegistration;
import UI.LogIn;
import model.User;
import model.enums.Roles;

import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class RentACarMain {
    public static void main(String[] args) {
        //Using here as a UI: Only User Interactions.
        User adminUser = new User(
                "beyza",
                "patika",
                "beypat@patika.com",
                "12345Beypat!",
                "12341234567",
                LocalDate.of(1997, 9, 12),
                Roles.ADMIN
        );

        Scanner s = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("--Welcome to Rent A Car System--");
                System.out.println("1-Registration");
                System.out.println("2-LogIn");
                System.out.println("0-Exit");
                System.out.println("Please Select one option: ");
                String choise = s.nextLine();
                switch(choise){
                    case"1":
                        CustomerRegistration.startReg();
                        break;
                    case "2":
                        LogIn.logInstart();
                        break;
                    case "0":
                        System.out.println("Exit is Successful");
                        return;
                    default:
                        System.out.println("Invalid Choise!!");
                        break;

                }


            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }



    }
    }