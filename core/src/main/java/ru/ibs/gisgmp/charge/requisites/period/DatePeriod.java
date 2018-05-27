package ru.ibs.gisgmp.charge.requisites.period;

import ru.ibs.nsi.validation.CompositeValidator;
import ru.ibs.nsi.validation.NonNullValidator;
import ru.ibs.nsi.validation.ValidationResult;
import ru.ibs.processor.FieldConst;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import static ru.ibs.gisgmp.charge.requisites.period.DatePeriodFields.*;

@FieldConst
public class DatePeriod {
   private LocalDate date;
   private boolean withDelimiter;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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
        return DateTimeFormatter.ofPattern(withDelimiter ? "dd.MM.yyyy" : "ddMMyyyy").format(date);
    }
}
