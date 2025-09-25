package dao;

import dao.constant.SqlScriptConstant;
import model.Order;
import model.vehicle.VehicleBase;
import util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements BaseDao<Order> {

    public void save(Order order) {
        List<VehicleBase> products = new ArrayList<>();
        try(Connection connection= DbUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.ORDER_SAVE);
            ps.setLong(1,order.getCustomer().getId());
            ps.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            ps.setBigDecimal(3,order.getTotalAmount());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order findById(long id) {
        return null;
    }


    @Override
    public void update(Order order,long id) {

    }

    @Override
    public void delete(Order order ,long id) {

    }

    @Override
    public List<VehicleBase> allListed(VehicleBase order, long id,int page) {

        return null;
    }
}
