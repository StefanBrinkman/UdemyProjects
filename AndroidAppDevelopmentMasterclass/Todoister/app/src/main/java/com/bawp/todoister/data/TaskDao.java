package com.bawp.todoister.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bawp.todoister.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    // Insert a new task
    @Insert
    void insertTask(Task task);

    // Delete entire table
    @Query("DELETE FROM task_table")
    void deleteAll();

    // Get all tasks
    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getTasks();

    // Get the task with the selected ID
    @Query("SELECT * FROM task_table WHERE task_table.task_id == :id")
    LiveData<Task> getTask(long id);

    // Update a task
    @Update
    void update(Task task);

    // Delete a task
    @Delete
    void delete(Task task);
}
