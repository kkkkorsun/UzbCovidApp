package com.example.uzbcovidapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_symptoms.*

class SymptomsActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_symptoms)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
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
    }
}