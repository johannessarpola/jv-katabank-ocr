# KataBankOCR 

>Johannes Sarpola, johannes.sarpola(a)gmail.com

>License: MIT


## What does it do

- [Task](http://codingdojo.org/cgi-bin/index.pl?KataBankOCR)
- It reads from 'OCR' output 'account numbers' according to the task.
- Numbers are presented as a three by three glyps with whitespace, horizontal and vertical lines
  which are characters
    - _ and |
- There migth be also some errors in the ocr's with one character getting misred
  and they should be handled as well
- Validity for each account is checked with a checksum which is defined in the Task

## How was the task solved

- Used language: Java
- Used libraries: Google Guava, Apache Commons, JodaTime, Log4j and Junit
  - Most heavily used library is Junit with others just for small features which
    are 'better' from native libraries
- Maven was used in build automation and dependency management
- IDE which was used was NetBeans
- Git was used for revision control and Bitbucket for storage
- Everything from documentation to source code is written in English like I always do

## Missing features

- There's no UI to speak of and it's usable from command line with 2 arguments
- There are probably bugs, even though there are test cases for about half the classes
- It's not very user friendly and lacks much of the feedback or expection handling
- Testing on different platforms is very lacking, only tested with Windows 10 running Java 8

## How to run

Jar can be found in the 'kata-ocr_with-test-cases.zip' which has the jar
and ocr test cases from the codingdojo webpage.

**It reads all the files from given folder and results in output which designate the source
and date it was run on.
**
To run it, you input following, in which first is the input folder and second output:

```
java -jar kata-ocr-1.01.jar ocr output

```

It'll lead to folder called output being created and which has the results
displayed in the format given in the task.



## Quick rundown on package and class structure

Here is the short explanations for the packaged found in the source code.


**fi.johannes.kata.ocr.cells**

- Package has the classes dealing with 'cells' as each 'digit' is basically a 3 x 3 character array
- Each  of the files is read with line by line basis they need to be bundled into RowBundles
- Hierarchies are as follows Cell->CellRow->CellRows and RowBundle->RowBundles
- Cells can be thought of as part of the input rows with limits defined in the ApplicationProperties.java
- RowBundle is used in constructing a set of cells as you need, in this case, 3 rows to create a single row of cells. 

**fi.johannes.kata.ocr.checksum**

- Checksum.java is a class which calculated itself and resolves is it valid. 

**fi.johannes.kata.ocr.core**

- has the high-level classes and the runnables. 
- OCR.java is the main program which is launched from Main.java
- OCREntries.java and OCREntriesOutput.java are the high level classes. First operates on multiple entries and other their output in string format. Both use the Builder pattern.
- OCRDigitResolver.java is the class used in identifying a string digit and it's alternatives as defined in the task

**fi.johannes.kata.ocr.entry**
- OCREntry.java is the upper plane unit which means a single row of digits from input file e.g. 123415
- OCREntryMethods.java is the class which has static methods for OCREntry.java, package scoped.

**fi.johannes.kata.ocr.core.data**

- Has the properties and strings hard coded to allow for a centralized configuration. 
- ApplicationData.java has dictionary definitions for OCR and their building blocks.
- ApplicationProperties.java has general properties like dimension limits and such. 
- ApplicationStrings.java has all the strings used in the app like logging messages and such.

**fi.johannes.kata.ocr.digit**

- is somewhat a self-concsious object which is built from a cell and it resolves numerical value and
  possible alternatvies for itself

**fi.johannes.kata.ocr.io**

- FolderIO.java is the parent class which is used as IO surrounding folders.
- InputFolderReader.java is a child of FolderIO which relates to files from a defined folder.
- OutputFolderWriter.java is a child of FolderIO which relates to writing output to a defined folder.

**fi.johannes.kata.ocr.utils**

- Has general utilities which are used here and there in the application.
- AppLogging.java is the class which handles the logging app-wide by static method invocations.

**fi.johannes.kata.ocr.utils.structs**

- Has models for different kinds of data.


## Thoughts

- Task was quite interesting and I got to learn something new, which is always nice. 
- It was probably on the harder spectrum of tasks from the website, but can't really evaluate this since I didn't do other tasks. Yet at least.
- In the end it was not that difficult, only required more than expected thought. Hardest part was probably the alternative digit rows, in the case
the fictional reader had made a mistake. The resolving is quite intensive process currently computationally, but not too much, you could improve by just creating cache of these permutations and use set to store them, but it was fancier this way and less typing.
- There are good number of tests in the program, as I prefer to work TDD. Mostly because that way I can be sure something works as it's designed. Could probably have cut about 25 % of time spent without much of testing.

I'll be happy to answer more quetions relating to the implementation part if there are more.

## Thanks for reading