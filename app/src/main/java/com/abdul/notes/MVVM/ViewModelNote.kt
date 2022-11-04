package com.abdul.notes.MVVM

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.abdul.notes.RoomDB.DatabseNote
import com.abdul.notes.RoomDB.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelNote(application: Application): AndroidViewModel(application) {

    val allNotes : LiveData<List<Note>>
    val repoNote: RepoNote

    init {
        val dao =  DatabseNote.getDatabase(application).getDao()
        repoNote = RepoNote(dao)
        allNotes = repoNote.allNote
    }

    fun deleteNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repoNote.deleteNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repoNote.updateNote(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repoNote.insertNote(note)
    }
}