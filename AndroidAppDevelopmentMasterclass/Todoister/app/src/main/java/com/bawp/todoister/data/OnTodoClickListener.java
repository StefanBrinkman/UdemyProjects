package com.bawp.todoister.data;

import com.bawp.todoister.model.Task;

public interface OnTodoClickListener {
    void onTodoClick(int adapterPosition, Task task);
}
