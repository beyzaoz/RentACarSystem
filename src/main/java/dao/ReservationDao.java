//package dao;
//
//import model.Reservation;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReservationDao {
//
//    private static final List<Reservation> reservations = new ArrayList<>();
//
//
//    public void save(Reservation reservation) {
//        if (reservation == null) {
//            throw new IllegalArgumentException("Reservation cannot be null");
//        }
//
//        // ID yoksa otomatik oluştur
//        if (reservation.getId() == 0) {
//            reservation.setId(generateId());
//        }
//
//        reservations.add(reservation);
//        System.out.println("Reservation saved. ID: " + reservation.getId());
//    }
//
//
//    public List<Reservation> getAll() {
//        return reservations;
//    }
//
//
//    public Reservation findById(long id) {
//        for (Reservation r : reservations) {
//            if (r.getId() == id) {
//                return r;
//            }
//        }
//        return null;
//    }
//
//    private long generateId() {
//        return reservations.size() + 1L;
//    }
//}
