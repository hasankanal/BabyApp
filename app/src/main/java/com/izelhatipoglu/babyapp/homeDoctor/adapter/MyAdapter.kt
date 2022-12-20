package com.izelhatipoglu.babyapp.homeDoctor.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.izelhatipoglu.babyapp.databinding.RecylerRowBinding
import com.izelhatipoglu.babyapp.model.DoctorModel


class MyAdapter(val list: ArrayList<DoctorModel>): RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecylerRowBinding ) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecylerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.userNamePregnant.text = list.get(position).userName
    }

    override fun getItemCount(): Int {
        return list.size
    }
}