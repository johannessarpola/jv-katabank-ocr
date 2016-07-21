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
java -jar kata-ocr-1.0.jar ocr output

```

It'll lead to folder called output being created and which has the results
displayed in the format given in the task.



## Quick rundown on package and class structure

Here is the short explanations for the packaged found in the source code.


**fi.johannes.kata.ocr.cells**

- has the classes dealing with 'cells' as each 'digit' is basically a 3 x 3 character array
- as each file is read with line by line basis they need to be bundled into RowBundles
- Hierarhies are as follows Cell->CellRow->CellRows and RowBundle->RowBundles

**fi.johannes.kata.ocr.checksum**

- is where the checksums are calculated for a integer list as was defined in the task

**fi.johannes.kata.ocr.core**

- has the high-level classes
- ocrentry is the basic unit which means a single row of digits from input file
- there are builders for a ocrentry and it's output as well
- resolvers class has the resolving part in which each digit is translated to a number
  and possible alternatives
- Builders operate on lists of entries so minimize the boiler needed in the main

**fi.johannes.kata.ocr.data**

- has the classes with static data for the whole application like strings and other
  properties
- allows a centralized management of all the specific details

**fi.johannes.kata.ocr.digit**

- is somewhat a self-concsious object which is built from a cell and it resolves numerical value and
  possible alternatvies for itself

**fi.johannes.kata.ocr.io**

- has the classes dealing wih input and output for the ocr which in this case means reading from
  disk and to it

**fi.johannes.kata.ocr.utils**

- has general classes for different utilties like logging, operating system detection and such
- some of the classes are from other project so there might be somewhat different from other classes

**fi.johannes.kata.ocr.utils.structs**

- has some basic structures to marshal data


## Thought process on some of the classes

- I generally work by clear separation from functionality and objects. This results in
  classes like Digit and DigitMethods. DigitMethods are static operations for Digit obj.
  - [Obj]Methods are generally only accessed from the relating object (e.g Digit) where
  as other classes mostly operate only Digit.  
- I try to reduce the classes to their atomic levels and thus there might be quite deep hierarchies
  like there is three step hierarchy of Cell-CellRow-CellRows
- Some of the 'data' in the application could be as well written in xml's or such files but for the
  scope it was just as to use classes to store this. (kata.ocr.data)
- Pretty much everything is boxed to 'higher' objects and it was a choice I made since there
  were going to be so many lists and I prefer to keep applications consistent.
  - It'll require more memory to run and Java is quite keen in hogging memory with these objects,
    so I'd probably rely more on primitives in a more demanding application.
- There was quite heavy TDD in this projects as I prefer to be sure that a class I made works like
  it was planned to. Still with the given timeframe an scope some aspects were left untested.

I'll be happy to answer more quetions relating to the implementation part if there are more.

## Thanks for reading