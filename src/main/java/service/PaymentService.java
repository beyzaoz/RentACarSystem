package service;

import dao.PaymentDao;

public class PaymentService {
    private final PaymentDao paymentDao;

    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = new PaymentDao();
    }
}
