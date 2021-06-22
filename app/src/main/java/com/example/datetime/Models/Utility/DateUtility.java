package com.example.datetime.Models.Utility;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtility {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    public static SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMMM  yyyy", Locale.getDefault());
    public static void showDateDialogForDOB(Context context,EditText et){
        final EditText editText = et;
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                int month = monthOfYear+1;
                editText.setText(String.format("%02d",dayOfMonth)+"/"+String.format("%02d",month)+"/"+year);
            }

        };
        DatePickerDialog dialog = new DatePickerDialog(context, AlertDialog.THEME_HOLO_LIGHT,
                date, 1995,0,1);
        dialog.show();

    }
    public static void showDateDialogForToday(Context context, TextView textView){

        final Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
               /* myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd-mm-yyyy"; // your format
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());*/
               int month = monthOfYear+1;
               textView.setText(String.format("%02d",dayOfMonth)+"-"+String.format("%02d",month)+"-"+year);
            }

        };
        DatePickerDialog dialog = new DatePickerDialog(context,
                AlertDialog.THEME_HOLO_LIGHT,
                date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    public static void showDateDialog(Context context,Date mDate,final DateSelectListener dateSelectListener){
        final Calendar myCalendar = Calendar.getInstance();
        myCalendar.setTime(mDate);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
               /* myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd-mm-yyyy"; // your format
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());*/
                int month = monthOfYear+1;
                dateSelectListener.onDateSelected((String.format("%02d",dayOfMonth)+"/"+String.format("%02d",month)+"/"+year));
            }

        };
        DatePickerDialog dialog = new DatePickerDialog(context,
                AlertDialog.THEME_HOLO_LIGHT,
                date, myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }


    public static String dateNavigator(String stringDate, int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDate(stringDate));
        calendar.add(Calendar.DATE,days);
        return getStringDate(calendar.getTimeInMillis());
    }
    public static String monthNavigator(String stringDate,int days)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getDate(stringDate));
        calendar.add(Calendar.MONTH,days);
        return getStringDate(calendar.getTimeInMillis());
    }

    public static long getTime(String stringDate){
        return getDate(stringDate).getTime();
    }

    public static String getStringDate(long time)
    {
        Date date = new Date(time);
        return dateFormat.format(date);
    }


    public static Date getDate(long time)
    {
        return new Date(time);
    }




    public static Date getDate(String stringDate){
        try {
            Date date = dateFormat.parse(stringDate);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static String getDate(Date date){
        return dateFormat.format(date);
    }

    public static String getStartingDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int month = calendar.get(Calendar.MONTH)+1;
        int year = calendar.get(Calendar.YEAR);
        return "01/"+String.format("%02d",month)+"/"+year;

    }
    public static Date getStartingYear()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),0,1);
        return calendar.getTime();

    }
    public static Date getEndingYear()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR),11,31);
        return calendar.getTime();

    }


    public static String calculateTimeAgo(String date)
    {
        try {
         //   Log.d(App.TAG,"input date = "+date);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date pastDate = format.parse(date);
            Date now = new Date();
            long days = TimeUnit.MILLISECONDS.toDays(now.getTime() - pastDate.getTime());
            String timeAgo;
            if(days==0){
                timeAgo = "Today";
            }
            else if(days>=365){
                int years = (int) (days/365);
                if(years==1)
                    timeAgo = years+" year ago";
                else
                    timeAgo = years+" years ago";
            }
            else if (days>=30) {
                int month = (int) (days/30);
                if(month==1)
                    timeAgo = month+" month ago";
                else
                    timeAgo = month+" months ago";
            }

            else {
                if (days==1)
                 timeAgo=days +" day ago";
                else
                    timeAgo=days +" days ago";
            }
            return timeAgo;
        }
        catch (Exception j){
            j.printStackTrace();
       //     Log.d(App.TAG,j.getMessage());
            return null;
        }
    }
    public static String getMonthDate(Date date)
    {
       return dateFormatForMonth.format(date);
    }
    public static Date getLastDayOfMonth(Date date)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }
    public static Date getFirstDayOfMonth(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH,1);
        return c.getTime();
    }
    public static Date getDate(int month,int year){
        Calendar c = Calendar.getInstance();
        c.set(year, month,1);
        return c.getTime();
    }
    public static int getMonth(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH);
    }
    public static int getDay(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE);
    }
    public static int getYear(){
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }



    public interface DateSelectListener{
        void onDateSelected(String date);
    }

    public static long getCurrentDay(){
        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date  = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        return cal.getTimeInMillis();
    }
}
