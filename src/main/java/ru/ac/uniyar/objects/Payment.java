package ru.ac.uniyar.objects;

import java.util.Date;

public class Payment implements Entity {
    private final int id;
    private final Date date;
    private final int rentAgreementId;
    private final int moneyPaid;

    public Payment(int id, Date date, int rentAgreementId, int moneyPaid) {
        this.id = id;
        this.date = date;
        this.rentAgreementId = rentAgreementId;
        this.moneyPaid = moneyPaid;
    }

    public int getId() {
        return this.id;
    }
    public Date getDate() {
        return this.date;
    }
    public int getRentAgreementId() {
        return this.rentAgreementId;
    }
    public int getMoneyPaid() {
        return moneyPaid;
    }
}
