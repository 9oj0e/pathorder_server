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

    public static String dateFormatter(Timestamp timestamp) { // 시간 표기 방식
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");

        return timestamp.toLocalDateTime().format(formatter);
    }

    public static String decimalFormatter(int decimalNum) { // 숫자 표기 방식
        DecimalFormat formatter = new DecimalFormat("###,###");

        return formatter.format(decimalNum);
    }

    public static String stringFormatter(String input) {
        int maxLength = 24; // 최대 길이

        String output;
        if (input.length() > maxLength) {
            output = input.substring(0, maxLength) + "...";
        } else {
            output = input;
        }
        return output;
    }

    public static String pNumFormatter(String number) { // 휴대폰 번호 표기 방식
        try {
            MaskFormatter formatter = new MaskFormatter("###-####-####");
            formatter.setValueContainsLiteralCharacters(false);

            return formatter.valueToString(number);
        } catch (ParseException e) {

            return number; // parsing 실패 시, 그냥 return.
        }
    }
}
