package com.example.anamuslim.prayertimecalculation;

import android.content.Context;

import com.example.anamuslim.R;



// Prayer
public class Calculators {

    public static int extractMinutes(double time) {
        /*Log.d("String_date_m" , time+"");
        String preparedNumber = (time + "").substring(0, 3);
        double timeDouble = new Double(time);
        int houre = (int) timeDouble;
        double minsDouble = (60 * (timeDouble - houre)) / 100;
        preparedNumber = (minsDouble + "").substring(0, (minsDouble + "").length() < 4 ? 3 : 4);
        double mins = new Double(preparedNumber);
        String minsFinal = String.valueOf(mins).replace("0.", "");
        Log.d("String_date_m" , minsFinal+"");
        return Integer.parseInt(minsFinal.trim());*/

        String preparedNumber = (time + "").substring(0, 3);
        double timeDouble = new Double(time);
        int hour = (int) timeDouble;
        double minsDouble = (60 * (timeDouble - hour)) / 100;

        preparedNumber = (minsDouble + "").substring(0, (minsDouble + "").length() < 4 ? 3 : 4);
        double mins = new Double(preparedNumber);
        String minsFinal = String.valueOf(mins).replace("0.", "");

        return Integer.parseInt((minsFinal.length() == 1 ? minsFinal + "0" : minsFinal).trim());

    }

    /**
     * Function to extract Hours only from double time
     *
     * @param time Double time
     * @return Integer hours
     */
    public static int extractHour(double time) {
        String preparedNumber = (time + "").substring(0, 3);
        double timeDouble = Double.parseDouble(preparedNumber);
        return (int) timeDouble;
    }


    /**
     * Function to extract praying time from double numbers
     *
     * @param time Double time
     * @return String Time of pray
     */
    public static String extractPrayTime(Context context, double time) {

        boolean pmFlag = false;
        String preparedNumber = (time + "").substring(0, 3);
        double timeDouble = new Double(time);
        int hour = (int) timeDouble;
        double minsDouble = (60 * (timeDouble - hour)) / 100;

        preparedNumber = (minsDouble + "").substring(0, (minsDouble + "").length() < 4 ? 3 : 4);
        double mins = new Double(preparedNumber);
        String minsFinal = String.valueOf(mins).replace("0.", "");
//
        System.out.println("Ahhhhhh" + minsFinal);
//
//        if (ConfigPreferences.getTwentyFourMode(context) != true) {
//            if (hour > 12) {
//                hour -= 12;
//                pmFlag = true;
//            }
//        }

        String h = "0" + hour + ":" +
                (minsFinal.length() == 1 ? minsFinal + "0" : minsFinal) + " " +
                (hour >= 12 ? context.getString(R.string.pm) : context.getString(R.string.am));
        String h1 = hour + ":" +
                (minsFinal.length() == 1 ? minsFinal + "0" : minsFinal) + " " +
                (hour >= 12 ? context.getString(R.string.pm) : context.getString(R.string.am));
//        return convertNumberType(context, hour + ":" +
//                (minsFinal.length() == 1 ? minsFinal + "0" : minsFinal) + " " +
//                ((hour >= 12 || pmFlag) ? context.getString(R.string.pm) : context.getString(R.string.am)));

        return hour >= 7 ? h1 : h;
    }

    public static String convertNumberType(Context context, String number) {

        try {
            if (context.getResources().getConfiguration().locale.getDisplayLanguage().equals("العربية"))
                return number.replaceAll("0", "٠").replaceAll("1", "١")
                        .replaceAll("2", "٢").replaceAll("3", "٣")
                        .replaceAll("4", "٤").replaceAll("5", "٥")
                        .replaceAll("6", "٦").replaceAll("7", "٧")
                        .replaceAll("8", "٨").replaceAll("9", "٩");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return number;
    }

    public static String convertNumberType1(String number) {


        number.replace("٠", "0")
                .replaceAll("١", "1")
                .replaceAll("٢", "2")
                .replaceAll("٣", "3")
                .replaceAll("٤", "4")
                .replaceAll("٥", "5")
                .replaceAll("٦", "6")
                .replaceAll("٧", "7")
                .replaceAll("٨", "8")
                .replaceAll("٩", "9");

        return number;
    }
}
