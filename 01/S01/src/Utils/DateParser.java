package Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DateParser {
    public static List<Integer> destructureDate(String dateString) {
        String[] dateList = dateString.split("/");

        int day = Integer.parseInt(dateList[0]);
        int month = Integer.parseInt(dateList[1]);
        int year = Integer.parseInt(dateList[2]);

        return List.of(day, month, year);
    }

    public static void main(String[] args) throws ParseException {
        // List<Integer> dDate = destructureDate("09/02/2003");
        
        // for (int d : dDate) {
        //     System.out.println(d);
        // }

        String date_string = "09/02/2003";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = formatter.parse(date_string);
        System.out.println("Date Value: " + date);
    }
}
