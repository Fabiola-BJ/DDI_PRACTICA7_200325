package mx.edu.utxj.tidgs.ddi.practica7_200561.presentation

import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.activity.ComponentActivity
import mx.edu.utxj.tidgs.ddi.practica7_200561.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() { // Reemplaza con el nombre de tu Activity
    private lateinit var clockTextView: TextView
    private lateinit var saludoTextView: TextView
    private lateinit var handler: Handler
    private lateinit var updateTimeRunnable: Runnable
    private var mHandler: Handler? = null
    private var mRunnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        clockTextView = findViewById(R.id.clockTextView)
        saludoTextView = findViewById(R.id.saludo)
        val calendar = Calendar.getInstance()
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val saludo: String = when(hourOfDay) {
            in 4..12 -> " Buenos Dias"
            in 12..20 -> "Buenas Tardes"
            else -> "Buenas Noches Nalgona 7w7"
        }

        saludoTextView.text = saludo

        handler = Handler()
        updateTimeRunnable = object : Runnable {
            override fun run() {
                val currentTime = Calendar.getInstance().time
                val dateFormat =  SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                val formattedTime = dateFormat.format(currentTime)
                clockTextView.text = formattedTime

                // Se actualiza después de 1 segundo
                saludoTextView.text = saludo
                handler.postDelayed(this, 1000)
            }
        }
    }


    override fun onResume() {
        super.onResume()
        handler.post(updateTimeRunnable)
    }
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(updateTimeRunnable)
    }
}