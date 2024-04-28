package shop.project.pathorderserver._core.utils;

import javax.swing.text.MaskFormatter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;

public class FormatUtil {

    public static String timeFormatter(Timestamp timestamp) { // 시간 표기 방식
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd hh:mm:ss");

        return timestamp.toLocalDateTime().format(formatter);
    }

    public static String decimalFormatter(int decimalNum) { // 숫자 표기 방식
        DecimalFormat formatter = new DecimalFormat("###,###");

        return formatter.format(decimalNum);
    }

    public static String pNumFormatter(String number) {
        try {
            MaskFormatter formatter = new MaskFormatter("###-####-####");
            formatter.setValueContainsLiteralCharacters(false);

            return formatter.valueToString(number);
        } catch (ParseException e) {

            return number; // parsing 실패 시, 그냥 return.
        }
    }
}
