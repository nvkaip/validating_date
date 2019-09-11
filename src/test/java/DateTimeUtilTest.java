import org.junit.Assert;
import org.junit.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DateTimeUtilTest {

    @Test
    public void validateFromToPastSameDates() {
        LocalDate fromDate = LocalDate.of(2019, 8, 31);
        LocalDate toDate = LocalDate.of(2019, 8, 31);
        boolean result = DateTimeUtil.validateFromToDates(false, fromDate,
                false, toDate,false);
        Assert.assertTrue(result);
    }

    @Test
    public void validateFromToFutureNotSame() {
        LocalDate fromDate = LocalDate.of(2020, 8, 30);
        LocalDate toDate = LocalDate.of(2020, 8, 31);
        boolean result = DateTimeUtil.validateFromToDates(true, fromDate,
                true, toDate, true);
        Assert.assertTrue(result);
    }

    @Test(expected = DateTimeException.class)
    public void validateFromToFutureSame() {
        LocalDate fromDate = LocalDate.of(2020, 8, 31);
        LocalDate toDate = LocalDate.of(2020, 8, 31);
        DateTimeUtil.validateFromToDates(true, fromDate, true, toDate, true);
    }

    @Test
    public void validateFromToPastFuture() {
        LocalDate fromDate = LocalDate.of(2019, 8, 30);
        LocalDate toDate = LocalDate.of(2020, 8, 31);
        boolean result = DateTimeUtil.validateFromToDates(false, fromDate,
                true, toDate, false);
        Assert.assertTrue(result);
    }

    @Test(expected = DateTimeException.class)
    public void validateFromAfterTo() {
        LocalDate fromDate = LocalDate.of(2020, 8, 30);
        LocalDate toDate = LocalDate.of(2019, 8, 31);
        DateTimeUtil.validateFromToDates(true, fromDate, true, toDate, true);
    }

    @Test(expected = DateTimeException.class)
    public void validateFromMustBeFuture() {
        LocalDate fromDate = LocalDate.of(2018, 8, 30);
        LocalDate toDate = LocalDate.of(2018, 8, 31);
        DateTimeUtil.validateFromToDates(true, fromDate, true, toDate,false);
    }

    @Test(expected = DateTimeException.class)
    public void validateToMustBeFuture() {
        LocalDate fromDate = LocalDate.of(2018, 8, 30);
        LocalDate toDate = LocalDate.of(2018, 8, 31);
        DateTimeUtil.validateFromToDates(false, fromDate, true, toDate,false);
    }

    @Test(expected = DateTimeException.class)
    public void validateFromMustBeAfterToday() {
        LocalDate fromDate = LocalDate.now();
        LocalDate toDate = LocalDate.of(2020, 8, 31);
        DateTimeUtil.validateFromToDates(true, fromDate, true, toDate,false);
    }
}
