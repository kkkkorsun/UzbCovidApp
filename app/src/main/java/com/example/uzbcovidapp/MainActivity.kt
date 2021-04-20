package com.example.uzbcovidapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    val phoneNumber = "1003"
    val REQUEST_PHONE_CALL = 1

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCall.setOnClickListener{
            if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.CALL_PHONE) !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CALL_PHONE), REQUEST_PHONE_CALL)
            }else {
                startCall()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        val symptomsList = ArrayList<Model>()
        symptomsList.add(
            Model(
                R.drawable.cough,
                "Сухой кашель",
                "Согласно нескольким исследованиям, более 60% пациентов с коронавирусом имеют сухой кашель."
            )
        )
        symptomsList.add(
            Model(
                R.drawable.fever,
                "Жар",
                "У многих пациентов появляется жар, а высокая температура наблюдается у 88% заражёных Covid-19."
            )
        )
        symptomsList.add(
            Model(
                R.drawable.pain,
                "Головная боль",
                "Этот признак начинает беспокоить раньше, чем другие клинические проявления."
            )
        )
        symptomsList.add(
            Model(
                R.drawable.sore_throat,
                "Боль в горле",
                "При проникновении инфекции в организм горло является одним из первых органов, подвергающихся вирусной атаке."
            )
        )

        val symptomsAdapter = SymptomsAdapter(symptomsList)

        recyclerView.adapter = symptomsAdapter

        recyclerViewPrecautions.layoutManager =
            LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        val precautionsList = ArrayList<Model>()
        precautionsList.add(
            Model(
                R.drawable.soap,
                "Мойте руки",
                "Часто мойте руки водой с мылом или обрабатывайте их спиртосодержащим антисептиком \nдля рук."
            )
        )
        precautionsList.add(
            Model(
                R.drawable.hone,
                "Оставайтесь дома",
                "Если вы чувствуете недомогание, оставайтесь дома."
            )
        )
        precautionsList.add(
            Model(
                R.drawable.distance,
                "Социальная дистанция",
                "Держитесь на безопасном \nрасстоянии от чихающих или кашляющих людей."
            )
        )
        precautionsList.add(
            Model(
                R.drawable.clean,
                "Обрабатывайте предметы",
                "Проводите регулярную обработку и дезинфекцию поверхностей, \nк которым часто прикасаются люди."
            )
        )
        precautionsList.add(
            Model(
                R.drawable.cover,
                "Избегайте прикосновений",
                "Не прикасайтесь руками к глазам, \nрту или носу."
            )
        )

        val precautionsAdapter = PrecautionsAdapter(precautionsList)

        recyclerViewPrecautions.adapter = precautionsAdapter

        getGlobalData()

        btnKnowMore.setOnClickListener{
            var intent = Intent(this@MainActivity,KnowMoreActivity::class.java)
            startActivity(intent)
        }

        txtViewPrecautions.setOnClickListener {
            var intent = Intent(this@MainActivity,PrecautionActivity::class.java)
            startActivity(intent)
        }

        txtViewSymptoms.setOnClickListener {
            var intent = Intent(this@MainActivity,SymptomsActivity::class.java)
            startActivity(intent)
        }

    }

    private fun startCall() {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:"+phoneNumber)
        startActivity(callIntent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PHONE_CALL)startCall()
    }


    fun getGlobalData() {
        val url: String =
            "https://disease.sh/v3/covid-19/countries/uzb?yesterday=true&twoDaysAgo=true&strict=true&allowNull=true"

        val stringRequest = StringRequest(Request.Method.GET,
            url,
            Response.Listener<String> {
                var jsonObject = JSONObject(it.toString())

                txtInfected.text = jsonObject.getString("cases")
                txtRecoverd.text = jsonObject.getString("recovered")
                txtDeceased.text = jsonObject.getString("deaths")
            },
            Response.ErrorListener {
                Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG).show()
                txtInfected.text = "-"
                txtRecoverd.text = "-"
                txtDeceased.text = "-"

            }
        )

        val requestQueue = Volley.newRequestQueue(this@MainActivity)
        requestQueue.add(stringRequest)

    }
}