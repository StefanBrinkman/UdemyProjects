package com.bawp.todoister.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bawp.todoister.data.TodoRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    public static TodoRepository repository;
    public final LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TodoRepository(application);
        allTasks = repository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public static void insert(Task taskToAdd) {
        repository.insert(taskToAdd);
    }

    public LiveData<Task> getTask(long taskID) {
        return repository.getTask(taskID);
    }

    public static void updateTask(Task taskToUpdate) {
        repository.updateTask(taskToUpdate);
    }

    public static void deleteTask(Task taskToDelete) {
        repository.deleteTask(taskToDelete);
    }

}
