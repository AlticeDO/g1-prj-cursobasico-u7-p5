package jsvasquez2315.com.calculadoradetiempos;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1_fechaA,btn2_horasA, btn3_fechaB, btn4_horasB, btn5_CalcularC;
    TextView Atexto_fecha1,Btexto_fecha2,Ctexto_Calcular3,Atexto_fecha3,Btexto_fecha4;

    int dia1, dia2;
    private  int año,mes,día,hora,minutos, segundos;

    private String ds1,ds2, ds3,ds4;

    private Date d1 = null;
    private Date d2 = null;

    private int diaohora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1_fechaA=(Button)findViewById(R.id.btn1_fechaA);
        btn2_horasA=(Button)findViewById(R.id.btn2_horasA);
        btn3_fechaB=(Button)findViewById(R.id.btn3_fechaB);
        btn4_horasB=(Button)findViewById(R.id.btn4_horasB);
        btn5_CalcularC=(Button)findViewById(R.id.btn5_CalcularC);
        Atexto_fecha1=(TextView)findViewById(R.id.Atexto_fecha1);
        Btexto_fecha2=(TextView)findViewById(R.id.Btexto_fecha2);
        Btexto_fecha4=(TextView)findViewById(R.id.Btexto_fecha4);
        Atexto_fecha3 =(TextView)findViewById(R.id.Atexto_fecha3);
        Ctexto_Calcular3=(TextView)findViewById(R.id.Ctexto_Calcular3);
        btn1_fechaA.setOnClickListener(this);
        btn2_horasA.setOnClickListener(this);
        btn3_fechaB.setOnClickListener(this);
        btn4_horasB.setOnClickListener(this);
        btn5_CalcularC.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==btn1_fechaA){
            final Calendar c= Calendar.getInstance();
            año=c.get(Calendar.YEAR);
            mes=c.get(Calendar.MONTH);
            día=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    ds1 = ((dayOfMonth > 9? dayOfMonth: "0"+(dayOfMonth))+"/"+(monthOfYear+1 > 9? monthOfYear+1: "0"+(monthOfYear+1))+"/"+year);
                    Atexto_fecha1.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                }
            }
                    ,año,mes,día);
            datePickerDialog.show();
        }
        if (v==btn2_horasA){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);
            segundos=c.get(Calendar.SECOND);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ds3 = hourOfDay+":"+minute;
                    Atexto_fecha3.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();

        }
        if(v==btn3_fechaB){
            final Calendar c= Calendar.getInstance();
            año=c.get(Calendar.YEAR);
            mes=c.get(Calendar.MONTH);
            día=c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    ds2 = ((dayOfMonth > 9? dayOfMonth: "0"+(dayOfMonth))+"/"+(monthOfYear+1 > 9? monthOfYear+1: "0"+(monthOfYear+1))+"/"+year);
                    Btexto_fecha2.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);
                }
            }
                    ,año,mes,día);
            datePickerDialog.show();
        }
        if (v==btn4_horasB){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);
            segundos=c.get(Calendar.SECOND);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ds4 = hourOfDay+":"+minute;
                    Btexto_fecha4.setText(hourOfDay+":"+minute);
                }
            },hora,minutos,false);
            timePickerDialog.show();

        }
        if  (v==btn5_CalcularC){
            if(ds1 == null || ds2 == null || ds3 == null || ds4 == null){
                Toast.makeText(this, "No se puede calcular la direfencia de horas, todos los campos deben ser completados", Toast.LENGTH_SHORT).show();
                return;
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy k:mm");

            try {
                d1 = format.parse(ds1+" "+ds3);
                d2 = format.parse(ds2+" "+ds4);
            } catch (ParseException e) {
                Toast.makeText(this, "Problema calculando tiempo", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = diff / 1000 % 60;
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            Ctexto_Calcular3.setText(diffDays + " days, "+
                    diffHours + " hours, "+
                    diffMinutes + " minutes, "+
                    diffSeconds + " seconds.");
        }
    }
}