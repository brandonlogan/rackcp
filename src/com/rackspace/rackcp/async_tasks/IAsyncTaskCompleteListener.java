package com.rackspace.rackcp.async_tasks;

interface IAsyncTaskCompleteListener<T> {
    public void onTaskComplete(T result);
    public T doTask();
 }
