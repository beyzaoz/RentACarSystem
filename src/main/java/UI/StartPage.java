package UI;

import java.util.Scanner;

public class StartPage {

    public static void startingPage(){
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
                        Registration.startReg();
                        break;
                    case "2":
                        LogIn.logInstart();
                        break;
                    case "0":
                        System.out.println("Exit is Successful!");
                        System.exit(0); //exit from the loop

                    default:
                        System.out.println("Invalid Choise!!");
                        break;

                }

return;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
