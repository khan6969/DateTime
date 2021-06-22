package com.example.datetime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datetime.Adapter.DayAdapter;
import com.example.datetime.Adapter.MounthAdapter;
import com.example.datetime.Adapter.YearAdapter;
import com.example.datetime.Models.DateModel;
import com.example.datetime.Models.Utility.DateUtility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
     TextView Tv_Date,Tv_ShowDate;
    private long dayID,CdayID;
    private ArrayList<DateModel> mlist = new ArrayList<>();
    private DayAdapter mAdapter;
    private MounthAdapter mounthAdapter;
    private YearAdapter yearAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitViews();
       // setdata();
    }


    private void InitViews() {
        Tv_Date = findViewById(R.id.tv_setdate);
        Tv_ShowDate = findViewById(R.id.tv_showdate);
        setdata();
    }

    private void setdata() {
        dayID = DateUtility.getCurrentDay();

        //    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, ''yy");
       // Calendar actualDate = Calendar.getInstance();

        //    SimpleDateFormat sddf = new SimpleDateFormat()
        String currentDateandTime = sdf.format(new Date());
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(DateUtility.getYear(),DateUtility.getMonth(),DateUtility.getDay());
        calendar.setTimeInMillis(calendar.getTimeInMillis());
        Date today = calendar.getTime();

        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date tomorrow = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String tomorrowAsString = sdf.format(tomorrow);

        Tv_ShowDate.setText(currentDateandTime+"  "+tomorrowAsString);
         if (currentDate!=tomorrowAsString){
             final Dialog dialog = new Dialog(MainActivity.this);
             dialog.setContentView(R.layout.addnumberlayout);
             int width = WindowManager.LayoutParams.MATCH_PARENT;
             int height = WindowManager.LayoutParams.WRAP_CONTENT;
             dialog.getWindow().setLayout(width,height);
             dialog.show();
             final TextView Expired = dialog.findViewById(R.id.tv_showexpired);
             Toast.makeText(MainActivity.this, "ttt" + tomorrowAsString, Toast.LENGTH_SHORT).show();
           //  Button post = dialog.findViewById(R.id.post);
             Expired.setText("Expired");
         }
    }


}