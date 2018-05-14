package ru.ibs.gisgmp.charge.payer.fl;

import javax.swing.text.Document;

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
}
