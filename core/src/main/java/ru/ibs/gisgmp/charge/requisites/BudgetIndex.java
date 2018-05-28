package ru.ibs.gisgmp.charge.requisites;

import java.time.LocalDate;
import java.util.Date;

public class BudgetIndex {
    private PayerStatus status;
    private String purpose;
    private String taxPeriod;
    private String taxDocNumber;
    private Date taxDocDate;
    private String paymentType;

    public PayerStatus getStatus() {
        return status;
    }

    public void setStatus(PayerStatus status) {
        this.status = status;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getTaxPeriod() {
        return taxPeriod;
    }

    public void setTaxPeriod(String taxPeriod) {
        this.taxPeriod = taxPeriod;
    }

    public String getTaxDocNumber() {
        return taxDocNumber;
    }

    public void setTaxDocNumber(String taxDocNumber) {
        this.taxDocNumber = taxDocNumber;
    }

    public Date getTaxDocDate() {
        return taxDocDate;
    }

    public void setTaxDocDate(Date taxDocDate) {
        this.taxDocDate = taxDocDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
