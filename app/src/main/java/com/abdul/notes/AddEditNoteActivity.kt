package com.abdul.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Note
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abdul.notes.MVVM.ViewModelNote
import com.abdul.notes.databinding.ActivityAddEditNoteBinding
import java.text.SimpleDateFormat
import java.util.*

class AddEditNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddEditNoteBinding
    lateinit var viewModelNote: ViewModelNote
    var noteId = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelNote = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(ViewModelNote::class.java)

        val noteType = intent.getStringExtra("noteType")

        if (noteType == "Edit"){
            val noteTitle = intent.getStringExtra("title")
            val noteDescription = intent.getStringExtra("description")
            noteId = intent.getIntExtra("id",-1)

            binding.noteBtn.setText("Update note")
            binding.noteTitle.setText(noteTitle)
            binding.noteDesp.setText(noteDescription)

        }else{
            binding.noteBtn.setText("Add Note")
        }

        binding.noteBtn.setOnClickListener {

            val noteTitle = binding.noteTitle.text.toString()
            val noteDesp = binding.noteDesp.text.toString()

            if (noteType.equals("Edit")){
                if (binding.noteTitle.text.isNotEmpty() && binding.noteDesp.text.isNotEmpty()){

                    val date = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val cDate:String = date.format(Date())

                    val updateNote = com.abdul.notes.RoomDB.Note(noteTitle, noteDesp, cDate)

                    updateNote.id = noteId
                    viewModelNote.updateNote(updateNote)

                    Toast.makeText(this, "Update note..", Toast.LENGTH_SHORT).show()
                }

                }else{

                if (binding.noteTitle.text.isNotEmpty() && binding.noteDesp.text.isNotEmpty()){
                    val date = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val cDate:String = date.format(Date())

                    viewModelNote.insertNote(com.abdul.notes.RoomDB.Note(noteTitle, noteDesp, cDate))

                    Toast.makeText(this, "Note added..", Toast.LENGTH_SHORT).show()

                }
            }
            startActivity(Intent(applicationContext,MainActivity::class.java))
            this.finish()
        }
    }
}