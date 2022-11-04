package com.abdul.notes.MVVM

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.abdul.notes.RoomDB.DAONote
import com.abdul.notes.RoomDB.Note

class RepoNote(val daoNote: DAONote) {

    val allNote: LiveData<List<Note>> = daoNote.getAllNotes()


    suspend fun insertNote(note: Note) {
        daoNote.insertNote(note)
    }


    suspend fun updateNote(note: Note) {
        daoNote.updateNote(note)
    }


    suspend fun deleteNote(note: Note) {
        daoNote.deleteNote(note)
    }
}