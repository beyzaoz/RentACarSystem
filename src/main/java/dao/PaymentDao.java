package dao;

import dao.constant.SqlScriptConstant;
import model.Order;
import model.Payment;
import model.vehicle.VehicleBase;
import util.DbUtil;

import java.sql.*;
import java.util.List;

public class PaymentDao implements  BaseDao<Payment>{

    //PAYMENT METHOD SAVE

    public void save(Payment payment){
        if (payment.getPaymentMethod() == null) {
            throw new IllegalArgumentException("PaymentMethod cannot be null");
        }

        String sql = "INSERT INTO payment (order_id, payment_method, total_amount) VALUES (?, ?, ?)";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.PAYMENT_SAVE)) {

            ps.setLong(1, payment.getOrder().getId());
            ps.setString(2, payment.getPaymentMethod().name());
            ps.setBigDecimal(3, payment.getTotalAmount());

            ps.executeUpdate();
//            try (ResultSet rs = ps.getGeneratedKeys()) {
//                if (rs.next()) {
//                    payment.setId(rs.getLong(1));
//                }
//            }

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
    public void delete(long id) {

    }

    @Override
    public List<VehicleBase> allListed(VehicleBase payment, long id,int page) {

        return null;
    }

}
