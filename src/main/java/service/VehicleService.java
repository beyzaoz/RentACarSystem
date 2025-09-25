package service;

import dao.VehicleDao;
import exception.ExceptionMessages;
import exception.SystemException;
import model.User;
import model.enums.Roles;
import model.vehicle.VehicleBase;

import java.util.List;

public class VehicleService {
    private final VehicleDao vehicleDao;

    public VehicleService(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public void vehicleSave(VehicleBase vehicle, User user) throws SystemException {
        if(!Roles.ADMIN.equals(user.getRole())){
            throw new SystemException(ExceptionMessages.USER_NOT_ADMIN);
        }

        vehicle.setCreatedUser(user.getId());
        vehicle.setUpdatedUser(user.getId());
        vehicleDao.save(vehicle);
    }


    public void vehicleUpdate(VehicleBase vehicle, User user) throws SystemException {
        if(!Roles.ADMIN.equals(user.getRole())){
            throw new SystemException(ExceptionMessages.USER_NOT_ADMIN);
        }


        vehicle.setUpdatedUser(user.getId());
        vehicleDao.update(vehicle, vehicle.getId());

    }

    public void vehicleDelete(VehicleBase vehicle, User user) throws SystemException {
        if(!Roles.ADMIN.equals(user.getRole())){
            throw new SystemException(ExceptionMessages.USER_NOT_ADMIN);
        }

        vehicle.setUpdatedUser(user.getId()); //who updated
        vehicleDao.delete(vehicle, vehicle.getId());
        System.out.println("Vehicle Deleted!"); //todo vehicledao-delete e tasi
    }

    public void vehicleListed(VehicleBase vehicle, User user) throws SystemException{
        if(!Roles.ADMIN.equals(user.getRole())){
            throw new SystemException(ExceptionMessages.USER_NOT_ADMIN);
        }

        int page = 1;
        vehicle.setUpdatedUser(user.getId());
        vehicleDao.allListed(vehicle,user.getId(), page);
    }

    public VehicleBase vehicleSearchById(Long vehicleID, User user) throws SystemException {
        if(!Roles.COOPARATE_CUSTOMER.equals(user.getRole()) && !Roles.INDIVIDUAL_CUSTOMER.equals(user.getRole())){
            throw  new SystemException(ExceptionMessages.USER_NOT_CUSTOMER);
        }
        return vehicleDao.findById(vehicleID);

    }

    public List<VehicleBase> vehicleSearchByType(String vehicletype, User user) throws SystemException {
        if(!Roles.COOPARATE_CUSTOMER.equals(user.getRole()) && !Roles.INDIVIDUAL_CUSTOMER.equals(user.getRole())){
            throw  new SystemException(ExceptionMessages.USER_NOT_CUSTOMER);
        }
        return vehicleDao.search(vehicletype);
    }


    }



