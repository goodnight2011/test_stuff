package ru.ibs.gisgmp.charge.requisites;

import ru.ibs.gisgmp.charge.requisites.period.TaxPeriod;
import ru.ibs.gisgmp.common.utils.ArrUtils;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.ibs.gisgmp.charge.requisites.BudgetIndexFields.*;

@FieldConst
public class BudgetIndex {
    private PayerStatus status;
    private Purpose purpose;
    private TaxPeriod taxPeriod;
    private String taxDocNumber;
    private Date taxDocDate;
    private PaymentType paymentType;

    public PayerStatus getStatus() {
        return status;
    }

    public void setStatus(PayerStatus status) {
        this.status = status;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public TaxPeriod getTaxPeriod() {
        return taxPeriod;
    }

    public void setTaxPeriod(TaxPeriod taxPeriod) {
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

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public static List<ValidationResult> validateTaxDocNumber(BudgetIndex index){
       return ArrUtils.concat(Arrays.asList(
               NonNullValidator.notEmptyString(index.getTaxDocNumber(), TAX_DOC_NUMBER, TAX_DOC_NUMBER + ".empty"),
               ( true && index.getTaxDocNumber() != null
                       && index.getTaxDocNumber().length() > 0
                       && index.getTaxDocNumber().length() <= 15 ?
                       Collections.emptyList():
                       Arrays.asList(new ValidationResult(TAX_DOC_NUMBER, TAX_DOC_NUMBER + ".format")))
       ));
    }

    public static List<ValidationResult> validate(BudgetIndex index){
        return ArrUtils.concat(Arrays.asList(
                NonNullValidator.validate(index.getStatus(), st -> st.validate(), STATUS, STATUS + ".empty"),
                NonNullValidator.validate(index.getPurpose(), purp -> purp.validate(), PURPOSE, PURPOSE + ".empty"),
                NonNullValidator.validate(index.getTaxPeriod(), period -> TaxPeriod.validate(period), TAX_PERIOD, TAX_PERIOD + ".empty"),
                validateTaxDocNumber(index),
                NonNullValidator.validate(index.getTaxDocDate(), TAX_DOC_DATE, TAX_DOC_DATE + ".empty"),
                index.getPaymentType() != null ? PaymentType.validateFormat(index.getPaymentType().getString()) : Collections.emptyList()
        ));
    }

}
