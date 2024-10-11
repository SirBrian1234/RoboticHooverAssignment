# RoboticHooverAssignment
A Java Backend Developer's assignment, based on a robotic hoover that has to mop a 2D room [Backend_Engineer_Assignment.md](Backend_Engineer_Assignment.md)

## Constraints

* You should have a working version of JDK 21 installed on your system
* You should have maven installed
* You should have git tools installed

## How to use

clone the project
```
git clone https://github.com/SirBrian1234/RoboticHooverAssignment.git
```

move into the project's dir
```
cd RoboticHooverAssignment
```

Test and build the project
```
mvn clean package
```

When the project is built, you can move to the `./target` dir and run the app. 
The app accepts an input file as shown below and produces the result both in the terminal as in an output file named `output.json`. 

```
cd target
java -jar robotichooverassignment-1.0-SNAPSHOT.jar inputCoordinateFile.json
```

## Performance

The application may generate a 2D room of up to **500.000.000** total spots. If a bigger room is defined, the application will prevent the user from executing the script file.
