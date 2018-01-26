package com.example.juliannr.nextmovie;

import org.junit.Test;

import java.io.Console;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static java.lang.System.out;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void tes(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = "2016-08-27 16:26:40 UTC";
        format.setTimeZone(TimeZone.getTimeZone(dateString.substring(20,22 )));
        Date date = new Date();
        try {
            date = format.parse(dateString.substring(0, 18));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        out.print(date.toGMTString());
        assertNotNull(date);
    }
}