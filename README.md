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
  <img align="center" width=80% src = "https://user-images.githubusercontent.com/55143087/211622709-24cfe4b2-bd31-4c6b-a877-756f06694672.png"/>
</p>

## Ex2_2 contains 6 classes :
### CustomExecutor.java
This class ... <br>
Explanation of each methods :
* CustomExecutor() -->
* submit(Task<T> task) -->
* submit(Callable<T> callable, TaskType type) -->
* submit(Callable callable) -->
* beforeExecute(Thread t, Runnable r) -->
* getCurrentMax() --> 
* gracefullyTerminate() -->


### Ex2_2.java
This class ... <br>
Explanation of each methods :
* partialTest() -->


### FutureTaskAdapter.java
This class extendds FutureTask ... <br>
Explanation of each methods :
* FutureTaskAdapter(Task<T> task) -->
* getPriority() -->


### FutureTaskComparator.java
This class extends Comparator ... <br>
Explanation of each methods :
* compare(FutureTaskAdapter<T> t1, FutureTaskAdapter<T> t2) -->


### Task.java
This class implements Callable <br>
Explanation of each methods :
* Task(Callable<T> CallableObject, TaskType type) -->
* createTask(Callable<T> callableObject, TaskType type) -->
* getPriority() -->
* call() -->

### TaskType.java
This class... <br>
Explanation of each methods :
* toString() -->
* TaskType(int priority) -->
* setPriority(int priority) -->
* getPriorityValue() -->
* getType() -->
* validatePriority(int priority) -->priority is represented by an integer value, ranging from 1 to 10, return  : whether the priority is valid or not

##  Description of the design and development considerations and provide techniques/patterns that we employed :

## Difficulties we have encountered and how we handled them :

## how the proposed design contributed to enhance the flexibility, performance, and maintainability of our code ?

### Design considerations
The solution we chose for this part is to implement the ThreadPool Executor ourselves. <br>
The Task class is the class that implements the Callable interface and is responsible for the execution a task. <br>
This class can be created by factory method. <br>
This class contains the following field:
TaskType is an enum that defines the type of task and its order to be executed. <br>
This class is also implement Comparable interface to compare between tasks. <br>
In order to adapt the natural order of tasks to whose priority queue, we use Adapter pattern. <br>
The TaskThread class is the class that extends Thread and is responsible to pull tasks from the priority queue of tasks and execute them. <br>
We created a class that represents the priority queue of tasks which extends Thread also and uses join to suspend the main thread until it is empty from tasks. Also can be blocked by the method block(). <br>
FutureTask class is the class that implements Future interface and is responsible to return the result of the task. <br>
The CustomThreadPoolExecutor class is the class that implements Executor interface and is responsible to execute tasks. <br>



