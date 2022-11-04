package com.abdul.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.abdul.notes.RoomDB.Note

class AdapterNote(private val context: Context,
                  val noteClickInterface: NoteClickInterface,
                  val noteDeleteClickInterface: NoteDeleteClickInterface)
    : RecyclerView.Adapter<AdapterNote.ViewHolder>() {

    private val allNote = ArrayList<Note>()

   inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.titleNote)
        val descroption = itemView.findViewById<TextView>(R.id.descriptionNote)
        val timeStamp = itemView.findViewById<TextView>(R.id.timestamp)
       val deleteBtn = itemView.findViewById<ImageView>(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.sample_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.setText(allNote.get(position).title)
        holder.descroption.setText(allNote.get(position).description)
        holder.timeStamp.setText(allNote.get(position).timeStamp)

        holder.deleteBtn.setOnClickListener {
            noteDeleteClickInterface.onDeleteNoteClick(allNote.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(allNote.get(position))
        }
    }

    override fun getItemCount(): Int {
       return allNote.size
    }

    fun updateList(newList: List<Note>){
        allNote.clear()
        allNote.addAll(newList)
        notifyDataSetChanged()
    }
}

interface NoteClickInterface{
     fun onNoteClick(note: Note)
}

interface NoteDeleteClickInterface{
    fun onDeleteNoteClick(note: Note)
}