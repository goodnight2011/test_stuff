package ru.ibs.gisgmp.charge.requisites.period;

import ru.ibs.gisgmp.common.validation.NonNullValidator;
import ru.ibs.gisgmp.common.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.util.Date;
import java.util.List;
import static ru.ibs.gisgmp.charge.requisites.period.DatePeriodFields.*;

@FieldConst
public class DatePeriod {
   private Date date;
   private boolean withDelimiter;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isWithDelimiter() {
        return withDelimiter;
    }

    public void setWithDelimiter(boolean withDelimiter) {
        this.withDelimiter = withDelimiter;
    }

    public static List<ValidationResult> validateDate(DatePeriod period){
        return NonNullValidator.validate(period.getDate(), DATE, DATE + ".empty");
    }

    public String asString(){
//        return DateTimeFormatter.ofPattern(withDelimiter ? "dd.MM.yyyy" : "ddMMyyyy").format(date);

        return null;
    }
}
