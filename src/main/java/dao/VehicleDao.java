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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao implements BaseDao<VehicleBase>{

    public List<VehicleBase> searrchByBrand(String vehiclebrand) {


        return List.of();
    }

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

                ps.executeUpdate();

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
                ps.executeUpdate();
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

                ps.executeUpdate();
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
        try(Connection connection = DbUtil.getConnection()){

            PreparedStatement pscar = connection.prepareStatement(SqlScriptConstant.FIND_VEHICLE_BY_ID_CAR);
            pscar.setLong(1,id);
            ResultSet rs = pscar.executeQuery();
            while(rs.next()){
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
                return  car;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(Connection connection = DbUtil.getConnection()) {
            PreparedStatement pshelicopter = connection.prepareStatement(SqlScriptConstant.FIND_VEHICLE_BY_ID_HELICOPTER);
            pshelicopter.setLong(1,id);
            ResultSet rs = pshelicopter.executeQuery();
            while(rs.next()){
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(Connection connection = DbUtil.getConnection()) {
            PreparedStatement psmotorcycle = connection.prepareStatement(SqlScriptConstant.FIND_VEHICLE_BY_ID_MOTORCYCLE);
            psmotorcycle.setLong(1,id);
            ResultSet rs = psmotorcycle.executeQuery();
            while(rs.next()){
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<VehicleBase> search(String vehicleType) {
        //SEARCH BY TYPE
        List<VehicleBase> vehicle = new ArrayList<>();
        try(Connection connection = DbUtil.getConnection()){

            PreparedStatement pscar = connection.prepareStatement(SqlScriptConstant.SEARCH_VEHICLE_BY_ID_CAR);
            pscar.setString(1,vehicleType);
            ResultSet rs = pscar.executeQuery();
            while(rs.next()){
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


        } catch (SQLException e) {
            e.printStackTrace();
        }


        try(Connection connection = DbUtil.getConnection()) {
            PreparedStatement pshelicopter = connection.prepareStatement(SqlScriptConstant.SEARCH_VEHICLE_BY_ID_HELICOPTER);
            pshelicopter.setString(1,vehicleType);
            ResultSet rs = pshelicopter.executeQuery();
            while(rs.next()){
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


        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(Connection connection = DbUtil.getConnection()) {
            PreparedStatement psmotorcycle = connection.prepareStatement(SqlScriptConstant.SEARCH_VEHICLE_BY_ID_MOTORCYCLE);
            psmotorcycle.setString(1,vehicleType);
            ResultSet rs = psmotorcycle.executeQuery();
            while(rs.next()){
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


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicle;
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

            int rows = ps.executeUpdate();
            System.out.println(rows + " row(s) updated in " + tableName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(VehicleBase vehicle,long id) {

        try(Connection connection = DbUtil.getConnection()){
            if(vehicle.getVehicleType().equalsIgnoreCase("car")){
                PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.CAR_VEHICLE_DELETE);
                ps.setLong(1,id);
                ps.executeUpdate();
            }
            if(vehicle.getVehicleType().equalsIgnoreCase("helicopter")){
                PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.MOTORCYCLE_VEHICLE_DELETE);
                ps.executeUpdate();
                ps.setLong(1,id);

            }
            if(vehicle.getVehicleType().equalsIgnoreCase("motorcycle")){
                PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.HELICOPTER_VEHICLE_DELETE);
                ps.executeUpdate();
                ps.setLong(1,id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<VehicleBase> allListed(VehicleBase vehicleBase, long id,int page) {
        //PAGINATIONS

        List<VehicleBase> vehicles = new ArrayList<>();
        //PAGINATION:
        int pageSize = RentAcarConstant.PAGE_SIZE;
        int totalRecords = 0;

        try(Connection connection = DbUtil.getConnection()){
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.COUNT_RECORD_NUMBER);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                totalRecords = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while getting the vehicle count: " +e.getMessage());
        }


        int totalPages = (int) Math.ceil((double) totalRecords/pageSize);
        int offset = (page) * pageSize;


        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SqlScriptConstant.ALL_LISTED);

            ps.setInt(1,pageSize);
            ps.setInt(2,offset);

            try(ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                String type = rs.getString("vehicletype").toLowerCase();
                VehicleBase vb;

                switch (type) {
                    case "car" -> {
                        Car car = new Car();
                        car.setCarPlateNumber(rs.getString("vehicleplatenumber"));
                        vb = car;
                    }
                    case "helicopter" -> {
                        Helicopter heli = new Helicopter();
                        heli.setSerialNumber(rs.getString("serialnumber"));
                        heli.setPurpose(rs.getString("purpose"));
                        vb = heli;
                    }
                    case "motorcycle" -> {
                        Motorcycle moto = new Motorcycle();
                        moto.setMplatenumber(rs.getString("mplatenumber"));
                        moto.setMotortype(rs.getString("motortype"));
                        vb = moto;
                    }
                    default -> vb = new VehicleBase();
                }

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return vehicles;
    }

    }









