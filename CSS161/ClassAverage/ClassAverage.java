import java.util.Scanner;
import java.io.File;
import java.io.IOException;

//authors: Fukuda, Zander, (edited by Nash) also edited by Anneliese Pessoa

public class ClassAverage {
    //... class constants go here
    
    public static void main(String[] args) throws IOException {
        int courseNumber;                     // number of the course
        Scanner inputFile = null;             // file containing data

        inputFile = new Scanner(new File("courseData.txt"));
        //initialize weight variables by reading the first three doubles in the text file
        double programsWeight = inputFile.nextDouble();
        double midtermWeight = inputFile.nextDouble();
        double finalWeight = inputFile.nextDouble();

        //Per class, print a table of lID numbers, grades, weighted average
        // per student, and a Pass or Fail programs grade.  
        // The class average is also printed.
        for (int count = 0; inputFile.hasNext(); count ++) {                         
            // Read class number, print class number title, headings.
            courseNumber = inputFile.nextInt();

            //print header
            header(courseNumber);

            // initialization
            int numStudents = 0; //counts the number of students to calculate the class average
            int studentID = 1; 
            int programsGrade = 0;
            int midtermGrade = 0;
            int finalGrade = 0;
            double weightedAverage = 0.0; //weighted average for each student
            double classAverage = 0.0; //average grade for the entire class
            String passFail = ""; //string to display whether the student passed the programs or not

            // Loop to handle one class.
            //For each student in the class, get and print their information, 
            // compute their avg, and sum the avgs
            while (studentID != 0) { 
                studentID = inputFile.nextInt(); //sets the student ID to equal the next integer in the file
                //if the next number is zero, break the while loop and start going through another class's grade data
                if(studentID == 0){ 
                    break;
                }
                numStudents ++; //add student to class
                programsGrade = inputFile.nextInt(); //finds the student's programs grade
                midtermGrade = inputFile.nextInt(); //finds the student's midterm grade
                finalGrade = inputFile.nextInt(); //finds the student's final exam grade

                //calculate weighted average from the programs, midterm, and final exam grades
                weightedAverage = average(programsWeight, programsGrade, midtermWeight, midtermGrade, finalWeight, finalGrade);

                //determine whether the programs grade is passing or failing by testing if it is greater than or equal to 70
                if(programsGrade >=70){
                    passFail = "Pass";
                }else{
                    passFail = "Fail";
                }

                //print a single student's grade data in one line
                printStudentData(studentID, programsGrade, midtermGrade, finalGrade, weightedAverage, passFail);

                //add the student's weighted average to the class average
                classAverage += weightedAverage;

                System.out.println();
            }

            // compute and print class average
            classAverage /= numStudents; //divide sum of all student's grades by the number of students
            System.out.printf("Class average: %.2f",  classAverage); //print out the class average with two decimal points
            System.out.println("\n");
        }
        
        inputFile.close();
    }

    public static void header(int courseNumber){
        //print class number
        System.out.println("Grade Data for Class " + courseNumber);
        System.out.println();
        //print out headings
        System.out.println(" ID   Programs  Midterm  Final  Weighted Average  Programs grade");
        System.out.println(" --   --------  -------  -----  ----------------  --------------");
    }

    public static double average(double programsWeight, int programsGrade, double midtermWeight, int midtermGrade, double finalWeight, int finalGrade){
        //returm the weighted average for the student
        return (programsWeight*programsGrade) +(midtermWeight*midtermGrade) +( finalWeight*finalGrade);
    }

    public static void printStudentData(int ID, int programs, int midterm, int finalGrade, double weightedAverage, String passFail){
        //format average to have two decimals
        String average = String.format("%.2f", weightedAverage);
        //print a single student's data in one line
        System.out.print(ID + "     " + programs + "       " + midterm + "       " + finalGrade + "         " + average + "       " + passFail);

    }
}
