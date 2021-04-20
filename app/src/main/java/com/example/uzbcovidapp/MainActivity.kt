package com.example.uzbcovidapp

import android.annotation.SuppressLint
import android.app.VoiceInteractor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        val symptomsList = ArrayList<SymptomsModel>()
        symptomsList.add(
            SymptomsModel(
                0,
                "dry cough",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            )
        )
        symptomsList.add(
            SymptomsModel(
                0,
                "dry cough",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            )
        )
        symptomsList.add(
            SymptomsModel(
                0,
                "dry cough",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            )
        )
        symptomsList.add(
            SymptomsModel(
                0,
                "dry cough",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            )
        )

        val symptomsAdapter = SymptomsAdapter(symptomsList)

        recyclerView.adapter = symptomsAdapter

        recyclerViewPrecautions.layoutManager =
            LinearLayoutManager(this, LinearLayout.HORIZONTAL, false)
        val precautionsList = ArrayList<PrecautionsModel>()
        precautionsList.add(
            PrecautionsModel(
                0,
                "dry cough",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            )
        )
        precautionsList.add(
            PrecautionsModel(
                0,
                "dry cough",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            )
        )
        precautionsList.add(
            PrecautionsModel(
                0,
                "dry cough",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            )
        )
        precautionsList.add(
            PrecautionsModel(
                0,
                "dry cough",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry."
            )
        )

        val precautionsAdapter = PrecautionsAdapter(precautionsList)

        recyclerViewPrecautions.adapter = precautionsAdapter

        getGlobalData()

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