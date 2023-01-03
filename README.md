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

# ```Ex2_1 ```
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
selon les zmanei ritsa si ils sont chavim ou pas.







# ```Ex2_2 ```

## UML of the project 
<p align="center">
  <img align="center" width=80% src = ""/>
</p>






