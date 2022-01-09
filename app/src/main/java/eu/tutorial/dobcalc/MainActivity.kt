package eu.tutorial.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
  private  var tvSelectedDate : TextView? =null
    private  var tvAgeINMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAgeINMinutes = findViewById(R.id.tvAgeInMinutes)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        val btnDatePicker:Button =findViewById(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener(){
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year =myCalendar.get(Calendar.YEAR)
        val month =myCalendar.get(Calendar.MONTH)
        val day =myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd =  DatePickerDialog(this,
            { _, selectedYear, selectedmonth, selecteddayOfMonth ->

                Toast.makeText(this,"Selected Year was $selectedYear , Month was ${selectedmonth+1}" +
                        " , Day of Month was $selecteddayOfMonth",
                    Toast.LENGTH_LONG).show()

                val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedYear"
                tvSelectedDate?.text = selectedDate
                val sdf =SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                  currentDate?.let {
                      val currentDateInMinutes = currentDate.time / 60000

                      val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                      tvAgeINMinutes?.text = differenceInMinutes.toString()
                   }
                }
            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate= System.currentTimeMillis()-86400000
        dpd.show()



    }
}