# Sorting Partial Point Clouds along a Spatial Axis

## About

This project implements an efficient way of sorting roughly cylindrical partial point clouds for the purposes of approximating volume. After sorting a data set, it becomes easy to separate it into several very small segments, each of which's volumes can be approximated and summed to approximate the whole.

You can read more about this project [here] (/written-materials/ResearchProject_Thesis_Final.pdf).

## Versioning

**VERSION:** 3.0

**RELEASE:** N/A

**LAST UPDATED:** March 14th, 2018

## Resources

**/lib/:** Contains the external library files for the project. Specifically, jPLY was used for this project to interact with the PLY files stored under **/plys/\*:**.

**/plys/:** Contains all partial point clouds, stored in PLY format.

**/src/core/:** Contains all source files for the project.

**/written-materials/:** Contains all written materials related to the research project.

## How To Use

This project was built in Eclipse Neon for Java and so should ideally be cloned to an Eclipse Java Neon project file. The included .gitignore will ignore any unnecessary Eclipse files. This being said, using this code in another IDE with a Java compiler should also work.

It is required that one links the external library (jPLY) contained under **/lib/** to the project in one's IDE of choice. To do this in Eclipse Java Neon:
1. Right click on the project and select "Properties".
2. In the properties window select "Java Build Path" from the left and then click on "Add External JARs" on the right.
3. Navigate to **/lib/** and select "jply-0.2.0".
4. Click "OK".

To run, compile the source files and run the main function in **/src/core/Run.java**. To run the code under a test harness (using JUnit) compile and run the code in **/src/core/TestHarness.java**.

**NOTE:** Make sure you include the JUnit library in your project.

## Future Development

Plans for future development include implementing splitting, volume approximation for each segment, and volume summation for the whole approximation. For now, this collection only sorts partial point clouds into an easily manipulated form.
