package dao;
import constant.RentAcarConstant;
import dao.constant.SqlScriptConstant;
import exception.ExceptionMessages;
import exception.SystemException;
import model.enums.VehicleAvailable;
import model.vehicle.Car;
import model.vehicle.Helicopter;
import model.vehicle.Motorcycle;
import model.vehicle.VehicleBase;
import util.DbUtil;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jdk.internal.net.http.common.Utils.close;

public class VehicleDao implements BaseDao<VehicleBase>{

    @Override
    public void save(VehicleBase vehicle) {
        try(Connection connection = DbUtil.getConnection()){



            if(vehicle.getVehicleType().toLowerCase().equals("car")){
                PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.CAR_VEHICLE_SAVE);
                Car car =  (Car) vehicle;
                ps.setString(1, vehicle.getVehiclemodel());
                ps.setString(2, vehicle.getVehicleType());
                ps.setDouble(3, vehicle.getHourlyPrice());
                ps.setDouble(4, vehicle.getDailyPrice());
                ps.setDouble(5, vehicle.getWeeklyPrice());
                ps.setDouble(6, vehicle.getMonthlyPrice());
                ps.setLong(7, vehicle.getCreatedUser());
                ps.setLong(8, vehicle.getUpdatedUser());
                ps.setString(9, car.getCarPlateNumber());
                ps.setString(10, vehicle.getAvailable().name());
                ps.setDouble(11, vehicle.getVehiclePrice());

                ps.executeUpdate();
                connection.close();

                System.out.println("Vehicle Added!!");
            }
            if(vehicle.getVehicleType().toLowerCase().equals("helicopter")){
                PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.HELICOPTER_VEHICLE_SAVE);
                Helicopter helicopter =  (Helicopter) vehicle;
                ps.setString(1, vehicle.getVehiclemodel());
                ps.setString(2, vehicle.getVehicleType());
                ps.setDouble(3, vehicle.getHourlyPrice());
                ps.setDouble(4, vehicle.getDailyPrice());
                ps.setDouble(5, vehicle.getWeeklyPrice());
                ps.setDouble(6, vehicle.getMonthlyPrice());
                ps.setLong(7, vehicle.getCreatedUser());
                ps.setLong(8, vehicle.getUpdatedUser());
                ps.setString(9, helicopter.getSerialNumber());
                ps.setString(10, helicopter.getPurpose());
                ps.setString(11, vehicle.getAvailable().name());
                ps.setDouble(12, vehicle.getVehiclePrice());
                ps.executeUpdate();
                connection.close();
                System.out.println("Vehicle Added!!");
            }

            if(vehicle.getVehicleType().toLowerCase().equals("motorcycle")){
                PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.MOTORCYCLE_VEHICLE_SAVE);
                Motorcycle motorcycle =  (Motorcycle) vehicle; //TYPE CASTING
                ps.setString(1, vehicle.getVehiclemodel());
                ps.setString(2, vehicle.getVehicleType());
                ps.setDouble(3, vehicle.getHourlyPrice());
                ps.setDouble(4, vehicle.getDailyPrice());
                ps.setDouble(5, vehicle.getWeeklyPrice());
                ps.setDouble(6, vehicle.getMonthlyPrice());
                ps.setLong(7, vehicle.getCreatedUser());
                ps.setLong(8, vehicle.getUpdatedUser());
                ps.setString(9, motorcycle.getMotortype());
                ps.setString(10, motorcycle.getMplatenumber());
                ps.setString(11, vehicle.getAvailable().name());
                ps.setDouble(12, vehicle.getVehiclePrice());

                ps.executeUpdate();
                connection.close();
                System.out.println("Vehicle Added!!");


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private VehicleAvailable parseVehicleAvailable(String value) {

        if (value == null || value.isBlank()) {
            return VehicleAvailable.AVAILABLE; // varsayılan değer
        }
        try {
            return VehicleAvailable.valueOf(value);
        } catch (IllegalArgumentException e) {
            System.out.println("Warning: Invalid vehicle available value: " + value);
            return VehicleAvailable.AVAILABLE;
        }
    }


    @Override
    public VehicleBase findById(long id ) throws SystemException {
        VehicleBase vehicle = new VehicleBase();
        try(Connection connection = DbUtil.getConnection()) {
            try (PreparedStatement pscar = connection.prepareStatement(SqlScriptConstant.FIND_VEHICLE_BY_ID_CAR);) {

                pscar.setLong(1, id);
              try(  ResultSet rs = pscar.executeQuery()){
                while (rs.next()) {
                    Car car = new Car();
                    car.setId(rs.getLong("id"));
                    car.setVehiclemodel(rs.getString("vehicle_model"));
                    car.setVehicleType(rs.getString("vehicletype"));
                    car.setHourlyPrice(rs.getDouble("hourlyprice"));
                    car.setDailyPrice(rs.getDouble("dailyprice"));
                    car.setWeeklyPrice(rs.getDouble("weeklyprice"));
                    car.setMonthlyPrice(rs.getDouble("monthlyprice"));
                    car.setAvailable(parseVehicleAvailable(rs.getString("vehicleavailable")));
                    car.setCarPlateNumber(rs.getString("vehicleplatenumber"));
                    return car;
                }}


            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (PreparedStatement pshelicopter = connection.prepareStatement(SqlScriptConstant.FIND_VEHICLE_BY_ID_HELICOPTER);) {

                pshelicopter.setLong(1, id);
               try( ResultSet rs = pshelicopter.executeQuery()){
                while (rs.next()) {
                    Helicopter helicopter = new Helicopter();
                    helicopter.setId(rs.getLong("id"));
                    helicopter.setVehiclemodel(rs.getString("vehicle_model"));
                    helicopter.setVehicleType(rs.getString("vehicletype"));
                    helicopter.setHourlyPrice(rs.getDouble("hourlyprice"));
                    helicopter.setDailyPrice(rs.getDouble("dailyprice"));
                    helicopter.setWeeklyPrice(rs.getDouble("weeklyprice"));
                    helicopter.setMonthlyPrice(rs.getDouble("monthlyprice"));
                    helicopter.setAvailable(VehicleAvailable.valueOf(rs.getString("vehicleavailable")));
                    helicopter.setSerialNumber(rs.getString("serialnumber"));
                    helicopter.setPurpose(rs.getString("purpose"));
                    return helicopter;
                }
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (PreparedStatement psmotorcycle = connection.prepareStatement(SqlScriptConstant.FIND_VEHICLE_BY_ID_MOTORCYCLE);) {
                psmotorcycle.setLong(1, id);
               try( ResultSet rs = psmotorcycle.executeQuery()){
                while (rs.next()) {
                    Motorcycle motorcycle = new Motorcycle();
                    motorcycle.setId(rs.getLong("id"));
                    motorcycle.setVehiclemodel(rs.getString("vehicle_model"));
                    motorcycle.setVehicleType(rs.getString("vehicletype"));
                    motorcycle.setHourlyPrice(rs.getDouble("hourlyprice"));
                    motorcycle.setDailyPrice(rs.getDouble("dailyprice"));
                    motorcycle.setWeeklyPrice(rs.getDouble("weeklyprice"));
                    motorcycle.setMonthlyPrice(rs.getDouble("monthlyprice"));
                    motorcycle.setAvailable(VehicleAvailable.valueOf(rs.getString("vehicleavailable")));
                    motorcycle.setMplatenumber(rs.getString("mplatenumber"));
                    motorcycle.setMotortype(rs.getString("motortype"));
                    return motorcycle;

                }
               }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.getMessage();
        }

        return null;
    }


    public List<VehicleBase> searchByType(String vehicleType) {
        //SEARCH BY TYPE
        List<VehicleBase> vehicle = new ArrayList<>();
        try(Connection connection = DbUtil.getConnection()) {
            try (PreparedStatement pscar = connection.prepareStatement(SqlScriptConstant.SEARCH_VEHICLE_BY_ID_CAR)) {

                pscar.setString(1, vehicleType);
                try(ResultSet rs = pscar.executeQuery()) {
                    while (rs.next()) {
                        Car car = new Car();
                        car.setId(rs.getLong("id"));
                        car.setVehiclemodel(rs.getString("vehicle_model"));
                        car.setVehicleType(rs.getString("vehicletype"));
                        car.setHourlyPrice(rs.getDouble("hourlyprice"));
                        car.setDailyPrice(rs.getDouble("dailyprice"));
                        car.setWeeklyPrice(rs.getDouble("weeklyprice"));
                        car.setMonthlyPrice(rs.getDouble("monthlyprice"));
                        car.setAvailable(parseVehicleAvailable(rs.getString("vehicleavailable")));
                        car.setCarPlateNumber(rs.getString("vehicleplatenumber"));
                        vehicle.add(car);
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }


            try (PreparedStatement pshelicopter = connection.prepareStatement(SqlScriptConstant.SEARCH_VEHICLE_BY_ID_HELICOPTER);) {

                pshelicopter.setString(1, vehicleType);
                try(ResultSet rs = pshelicopter.executeQuery()) {
                    while (rs.next()) {
                        Helicopter helicopter = new Helicopter();
                        helicopter.setId(rs.getLong("id"));
                        helicopter.setVehiclemodel(rs.getString("vehicle_model"));
                        helicopter.setVehicleType(rs.getString("vehicletype"));
                        helicopter.setHourlyPrice(rs.getDouble("hourlyprice"));
                        helicopter.setDailyPrice(rs.getDouble("dailyprice"));
                        helicopter.setWeeklyPrice(rs.getDouble("weeklyprice"));
                        helicopter.setMonthlyPrice(rs.getDouble("monthlyprice"));
                        helicopter.setAvailable(VehicleAvailable.valueOf(rs.getString("vehicleavailable")));
                        helicopter.setSerialNumber(rs.getString("serialnumber"));
                        helicopter.setPurpose(rs.getString("purpose"));
                        vehicle.add(helicopter);
                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }

            try (PreparedStatement psmotorcycle = connection.prepareStatement(SqlScriptConstant.SEARCH_VEHICLE_BY_ID_MOTORCYCLE);) {

                psmotorcycle.setString(1, vehicleType);
               try( ResultSet rs = psmotorcycle.executeQuery()){
                while (rs.next()) {
                    Motorcycle motorcycle = new Motorcycle();
                    motorcycle.setId(rs.getLong("id"));
                    motorcycle.setVehiclemodel(rs.getString("vehicle_model"));
                    motorcycle.setVehicleType(rs.getString("vehicletype"));
                    motorcycle.setHourlyPrice(rs.getDouble("hourlyprice"));
                    motorcycle.setDailyPrice(rs.getDouble("dailyprice"));
                    motorcycle.setWeeklyPrice(rs.getDouble("weeklyprice"));
                    motorcycle.setMonthlyPrice(rs.getDouble("monthlyprice"));
                    motorcycle.setAvailable(VehicleAvailable.valueOf(rs.getString("vehicleavailable")));
                    motorcycle.setMplatenumber(rs.getString("mplatenumber"));
                    motorcycle.setMotortype(rs.getString("motortype"));
                    vehicle.add(motorcycle);
                }
               }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }


    public List<VehicleBase> searchByBrand(String vehicleBrand) {
        //SEARCH BY Brand
        List<VehicleBase> vehicle = new ArrayList<>();
        try(Connection connection = DbUtil.getConnection()){

        try ( PreparedStatement pscar = connection.prepareStatement(SqlScriptConstant.SEARCH_VEHICLE_BY_BRAND_CAR);) {

            pscar.setString(1, vehicleBrand);
           try( ResultSet rs = pscar.executeQuery()){
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getLong("id"));
                car.setVehiclemodel(rs.getString("vehicle_model"));
                car.setVehicleType(rs.getString("vehicletype"));
                car.setHourlyPrice(rs.getDouble("hourlyprice"));
                car.setDailyPrice(rs.getDouble("dailyprice"));
                car.setWeeklyPrice(rs.getDouble("weeklyprice"));
                car.setMonthlyPrice(rs.getDouble("monthlyprice"));
                car.setAvailable(VehicleAvailable.valueOf(rs.getString("vehicleavailable")));
                car.setCarPlateNumber(rs.getString("vehicleplatenumber"));
                vehicle.add(car);
            }
           }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        try (PreparedStatement pshelicopter = connection.prepareStatement(SqlScriptConstant.SEARCH_VEHICLE_BY_BRAND_HELICOPTER);) {

            pshelicopter.setString(1, vehicleBrand);
           try( ResultSet rs = pshelicopter.executeQuery()) {
               while (rs.next()) {
                   Helicopter helicopter = new Helicopter();
                   helicopter.setId(rs.getLong("id"));
                   helicopter.setVehiclemodel(rs.getString("vehicle_model"));
                   helicopter.setVehicleType(rs.getString("vehicletype"));
                   helicopter.setHourlyPrice(rs.getDouble("hourlyprice"));
                   helicopter.setDailyPrice(rs.getDouble("dailyprice"));
                   helicopter.setWeeklyPrice(rs.getDouble("weeklyprice"));
                   helicopter.setMonthlyPrice(rs.getDouble("monthlyprice"));
                   helicopter.setAvailable(VehicleAvailable.valueOf(rs.getString("vehicleavailable")));
                   helicopter.setSerialNumber(rs.getString("serialnumber"));
                   helicopter.setPurpose(rs.getString("purpose"));
                   vehicle.add(helicopter);
               }
           }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement psmotorcycle = connection.prepareStatement(SqlScriptConstant.SEARCH_VEHICLE_BY_BRAND_MOTORCYCLE)) {
            psmotorcycle.setString(1, vehicleBrand);
            try(ResultSet rs = psmotorcycle.executeQuery()) {
                while (rs.next()) {
                    Motorcycle motorcycle = new Motorcycle();
                    motorcycle.setId(rs.getLong("id"));
                    motorcycle.setVehiclemodel(rs.getString("vehicle_model"));
                    motorcycle.setVehicleType(rs.getString("vehicletype"));
                    motorcycle.setHourlyPrice(rs.getDouble("hourlyprice"));
                    motorcycle.setDailyPrice(rs.getDouble("dailyprice"));
                    motorcycle.setWeeklyPrice(rs.getDouble("weeklyprice"));
                    motorcycle.setMonthlyPrice(rs.getDouble("monthlyprice"));
                    motorcycle.setAvailable(VehicleAvailable.valueOf(rs.getString("vehicleavailable")));
                    motorcycle.setMplatenumber(rs.getString("mplatenumber"));
                    motorcycle.setMotortype(rs.getString("motortype"));
                    vehicle.add(motorcycle);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
    }


    public List<VehicleBase> searchByPrice(String rentalType, BigDecimal minPrice, BigDecimal maxPrice) {
        List<VehicleBase> vehicles = new ArrayList<>();

        rentalType = rentalType.toLowerCase();

        String priceColumn = switch (rentalType) {
            case "hourly" -> "hourlyprice";
            case "daily" -> "dailyprice";
            case "weekly" -> "weeklyprice";
            case "monthly" -> "monthlyprice";
            default -> throw new IllegalArgumentException("Invalid rental type: " + rentalType);
        };


        String SEARCH_VEHICLE_BY_PRICE_DAILY =
                "SELECT id, vehicletype, vehicle_model, hourlyprice, dailyprice, weeklyprice, monthlyprice, vehicleavailable, " +
                        "vehicleplatenumber, NULL AS serialnumber, NULL AS motortype, NULL AS mplatenumber, NULL AS purpose " +
                        "FROM car WHERE " + priceColumn + " BETWEEN ? AND ? " +
                        "UNION ALL " +
                        "SELECT id, vehicletype, vehicle_model, hourlyprice, dailyprice, weeklyprice, monthlyprice, vehicleavailable, " +
                        "NULL AS vehicleplatenumber, serialnumber, NULL AS motortype, NULL AS mplatenumber, purpose " +
                        "FROM helicopter WHERE " + priceColumn + " BETWEEN ? AND ? " +
                        "UNION ALL " +
                        "SELECT id, vehicletype, vehicle_model, hourlyprice, dailyprice, weeklyprice, monthlyprice, vehicleavailable, " +
                        "NULL AS vehicleplatenumber, NULL AS serialnumber, motortype, mplatenumber, NULL AS purpose " +
                        "FROM motorcycle WHERE " + priceColumn + " BETWEEN ? AND ? " +
                        "ORDER BY id ASC";

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(SEARCH_VEHICLE_BY_PRICE_DAILY)) {

            // Fiyat parametrelerini sırayla set et
            for (int i = 0; i < 3; i++) {
                ps.setBigDecimal(i * 2 + 1, minPrice);
                ps.setBigDecimal(i * 2 + 2, maxPrice);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String type = rs.getString("vehicletype").toLowerCase();
                    VehicleBase vehicle = switch (type) {
                        case "car" -> new Car();
                        case "helicopter" -> new Helicopter();
                        case "motorcycle" -> new Motorcycle();
                        default -> null;
                    };

                    if (vehicle != null) {
                        vehicle.setId(rs.getLong("id"));
                        vehicle.setVehiclemodel(rs.getString("vehicle_model"));
                        vehicle.setVehicleType(type);
                        vehicle.setHourlyPrice(rs.getBigDecimal("hourlyprice").doubleValue());
                        vehicle.setDailyPrice(rs.getBigDecimal("dailyprice").doubleValue());
                        vehicle.setWeeklyPrice(rs.getBigDecimal("weeklyprice").doubleValue());
                        vehicle.setMonthlyPrice(rs.getBigDecimal("monthlyprice").doubleValue());
                        vehicle.setAvailable(VehicleAvailable.valueOf(rs.getString("vehicleavailable")));

                        if (vehicle instanceof Car car) {
                            car.setCarPlateNumber(rs.getString("vehicleplatenumber"));
                        } else if (vehicle instanceof Helicopter heli) {
                            heli.setSerialNumber(rs.getString("serialnumber"));
                            heli.setPurpose(rs.getString("purpose"));
                        } else if (vehicle instanceof Motorcycle moto) {
                            moto.setMotortype(rs.getString("motortype"));
                            moto.setMplatenumber(rs.getString("mplatenumber"));
                        }
                        vehicles.add(vehicle);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error searching vehicles by price", e);
        }

        return vehicles;
    }


    @Override
    public void update(VehicleBase vehicle,long id) throws SystemException {
        String tableName;
        switch (vehicle.getVehicleType().toLowerCase()) {
            case "car": tableName = "car"; break;
            case "helicopter": tableName = "helicopter"; break;
            case "motorcycle": tableName = "motorcycle"; break;
            default: throw new SystemException(ExceptionMessages.ILLEGAL_VEHICLE_TYPE);
        }

        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
        List<Object> params = new ArrayList<>();

        // COMMON FIELDS
        if (vehicle.getHourlyPrice() != 0) { sql.append("hourlyprice=?, "); params.add(vehicle.getHourlyPrice()); }
        if (vehicle.getDailyPrice() != 0)  { sql.append("dailyprice=?, "); params.add(vehicle.getDailyPrice()); }
        if (vehicle.getWeeklyPrice() != 0) { sql.append("weeklyprice=?, "); params.add(vehicle.getWeeklyPrice()); }
        if (vehicle.getMonthlyPrice() != 0){ sql.append("monthlyprice=?, "); params.add(vehicle.getMonthlyPrice()); }
        if (vehicle.getAvailable() != null){ sql.append("vehicleavailable=?, "); params.add(vehicle.getAvailable().name()); }
        if (vehicle.getVehiclemodel() != null){ sql.append("vehicle_model=?, "); params.add(vehicle.getVehiclemodel()); }

        // SPECIAL FIELDS
        if (vehicle instanceof Car) {
            Car car = (Car) vehicle;
            if (car.getCarPlateNumber() != null) {
                sql.append("vehicleplatenumber=?, ");
                params.add(car.getCarPlateNumber());
            }
        }
        if (vehicle instanceof Helicopter) {
            Helicopter h = (Helicopter) vehicle;
            if (h.getSerialNumber() != null) { sql.append("serialnumber=?, "); params.add(h.getSerialNumber()); }
            if (h.getPurpose() != null) { sql.append("purpose=?, "); params.add(h.getPurpose()); }
        }
        if (vehicle instanceof Motorcycle) {
            Motorcycle m = (Motorcycle) vehicle;
            if (m.getMplatenumber() != null) { sql.append("mplatenumber=?, "); params.add(m.getMplatenumber()); }
            if (m.getMotortype() != null) { sql.append("motortype=?, "); params.add(m.getMotortype()); }
        }

        // sondaki virgülü sil

        if (sql.toString().endsWith(", ")) {
            sql.setLength(sql.length() - 2);
        }

        sql.append(" WHERE id=?");
        params.add(vehicle.getId());

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));

            }

            System.out.println("Updated!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void delete(long id) {

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT vehicletype FROM vehicle WHERE id = ?")) {
            // Önce tipini öğren

            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){

            if (rs.next()) {
                String type = rs.getString("vehicle_type");

                PreparedStatement deletePs = null;
                if (type.equalsIgnoreCase("car")) {
                    deletePs = connection.prepareStatement(SqlScriptConstant.CAR_VEHICLE_DELETE);
                } else if (type.equalsIgnoreCase("helicopter")) {
                    deletePs = connection.prepareStatement(SqlScriptConstant.HELICOPTER_VEHICLE_DELETE);
                } else if (type.equalsIgnoreCase("motorcycle")) {
                    deletePs = connection.prepareStatement(SqlScriptConstant.MOTORCYCLE_VEHICLE_DELETE);
                }

                if (deletePs != null) {
                    deletePs.setLong(1, id);
                    deletePs.executeUpdate();
                }
            }
            }

        } catch (SQLException e) {
            System.out.println("Delete is not apply!: " + e.getMessage());
        }

    }


    @Override
    public List<VehicleBase> allListed(VehicleBase vehicleBase, long id,int page) {
        List<VehicleBase> vehicles = new ArrayList<>();
        //PAGINATION:
        int pageSize = RentAcarConstant.PAGE_SIZE;
        int totalRecords = 0;
        try(Connection connection = DbUtil.getConnection()) {

            try (PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.COUNT_RECORD_NUMBER)) {

                try (ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        totalRecords = rs.getInt(1);
                    }
                }

            } catch (SQLException e) {
                System.out.println("An error occurred while getting the vehicle count: " + e.getMessage());
            }


            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);
            int offset = (page ) * pageSize;


            try (PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.ALL_LISTED);) {


                ps.setInt(1, pageSize);
                ps.setInt(2, offset);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String type = rs.getString("vehicletype").toLowerCase();
                        VehicleBase vb = switch(type) {
                            case "car" -> new Car(rs.getString("vehicleplatenumber"));
                            case "helicopter" -> new Helicopter(rs.getString("serialnumber"), rs.getString("purpose"));
                            case "motorcycle" -> new Motorcycle(rs.getString("mplatenumber"), rs.getString("motortype"));
                            default -> new VehicleBase();
                        };


                        vb.setId(rs.getLong("id"));
                        vb.setVehicleType(rs.getString("vehicletype"));
                        vb.setVehiclemodel(rs.getString("vehicle_model"));
                        vb.setHourlyPrice(rs.getDouble("hourlyprice"));
                        vb.setDailyPrice(rs.getDouble("dailyprice"));
                        vb.setWeeklyPrice(rs.getDouble("weeklyprice"));
                        vb.setMonthlyPrice(rs.getDouble("monthlyprice"));
                        vb.setAvailable(parseVehicleAvailable(rs.getString("vehicleavailable")));

                        vehicles.add(vb);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return vehicles;
    }

}









