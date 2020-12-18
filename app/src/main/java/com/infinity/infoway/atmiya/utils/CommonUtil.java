package com.infinity.infoway.atmiya.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {


    public static String getMonthNameFromNumber(int monthNo) {
        String monthName = "";
        switch (monthNo) {
            case 0:
                monthName = "JANUARY";
                break;
            case 1:
                monthName = "FEBRUARY";
                break;
            case 2:
                monthName = "MARCH";
                break;
            case 3:
                monthName = "APRIL";
                break;
            case 4:
                monthName = "MAY";
                break;
            case 5:
                monthName = "JUNE";
                break;
            case 6:
                monthName = "JULY";
                break;
            case 7:
                monthName = "AUGUST";
                break;
            case 8:
                monthName = "SEPTEMBER";
                break;
            case 9:
                monthName = "OCTOBER";
                break;
            case 10:
                monthName = "NOVEMBER";
                break;
            case 11:
                monthName = "DECEMBER";
                break;
        }
        return monthName;
    }


    public static String getMonthSortNameFromNumber(int monthNo) {
        String monthName = "";
        switch (monthNo) {
            case 1:
                monthName = "Jan";
                break;
            case 2:
                monthName = "Feb";
                break;
            case 3:
                monthName = "Mar";
                break;
            case 4:
                monthName = "Apr";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "Jun";
                break;
            case 7:
                monthName = "Jul";
                break;
            case 8:
                monthName = "Aug";
                break;
            case 9:
                monthName = "Sep";
                break;
            case 10:
                monthName = "Oct";
                break;
            case 11:
                monthName = "Nov";
                break;
            case 12:
                monthName = "Dec";
                break;
        }
        return monthName;
    }


    public static String getWeekDayName(int currentMonth, int dayOfMonth, String currentDate) {

        SimpleDateFormat inFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = inFormat.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
        String goal = outFormat.format(date).substring(0,3);
        return goal;


//        if (dayOfMonth != 10 && String.valueOf(dayOfMonth).contains("0")) {
//            dayOfMonth = Integer.parseInt(String.valueOf(dayOfMonth).replace("0", ""));
//        }
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.MONTH, currentMonth);
//        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        String days[] = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
//
//        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
//
//        return days[dayIndex - 1];
    }


}
