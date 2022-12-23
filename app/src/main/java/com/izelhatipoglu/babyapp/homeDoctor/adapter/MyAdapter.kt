package com.izelhatipoglu.babyapp.homeDoctor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.RecyclerView
import com.izelhatipoglu.babyapp.R
import com.izelhatipoglu.babyapp.databinding.RecylerRowBinding
import com.izelhatipoglu.babyapp.model.DoctorModel
import com.izelhatipoglu.babyapp.pregnantDoctor.PregnantFragment


class MyAdapter(list: ArrayList<DoctorModel>, val onItemClicked: IsClicked): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var momList = list

    class ViewHolder(val binding: RecylerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecylerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.userNamePregnant.text = momList[position].userName
        holder.itemView.setOnClickListener {
            onItemClicked.clicked(momList[position].userName)
        }
    }


    override fun getItemCount(): Int {
        return momList.size
    }

    fun updateList(newMomList : ArrayList<DoctorModel>){
        momList.clear()
        momList.addAll(newMomList)
        notifyDataSetChanged()
    }

    interface IsClicked{
        fun clicked(name: String)
    }
}