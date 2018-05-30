package ru.ibs.gisgmp.charge;

import ru.ibs.gisgmp.charge.organization.Oktmo;
import ru.ibs.gisgmp.charge.organization.Organization;
import ru.ibs.gisgmp.charge.payer.UnifiedPayerIdentifier;
import ru.ibs.gisgmp.common.utils.ArrUtils;
import ru.ibs.gisgmp.common.utils.DateUtils;
import ru.ibs.gisgmp.common.validation.CompositeValidator;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;
import static ru.ibs.gisgmp.charge.ChargeFields.*;

import java.util.*;

@FieldConst
public class Charge {
    private SupplierBillId supplierBillId;
    private Date billDate;
    private Date docDispatchDate;
    private Organization supplierOrgInfo;
    private String billFor;
    private long totalAmount;
    private Meaning meaning;
    private String reason;
    private Kbk kbk;
    private Oktmo oktmo;
    private UnifiedPayerIdentifier unifiedPayerIdentifier;

    public SupplierBillId getSupplierBillId() {
        return supplierBillId;
    }

    public void setSupplierBillId(SupplierBillId supplierBillId) {
        this.supplierBillId = supplierBillId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Date getDocDispatchDate() {
        return docDispatchDate;
    }

    public void setDocDispatchDate(Date docDispatchDate) {
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

    public Kbk getKbk() {
        return kbk;
    }

    public void setKbk(Kbk kbk) {
        this.kbk = kbk;
    }

    public Oktmo getOktmo() {
        return oktmo;
    }

    public void setOktmo(Oktmo oktmo) {
        this.oktmo = oktmo;
    }

    public UnifiedPayerIdentifier getUnifiedPayerIdentifier() {
        return unifiedPayerIdentifier;
    }

    public void setUnifiedPayerIdentifier(UnifiedPayerIdentifier unifiedPayerIdentifier) {
        this.unifiedPayerIdentifier = unifiedPayerIdentifier;
    }

    public static List<ValidationResult> validate(Charge charge){
//        return CompositeValidator.validate(charge,
//                new DelegateValidator<>(SUPPLIER_BILL_ID, StringBased::validate, Charge::getSupplierBillId),
//                Charge::validateBillDate,
//                new CompositeValidator<>(new NonNullValidator<>(SUPPLIER_ORG_INFO, SUPPLIER_ORG_INFO+".empty"),
//                        Organization::)
//
//        );
//        return null;
        return ArrUtils.concat(Arrays.asList(
                NonNullValidator.validate(charge.getSupplierBillId(), suppl -> SupplierBillId.validateFormat2(suppl.getString()), SUPPLIER_BILL_ID, SUPPLIER_BILL_ID + ".empty"),
                validateBillDate(charge),
                validateSupplierOrgInfo(charge),
                NonNullValidator.notEmptyString(charge.getBillFor(), BILL_FOR, BILL_FOR + ".empty"),
                validateTotalAmount(charge),
                NonNullValidator.validate(charge.getKbk(), kbk -> kbk.validate(), KBK, KBK  +".empty"),
                NonNullValidator.validate(charge.getOktmo(), oktmo -> oktmo.validate(), OKTMO, OKTMO + ".empty")/*,
                NonNullValidator.validate(charge.geG)*/
        ));
    }

    public static List<ValidationResult> validateTotalAmount(Charge charge){
       return charge.getTotalAmount() < 0 ? Arrays.asList(new ValidationResult(TOTAL_AMOUNT, TOTAL_AMOUNT + ".incorrect")) :
               Collections.emptyList();
    }

    public static List<ValidationResult> validateReason(Charge charge){
       if(charge.getMeaning() != null && !charge.getMeaning().equals(Meaning.NEW))
           return NonNullValidator.notEmptyString(charge.getReason(), REASON, REASON + ".empty");
       return Collections.emptyList();
    }

    public static List<ValidationResult> validateBillDate(Charge charge){
        return NonNullValidator.validate(charge.getBillDate(), date -> {
            boolean cmp = DateUtils.compare(DateUtils.createDate(2013, 0,1), date) > 0;
            return cmp ? Arrays.asList(new ValidationResult(BILL_DATE, BILL_DATE +".too_early")): Collections.emptyList();
        }, BILL_DATE, BILL_DATE + ".empty");
    }

    public static List<ValidationResult> validateSupplierOrgInfo(Charge charge){
       return NonNullValidator.validate(charge.getSupplierOrgInfo(), SUPPLIER_ORG_INFO, SUPPLIER_ORG_INFO + ".empty");
    }
}
