# OOP_Ex2
Third Assignment in Object-oriented programming course in Java @Ariel_University_2022

# Authors :
* Boritsky Jonatan : 207254194
* Souffir Ilan Meir : 342615648

# Dependencies

* ``JAVA`` JDK 19.0.1
* ``JUNIT`` Jupiter 5.9.0
* ``JUnit Platform Launcher`` 1.4.0

### IDE platform
* ``IntelliJ IDEA`` 2021.1.1 (Community Edition)

# Topic of the Assignment 
In this assignment we have two parts:
* ```Part 1 : Ex2_1``` -->  It's a class that creates several text files and calculate the total number of lines in these files. We use three methods to do this : 1) Normal method without Threads  2) Method with Threads  3) Method with ThreadPool 

* ```Part 2 : Ex2_2``` -->

# How to run Ex2 ?
For Windows OS:  
1. Make sure you have git installed on your machine
2. Download the project from GitHub
```
$ git clone https://github.com/ilan2505/OOP_EX2.git
```
4. For Ex2_1: all you have to do is to run the Main function in the Ex2_1.java by changing the parameters (n, seed, bound) that you want.
5. For Ex2_2: you need to run the main Ex2_2 to see how the tests work.

# Ex2_1 
## UML of the project 
<p align="center">
  <img align="center" width=80% src = "https://user-images.githubusercontent.com/55143087/210436830-59020281-6a61-4902-8bca-98aa585104f9.png"/>
</p>

## Ex2_1 contains 4 classes :
### Ex2_1.java
This class creates several text files and calculate the total number of lines in these files with methods using nothing, threads and threadPool.<br>
Explanation of each methods :
* createTextFiles(int n, int seed, int bound) --> This method creates n text files on disk and returns an array of the file names. In each line we write a sentence : "Hello World".
* getNumOfLines(String[] fileNames) --> Gives the total number of lines in these files. This method doesn't use any thread.
* getNumOfLinesThreads(String[] fileNames) --> Gives the total number of lines in these files using Threads.
* getNumOfLinesThreadPool(String[] fileNames) --> Gives the total number of lines in these files using ThreadPool.

### functions.java
This class contains helps functions that we use in our Ex2_1.java class.<br>
Explanation of each methods :
* count_lines(String file_name) --> Opens a file and counts the lines inside the file.
* create_files(int num_file) --> Creates a new file with the name "file_X".
* write_files(String file_name, int num_lines) --> Writes "Hello World" in each line of the file.

### FileLineCounter.java
This class extends Thread that we need to use threads in our function number 3 "getNumOfLinesThreads(String[] fileNames)". <br>
Objects of this class :
* fileName --> Name of the file.
* numOfLines --> Number of lines.<br><br>
Explanation of each methods :
* FileLineCounter(String fileName) --> Constructor.
* run() --> Run method for the third function with threads.
* getNumOfLines() --> Gives the num of lines into threads.

### FileLineCounterCallable.java
This class is an implement of Callable that we need to use ThreadPool in our function number 4 "getNumOfLinesThreadPool(String[] fileNames)".<br>
Objects of this class :
* fileName --> Name of the file.
* numOfLines --> Number of lines.<br><br>
Explanation of each methods :
* FileLineCounterCallable(String fileName) --> Constructor.
* call() --> Call method for the forth function with ThreadPool.

## Results for our 3 functions 
### For n=100, seed=42, bound=999 :
```
[Normal Example]
Number of lines : 54096
Time : 93 ms

[Example using Threads]
Number of lines : 54096
Time : 80 ms

[Example using Threads Pool]
Number of lines : 54096
Time : 78 ms
```
### For n=1 000, seed=42, bound=9 999 :
```
[Normal Example]
Number of lines : 4945060
Time : 1157 ms

[Example using Threads]
Number of lines : 4945060
Time : 621 ms

[Example using Threads Pool]
Number of lines : 4945060
Time : 591 ms
```
### For n=1 000, seed=42, bound=99 999 :
```
[Normal Example]
Number of lines : 49029445
Time : 3674 ms

[Example using Threads]
Number of lines : 49029445
Time : 1892 ms

[Example using Threads Pool]
Number of lines : 49029445
Time : 1818 ms
```
### For n=10 000, seed=42, bound=99 999 :
```
[Normal Example]
Number of lines : 498839180
Time : 49523 ms

[Example using Threads]
Number of lines : 498839180
Time : 24363 ms

[Example using Threads Pool]
Number of lines : 498839180
Time : 23994 ms
```
### Explanation of the results we saw before :
We can conclude two important things: 
* the first is that the first method without using thread or thread pool is much slower than the other two methods. 
* the second is that for the other two methods (thread and threadPool) they are roughly equal with a slight advantage for threadPool which is a tiny bit faster. <br><br>

The reason is simple, it's just that thread and threadPool methods can perform several tasks at the same time unlike the first method which does one by one.
We can also see that the more files we have with more and more lines, the more the 3 methods will take longer to give the total number of lines in all the files. Conclusion always use Thread or ThreadPool depending on what you need to do in parallel !






# Ex2_2 

## UML of the project 
<p align="center">
    ![FutureTaskComparator](https://user-images.githubusercontent.com/73108322/212267478-9d0bad8d-146a-4e73-892d-2f9f29fdf01c.png)
    
</p>

## Ex2_2 contains 6 classes :
### CustomExecutor.java
This class represents a custom executor service, implemented by extending ThreadPoolExecutor. <br>
Explanation of each methods :
* CustomExecutor() --> creates with number of threads a new custom executor.
* submit(Task<T> task) --> gives a future task to the executor service.
* submit(Callable<T> callable, TaskType type) --> submits a task with a task type, and gives this task to the executor service.
* submit(Callable callable) --> call the submit function with the default task type OTHER.
* beforeExecute(Thread t, Runnable r) --> This function is called when the thread pool is about to execute a task. The function cast the task to a future task adapter and get th priority of the task and assign it to the CurrentMax.
* getCurrentMax() --> returns the current max priority of the executed task in the queue.
* gracefullyTerminate() --> end off the executor service.


### Ex2_2.java
This class is a main class to run the tests. <br>
Explanation of each methods :
* partialTest() --> differents tests.


### FutureTaskAdapter.java
This class provides an adapter for task in order to enable the use of the inner priority queue of thread pool, which contains only runnable objects. <br>
Explanation of each methods :
* FutureTaskAdapter(Task<T> task) --> Constructor of the FutureTaskAdapter class.
* getPriority() --> returns the priority of the task.


### FutureTaskComparator.java
This class implements Callable. This class provides a comparator for future tasks. It compares between two future tasks by their tasks natural order. <br>
Explanation of each methods :
* compare(FutureTaskAdapter<T> t1, FutureTaskAdapter<T> t2) --> Compares the task with the task for order.


### Task.java
This class represents a task that can be executed by a custom thread pool. <br>
Explanation of each methods :
* Task(Callable<T> CallableObject, TaskType type) --> creates a new task with the given task and the task type.
* createTask(Callable<T> callableObject, TaskType type) --> creates a task with the given task and the task type.
createTask(Callable<T> callableObject) --> creates a task with the given task.
* getPriority() --> get the priority value of the type.
* call() --> calls the method for the task.

### TaskType.java
This class... <br>
Explanation of each methods :
* validatePriority(int priority) -->priority is represented by an integer value, ranging from 1 to 10, return  : whether the priority is valid or not

##  Description of the design and development considerations and provide techniques/patterns that we employed :
The solution we chose for this part is to adapt the ThreadPoolExecutor class to our needs. We did this by creating a custom executor service that extends ThreadPoolExecutor,
to scheduling the tasks in the queue according to their priority. <br>

Design patterns used:
1) ``Factory method pattern:`` We used this pattern to create the tasks.
2) ``Adapter pattern:`` We used this pattern to adapt Task to Runnable due to the inner priority queue constraints og the ThreadPoolExecutor, using FutureTaskAdapter
which implements Future to store the future result.

OOP principles used:
1) ``Inheritance:`` We used inheritance to extend the ThreadPoolExecutor class.
2) ``Interface:`` We used interface to define the Task class as Callable.

We used the following techniques:
1) ``Priority queue:`` We used a priority queue to store the tasks in the queue according to their priority.
2) ``Future:`` We used Future to store the future result of the task.
3) ``Callable:`` We used Callable to define the task as a callable object.
4) ``Comparator:`` We used Comparator to compare between two tasks according to their priority.


## Difficulties we have encountered and how we handled them :
We had encountered with the following difficulties:
1) We didn't succeed to find a way to avoid casting the Runnable object to FutureTaskAdapter in the beforeExecute method. Even though it stands in contradiction with one of the SOLID principles, we decided to leave it as it is.
2) We have noticed that the main thread reach the GetCurrentMax method before the thread pool executes the tasks called by the main thread. This is because the main thread is not a part of the thread pool.

## how the proposed design contributed to enhance the flexibility, performance, and maintainability of our code ?
The proposed design helps to enhance the flexibility, performance, and maintainability of our code in the following ways:
1) ``Flexibility:`` The proposed design allows us to change the order in which the tasks are executed in the queue according to their priority. By simply changing the comparator, we can change the order in which the tasks are executed.
2) ``Performance:`` The proposed design relies on the priority queue which is a data structure that is implemented using a heap. This data structure allows us to insert and remove elements in O(log(n)) time complexity. This is much better than the O(n) time complexity of the linked list. Additionally, by using the built-in methods of ThreadPoolExecutor, we can make sure that the queue is thread-safe.
3) ``Maintainability:`` The code is simply and by using familiar APIs and design patterns makes it easy to understand and maintain.


