package pere.maineventtool.shared;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

@RunWith(Parameterized.class)
public class DateToolTests {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"2019-01-01T01:00:00+00:00"}, {"2019-01-01T12:33:00+00:00"}, {"2019-01-01T00:00:00+00:00"}, {"2019-01-01T08:59:59+00:00"}
        });
    }

    private String input;

    public DateToolTests(String input) {
        this.input = input;
    }

    @Test
    public void testParseStringDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
        Date expected = dateFormat.parse(input);

        Assertions.assertEquals(expected, DateTool.parseStringDate(input, "yyyy-MM-dd HH:mm:ss"));
    }
}
