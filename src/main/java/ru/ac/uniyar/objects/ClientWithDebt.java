package ru.ac.uniyar.objects;

public class ClientWithDebt extends Client {
    private final int rentAgreementId;
    private final double debt;

    public ClientWithDebt(int id, String name, int rentAgreementId, double debt) {
        super(id, name);
        this.rentAgreementId = rentAgreementId;
        this.debt = Math.abs(debt);
    }

    public int getRentAgreementId() {
        return this.rentAgreementId;
    }
    public double getDebt() {
        return this.debt;
    }
}
