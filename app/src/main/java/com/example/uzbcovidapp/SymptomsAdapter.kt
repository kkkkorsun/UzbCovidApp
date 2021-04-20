package com.example.uzbcovidapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SymptomsAdapter(var symptomsList: ArrayList<Model>) :
    RecyclerView.Adapter<SymptomsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SymptomsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SymptomsAdapter.ViewHolder, position: Int) {
        val symptomsModel = symptomsList[position]
        holder.bind(symptomsModel)

    }

    override fun getItemCount(): Int {
        return symptomsList.size

    }

    class ViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_symptoms, viewGroup, false)) {

        fun bind(symptomsModel: Model) {
            val symptomsText = itemView.findViewById<TextView>(R.id.txtSymptoms)
            val symptomsTextDetail = itemView.findViewById<TextView>(R.id.txtSymptomsDetail)

            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            imageView.setImageResource(symptomsModel.imageView)
            symptomsText.text = symptomsModel.symptomsText
            symptomsTextDetail.text = symptomsModel.symptomsDetail
        }

    }
}