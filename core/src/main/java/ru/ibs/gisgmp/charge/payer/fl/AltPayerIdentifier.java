package ru.ibs.gisgmp.charge.payer.fl;

import ru.ibs.gisgmp.common.utils.ArrUtils;
import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import javax.swing.text.Document;
import java.util.Arrays;
import java.util.List;
import static ru.ibs.gisgmp.charge.payer.fl.AltPayerIdentifierFields.*;

@FieldConst
public class AltPayerIdentifier {
    private DocumentType documentType;
    private Citezenship citezenship;
    private String seriesNum;

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Citezenship getCitezenship() {
        return citezenship;
    }

    public void setCitezenship(Citezenship citezenship) {
        this.citezenship = citezenship;
    }

    public String getSeriesNum() {
        return seriesNum;
    }

    public void setSeriesNum(String seriesNum) {
        this.seriesNum = seriesNum;
    }

    public static List<ValidationResult> validate(AltPayerIdentifier identifier){
        return ArrUtils.concat(Arrays.asList(
                NonNullValidator.validate(identifier.documentType, DocumentType::validate, DOCUMENT_TYPE, DOCUMENT_TYPE + ".empty"),
                NonNullValidator.validate(identifier.citezenship, Citezenship::validate, CITEZENSHIP, CITEZENSHIP + ".empty"),
                NonNullValidator.notEmptyString(identifier.seriesNum, SERIES_NUM, SERIES_NUM + ".empty")
        ));
    }
}
