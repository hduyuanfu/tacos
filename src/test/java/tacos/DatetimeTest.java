package tacos;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author yuanfu
 * @data 2021/5/13 19:15
 */
public class DatetimeTest {

    @Test
    public void datetime() {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
    }
}
