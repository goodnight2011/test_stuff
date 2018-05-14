package ru.ibs.gisgmp.charge;

import ru.ibs.gisgmp.charge.organization.Organization;
import ru.ibs.gisgmp.charge.payer.UnifiedPayerIdentifier;

import java.time.LocalDate;

public class Charge {
    private String supplierBillId;
    private LocalDate billDate;
    private LocalDate docDispatchDate;
    private Organization supplierOrgInfo;
    private String billFor;
    private long totalAmount;
    private Meaning meaning;
    private String reason;
    private String kbk;
    private String oktmo;
    private String tofkCode;
    private String tofkLs;
    private UnifiedPayerIdentifier unifiedPayerIdentifier;

    public String getSupplierBillId() {
        return supplierBillId;
    }

    public void setSupplierBillId(String supplierBillId) {
        this.supplierBillId = supplierBillId;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public LocalDate getDocDispatchDate() {
        return docDispatchDate;
    }

    public void setDocDispatchDate(LocalDate docDispatchDate) {
        this.docDispatchDate = docDispatchDate;
    }

    public Organization getSupplierOrgInfo() {
        return supplierOrgInfo;
    }

    public void setSupplierOrgInfo(Organization supplierOrgInfo) {
        this.supplierOrgInfo = supplierOrgInfo;
    }

    public String getBillFor() {
        return billFor;
    }

    public void setBillFor(String billFor) {
        this.billFor = billFor;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Meaning getMeaning() {
        return meaning;
    }

    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getKbk() {
        return kbk;
    }

    public void setKbk(String kbk) {
        this.kbk = kbk;
    }

    public String getOktmo() {
        return oktmo;
    }

    public void setOktmo(String oktmo) {
        this.oktmo = oktmo;
    }

    public String getTofkCode() {
        return tofkCode;
    }

    public void setTofkCode(String tofkCode) {
        this.tofkCode = tofkCode;
    }

    public String getTofkLs() {
        return tofkLs;
    }

    public void setTofkLs(String tofkLs) {
        this.tofkLs = tofkLs;
    }

    public UnifiedPayerIdentifier getUnifiedPayerIdentifier() {
        return unifiedPayerIdentifier;
    }

    public void setUnifiedPayerIdentifier(UnifiedPayerIdentifier unifiedPayerIdentifier) {
        this.unifiedPayerIdentifier = unifiedPayerIdentifier;
    }
}
