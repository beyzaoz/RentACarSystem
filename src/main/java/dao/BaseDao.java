package dao;

import exception.SystemException;
import model.vehicle.VehicleBase;

import java.util.List;

public interface BaseDao<T> {
    void save(T t);
    T findById(long id) throws SystemException;
    void update(T t, long id) throws SystemException;
    void delete(long id);
    List<VehicleBase> allListed(VehicleBase vehicleBase, long id,int page);

}
