package com.denger.naomitian.utils;



import java.util.List;

public interface TaskFactory<T extends BasicTask> {

    void removeTask(String taskName);

    void removeTask(T task);

    List<T> getTasks();
}
