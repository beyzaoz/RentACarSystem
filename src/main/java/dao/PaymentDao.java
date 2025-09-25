package dao;

import dao.constant.SqlScriptConstant;
import model.Payment;
import model.vehicle.VehicleBase;
import util.DbUtil;

import java.sql.*;
import java.util.List;

public class PaymentDao implements  BaseDao<Payment>{

    //PAYMENT METHOD SAVE

    public void save(Payment payment){
        try(Connection connection = DbUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.PAYMENT_SAVE);
            ps.setLong(1,payment.getReservation().getId());
            ps.setString(2,payment.getPaymentMethod().name());
            ps.setBigDecimal(3,payment.getTotalamount());
            ResultSet rs =ps.executeQuery();
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment findById(long id) {
        return null;
    }


    @Override
    public void update(Payment payment ,long id) {

    }

    @Override
    public void delete(Payment payment ,long id) {

    }

    @Override
    public List<VehicleBase> allListed(VehicleBase payment, long id,int page) {

        return null;
    }

}
