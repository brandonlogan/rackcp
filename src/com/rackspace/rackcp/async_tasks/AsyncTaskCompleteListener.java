package com.rackspace.rackcp.async_tasks;

interface AsyncTaskCompleteListener<T> {
    public void onTaskComplete(T result);
    public T doTask();
 }
