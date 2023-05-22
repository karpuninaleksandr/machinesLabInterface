package ru.ac.uniyar.objects;

import java.util.Date;

public class RentAgreement implements Entity {
    private final int id;
    private final String paymentType;
    private final Date startDate;
    private final Date expireDate;
    private final double rate;
    private final int clientId;
    private final int machineId;

    public RentAgreement(int id, String paymentType, Date startDate, Date expireDate, double rate, int clientId, int machineId) {
        this.id = id;
        this.paymentType = paymentType;
        this.startDate = startDate;
        this.expireDate = expireDate;
        this.rate = rate;
        this.clientId = clientId;
        this.machineId = machineId;
    }

    public int getId() {
        return id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public double getRate() {
        return rate;
    }

    public int getClientId() {
        return clientId;
    }

    public int getMachineId() {
        return machineId;
    }
}
