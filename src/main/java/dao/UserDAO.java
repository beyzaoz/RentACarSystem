package dao;

import dao.constant.SqlScriptConstant;
import model.User;
import model.enums.Roles;
import model.vehicle.VehicleBase;
import util.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements BaseDao<User> {

    public void save(User customer) {

        try(Connection connection = DbUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.USER_SAVE)){

            //Determine which ? is get  which value
            ps.setString(1, customer.getFirst_name());
            ps.setString(2, customer.getLast_name());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getPassword());
            ps.setString(5, customer.getDriver_license());
            ps.setDate(6, Date.valueOf(customer.getBirth_date()));
            ps.setString(7, String.valueOf(customer.getRole().name()));
            ps.executeUpdate(); // sent to database
            connection.setAutoCommit(false); //close auto committ
            connection.commit(); //do it manually

        } catch (Exception e) {
            //connection.rollback(); // rollback if it is wrong.
            e.printStackTrace();

        }
    }

    @Override
    public User findById(long id) {
        return null;
    }



    @Override
    public void update(User user ,long id) {

    }

    @Override
    public void delete(long id) {

    }

    @Override
    public List<VehicleBase> allListed(VehicleBase user , long id,int page) {

        return null;
    }

    public boolean existByEmail(String email) {

        //Email is Exist or not
        List<User> customers = new ArrayList<>();
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.USER_EXIST_EMAIL)){

            ps.setString(1,email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // It will be true if same email is exist
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return false;
    }

    public User findByEmail(String email) {
        User customer = new User();
        try(Connection connection = DbUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.USER_EXIST_EMAIL);){

            //JDBC Connections

            ps.setString(1,email.trim());
            ResultSet rs =ps.executeQuery();
            if (rs.next()) {

                //This process is called converting the data from ResultSet into Java objects (Product).
                customer.setId(rs.getLong("id"));
                customer.setFirst_name(rs.getString("first_name"));
                customer.setLast_name(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setRole(Roles.valueOf(rs.getString("roles")));
                customer.setDriver_license(rs.getString("driver_license"));
                customer.setBirth_date(rs.getDate("birth_date").toLocalDate());
                customer.setCurrentDate(new Timestamp(rs.getDate("createdDate").getTime()).toLocalDateTime());
                customer.setUpdatedDate(new Timestamp(rs.getDate("updatedDate").getTime()).toLocalDateTime());

                return customer;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

}
