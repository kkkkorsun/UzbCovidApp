package com.example.uzbcovidapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_precaution.*

class PrecautionActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_precaution)


        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val precautionsList = ArrayList<Model>()
        precautionsList.add(
            Model(
                R.drawable.soap,
                "Мойте руки",
                "Часто мойте руки водой с мылом или обрабатывайте их спиртосодержащим антисептиком для рук."
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
                "Держитесь на безопасном расстоянии от чихающих или кашляющих людей."
            )
        )
        precautionsList.add(
            Model(
                R.drawable.clean,
                "Обрабатывайте предметы",
                "Проводите регулярную обработку и дезинфекцию поверхностей, к которым часто прикасаются люди."
            )
        )
        precautionsList.add(
            Model(
                R.drawable.cover,
                "Избегайте прикосновений",
                "Не прикасайтесь руками к глазам, рту или носу."
            )
        )


        val precautionsAdapter = PrecautionsAdapter(precautionsList)

        recyclerView.adapter = precautionsAdapter
    }
}