package com.cmps312.todolistapp.repository

import android.app.Application
import com.cmps312.todolistapp.datasource.local.ProjectDao
import com.cmps312.todolistapp.datasource.local.TodoDB
import com.cmps312.todolistapp.datasource.local.TodoDao
import com.cmps312.todolistapp.entity.Project
import com.cmps312.todolistapp.entity.ProjectWithTodos
import com.cmps312.todolistapp.entity.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(appContext: Application) {
    private val projectDao by lazy { TodoDB.getDatabase(appContext).projectDao() }
    private val todoDao by lazy { TodoDB.getDatabase(appContext).todoDao() }

    fun observeTodos(pid: Int): Flow<List<Todo>> = todoDao.observeTodos(pid)
    suspend fun getTodo(id: Int): Todo = todoDao.getTodo(id)
    suspend fun upsertTodo(todo: Todo): Long = todoDao.upsertTodo(todo)
    suspend fun deleteTodo(todo: Todo): Int = todoDao.deleteTodo(todo)
    fun observeProjects(): Flow<List<Project>> = projectDao.observeProjects()
    suspend fun upsertProject(project: Project) = projectDao.upsertProject(project)
    suspend fun deleteProject(project: Project) = projectDao.deleteProject(project)
    suspend fun getTodoListByProject(id: Int): List<ProjectWithTodos> = projectDao.getTodoListByProject(id)

}