package ru.ibs.gisgmp.common.validation;

public class ValidationResult {
    private String path;
    private String errCode;

    public ValidationResult(String path, String errCode) {
        this.path = path;
        this.errCode = errCode;
    }

    public String getPath() {
        return path;
    }

    public String getErrCode() {
        return errCode;
    }
}

