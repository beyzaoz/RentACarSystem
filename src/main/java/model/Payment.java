package model;

import model.enums.PaymentMethod;
import model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

public class Payment extends BaseModel{

    private Reservation reservation;
    private Date paymentDate;
    private BigDecimal totalamount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;//NOT NULL


    //CONSTRUCTOR
    public Payment(Reservation reservation, Date paymentDate, BigDecimal totalamount, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.reservation = reservation;
        this.paymentDate = paymentDate;
        this.totalamount = totalamount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    //GETTER/SETTER

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(BigDecimal totalamount) {
        this.totalamount = totalamount;
    }
}
