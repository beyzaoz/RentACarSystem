package dao;
import dao.constant.SqlScriptConstant;
import model.Order;
import model.User;
import model.enums.OrderStatue;
import model.enums.VehicleAvailable;
import model.vehicle.Car;
import model.vehicle.Helicopter;
import model.vehicle.Motorcycle;
import model.vehicle.VehicleBase;
import util.DbUtil;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements BaseDao<Order> {

    public static List<Order> usersoldOrders(Long customerId) {
        List<Order> oldOrders = new ArrayList<>();
        UserDAO userDao = new UserDAO(); // UserDao nesnesi oluşturuluyor

        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.FIND_OLD_ORDERS);
            ps.setLong(1, customerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Order o = new Order();

                    long dbCustomerId = rs.getLong("customer_id");

                    o.setId(rs.getLong("id"));
                    o.setCustomerId(dbCustomerId); // DB'den gelen customerId
                    o.setVehicleId(rs.getLong("vehicle_id"));
                    o.setStartDateTime(rs.getTimestamp("start_date_time").toLocalDateTime());
                    o.setEndDateTime(rs.getTimestamp("end_date_time").toLocalDateTime());
                    o.setOrderDate(rs.getTimestamp("orderdate").toLocalDateTime());
                    o.setTotalAmount(rs.getBigDecimal("total_amount"));
                    o.setStatue(OrderStatue.valueOf(rs.getString("status")));

                    User u = userDao.findById(dbCustomerId);
                    o.setUser(u);

                    oldOrders.add(o);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return oldOrders;
    }


    public void save(Order order) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.ORDER_SAVE)) {
                    ps.setLong(1, order.getUser().getId());                   // customer_id
                    ps.setLong(2, order.getVehicle().getId());               // vehicle_id
                    ps.setTimestamp(3, Timestamp.valueOf(order.getStartDateTime())); // start_date_time
                    ps.setTimestamp(4, Timestamp.valueOf(order.getEndDateTime()));   // end_date_time
                    ps.setTimestamp(5, Timestamp.valueOf(order.getOrderDate()));     // createddate
                    ps.setBigDecimal(6, order.getTotalAmount());            // total_amount

                    ps.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    public boolean isVehicleAvailable(long vehicleId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SqlScriptConstant.VEHICLE_IS_AVAILABLE)) {
            ps.setLong(1, vehicleId);
            ps.setObject(2, endDateTime);   // Yeni rezervasyon bitiş zamanı
            ps.setObject(3, startDateTime); // Yeni rezervasyon başlangıç zamanı

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // 0 ise araç müsait
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // hata olursa araç müsait değil say
    }

    public void cancel(Order order) {
            if (order == null) return;

            try (Connection connection = DbUtil.getConnection();
                 PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.ORDER_DELETE)) {

                ps.setString(1, OrderStatue.CANCELED.name()); // STATUE = CANCELED
                ps.setLong(2, order.getId());
                ps.executeUpdate();

                // Local Order nesnesini de güncelle
                order.setStatue(OrderStatue.CANCELED);

                System.out.println("Order with ID " + order.getId() + " has been canceled.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to cancel order: " + e.getMessage());
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
    public void delete(long id) {

    }



    @Override
    public List<VehicleBase> allListed(VehicleBase order, long id,int page) {

        return null;
    }

}
