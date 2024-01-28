package com.cmps312.todolistapp.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.entity.ProjectWithTodos
import com.cmps312.todolistapp.entity.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    @Query("SELECT * FROM Project")
    fun observeProjects() : Flow<List<Project>>

    @Upsert
    suspend fun upsertProject(project: Project)

    @Delete
    suspend fun deleteProject(project: Project)

    @Query("SELECT * FROM Project WHERE id=:pid")
    suspend fun getTodoListByProject(pid: Int) : List<ProjectWithTodos>
}