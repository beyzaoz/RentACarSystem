package model;

import model.enums.PaymentMethod;
import model.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

public class Payment extends BaseModel {

    private Order order;
    private Date paymentDate;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus; // NOT NULL
    private BigDecimal deposite;

    // CONSTRUCTOR
    public Payment(Order order, Date paymentDate, BigDecimal totalAmount, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.order = order;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public Payment() {}

    // GETTER / SETTER
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDeposite() {
        return deposite;
    }

    public void setDeposite(BigDecimal deposite) {
        this.deposite = deposite;
    }
}
