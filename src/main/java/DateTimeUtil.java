import java.time.DateTimeException;
import java.time.LocalDate;

import org.apache.log4j.Logger;

public class DateTimeUtil {
    private static final Logger LOGGER = Logger.getLogger(DateTimeUtil.class);

    /**
     * validateFromToDates(canBePast(fromDate), inclusive(canBePast(toDate));
     *    - обе даты могут быть в прошлом, в том числе быть одной и той же датой.
     *    Пример - поиск сущностей по дате. Можно искать в прошлом, в том числе по 1 дню.
     * validateFromToDates(mustBeFuture(fromDate), exclusive(mustBeFuture(toDate));
     *    - обе даты должны быть в будующем, НЕ могут обозначать один и тот же день.
     *    Пример - создание скидки. Нельзя создать скидку, применимую в прошлом и ее срок действия
     *    не может быть с сегодня до сегодня.
     * validateFromToDates(canBePast(fromDate), inclusive(mustBeFuture(toDate));
     *    - fromDate может быть в прошлом, toDate должен быть в будующем, в том числе до указанной
     *    даты включительно. Пример - контракт подписан давно, но внесен в систему только сейчас
     *    и действует до указанной даты включительно.
     * @param fromIsFuture true, если дата должна быть в будущем
     * @param toIsFuture true, если дата должна быть в будущем
     * @param notSameDates true, если даты не могут обозначать один и тот же день
     * @return метод возвращает true или кидает ошибку DateTimeException
     */
    public static boolean validateFromToDates(boolean fromIsFuture, LocalDate fromDate,
                                              boolean toIsFuture, LocalDate toDate,
                                              boolean notSameDates) {
        if (notSameDates) {
            toDate = toDate.minusDays(1);
        }
        if (fromIsFuture && !toIsFuture || fromDate.isAfter(toDate)) {
            throw new DateTimeException(toDate + "is before " + fromDate);
        }
        if (fromIsFuture) {
            LOGGER.info("Date " + mustBeFuture(fromDate) + " is valid");
        }
        if (toIsFuture) {
            LOGGER.info("Date " + mustBeFuture(toDate) + " is valid");
        }
        LOGGER.info("Dates are validated");
        return true;
    }

    private static LocalDate mustBeFuture(LocalDate dateToValidate) {
        if (dateToValidate.isAfter(LocalDate.now())) {
            return dateToValidate;
        } else {
            throw new DateTimeException(dateToValidate + " must be after today");
        }
    }
}
