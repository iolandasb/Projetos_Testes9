package com.example.projetointegrador.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projetointegrador.R
import com.example.projetointegrador.data.model.InfosCast
import de.hdodenhof.circleimageview.CircleImageView

class CastAdapter(val context: Context, var dataSetCast: MutableList<InfosCast> = mutableListOf()) : RecyclerView.Adapter<CastAdapter.RecyclerviewViewHolder>() {

    class RecyclerviewViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var picture = view.findViewById<CircleImageView>(R.id.imgPerson)
        var characterName = view.findViewById<TextView>(R.id.txtName)
        var role = view.findViewById<TextView>(R.id.txtRole)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerviewViewHolder = RecyclerviewViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.activity_cast, parent, false)
    )

    override fun onBindViewHolder(holder: RecyclerviewViewHolder, position: Int) {
        if(dataSetCast[position].profile_path !== ""){
            holder.picture?.let { Glide.with(context).load("https://image.tmdb.org/t/p/w500" + dataSetCast[position].profile_path).into(it) }
        }
        holder.characterName.text = dataSetCast[position].name
        holder.role.text = dataSetCast[position].character
    }

    override fun getItemCount() = dataSetCast.size

}