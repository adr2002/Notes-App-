package com.abdul.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.abdul.notes.MVVM.ViewModelNote
import com.abdul.notes.RoomDB.Note
import com.abdul.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NoteDeleteClickInterface, NoteClickInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ViewModelNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMain.layoutManager = LinearLayoutManager(this)

        val noteAdapter = AdapterNote(this, this, this)
        binding.rvMain.adapter = noteAdapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ViewModelNote::class.java)

        viewModel.allNotes.observe(this,{list->

            list?.let {
                noteAdapter.updateList(it)
            }

        })

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this,AddEditNoteActivity::class.java)
            startActivity(intent)

        }

    }

    override fun onDeleteNoteClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "Deleted "+ note.title, Toast.LENGTH_SHORT).show()
    }

    override fun onNoteClick(note: Note) {
        val intent = Intent(this,AddEditNoteActivity::class.java)
        intent.putExtra("noteType","Edit")
        intent.putExtra("title",note.title)
        intent.putExtra("description",note.description)
        intent.putExtra("id",note.id)
        startActivity(intent)
        this.finish()
    }
}