package service;

import dao.UserDAO;
import exception.ExceptionMessages;
import exception.SystemException;
import model.User;
import model.enums.Roles;
import util.PasswordUtil;

import java.time.LocalDate;

public class UserService {

    private UserDAO UserDAO;

    public UserService() {
        this.UserDAO = new UserDAO();
    }

    //USER SAVED
    public void save(String fname, String lname, String bDate, String email, String password, Roles role, String dl) {

        //if email already exist chck:
        boolean isExist = UserDAO.existByEmail(email);

        if(isExist){
            try {
                throw new SystemException(ExceptionMessages.EMAIL_ALREADY_EXIST);
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }
        }
        LocalDate date = LocalDate.parse(bDate);

        try {
            User customer = new User(fname,lname,email, PasswordUtil.hash(password), dl,date,role);
            UserDAO.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
         //TODO empty veya null hata kontrolu

    }

    //USER LOGIN
    public User login(String email, String password){  //todo
        //if email not exist chck:
        boolean isExist = UserDAO.existByEmail(email);

        if(!isExist){
            try {
                throw new SystemException(ExceptionMessages.EMAIL_NOT_EXIST);
            } catch (SystemException e) {
                throw new RuntimeException(e);
            }
        }

        String hashPassword = PasswordUtil.hash(password);
        User foundCustomer = UserDAO.findByEmail(email);

        //Paswword check
        boolean pwEquals = foundCustomer.getPassword().equals(hashPassword);

            if(pwEquals){
                try {
                    throw new SystemException(ExceptionMessages.CUSTOMER_EMAIL_OR_PS_NOT_EXIST);
                } catch (SystemException e) {
                    throw new RuntimeException(e);
                }
            }
        //---------------------------------

return foundCustomer ;
    }


}
