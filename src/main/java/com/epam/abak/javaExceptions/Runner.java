package com.epam.abak.javaExceptions;

import com.epam.abak.javaExceptions.educationProcess.EducationDiscipline;
import com.epam.abak.javaExceptions.educationProcess.EducationDisciplines;
import com.epam.abak.javaExceptions.universityTree.Faculty;
import com.epam.abak.javaExceptions.universityTree.Group;
import com.epam.abak.javaExceptions.universityTree.Student;
import com.epam.abak.javaExceptions.universityTree.University;
import com.epam.abak.javaExceptions.userExceptions.*;

public class Runner {
   public static void main(String[] args) {
      University hogwarts;
      Faculty[] faculties;
      Group[][] groups;
      Student[][][] students;
      try {
         hogwarts = University.createNewUniversity("Hogwarts");
         faculties = hogwarts.addNewFaculties(new String[]{"Gryffindor", "Slytherin"});

         groups = new Group[][]{
               faculties[0].addNewGroups(new String[]{"Gry Year 1", "Gry Year 2"}),
               faculties[1].addNewGroups(new String[]{"Sly Year 1", "Sly Year 2"}),
         };

         students = new Student[][][]{
               {
                     groups[0][0].addNewStudents(
                           new String[]{"Gry Year 1 Stud"},
                           new int[][][]{
                                 {{0, 2}, {1, 4}, {3, 6}}
                           }
                     ),
                     groups[0][1].addNewStudents(
                           new String[]{"Gry Year 2 Stud"},
                           new int[][][]{
                                 {{2, 8}, {3, 10}}
                           }
                     )
               },
               {
                     groups[1][0].addNewStudents(
                           new String[]{"Sly Year 1 Stud"},
                           new int[][][]{
                                 {{0, 1}, {1, 3}, {3, 5}}
                           }
                     ),
                     groups[1][1].addNewStudents(
                           new String[]{"Sly Year 2 Stud"},
                           new int[][][]{
                                 {{1, 7}, {2, 9}}
                           }
                     )
               }
         };
         Student currentStudent = students[0][0][0];
         System.out.printf("Average mark of student %s (Group %s of faculty %s): %.0f\n",
               currentStudent.getStudentName(),
               currentStudent.getGroupName(),
               currentStudent.getFacultyName(),
               currentStudent.countAverageMark()
         );

         EducationDiscipline currentEducationDiscipline = EducationDisciplines.getDisciplineById(0);
         Group currentGroup = groups[0][1];
         System.out.printf("Average mark in discipline %s in group %s: %.0f\n",
               currentEducationDiscipline.getName(),
               currentGroup.getGroupName(),
               currentGroup.countAverageMarkInDisciplineById(0)
         );

         currentEducationDiscipline = EducationDisciplines.getDisciplineById(1);
         Faculty currentFaculty = faculties[1];
         System.out.printf("Average mark in discipline %s in faculty %s: %.0f\n",
               currentEducationDiscipline.getName(),
               currentGroup.getFacultyName(),
               currentFaculty.countAverageMarkInDisciplineById(1)
         );

         currentEducationDiscipline = EducationDisciplines.getDisciplineById(2);
         System.out.printf("Average mark in discipline %s in university %s: %.0f\n",
               currentEducationDiscipline.getName(),
               hogwarts.getUniversityName(),
               hogwarts.countAverageMarkInDisciplineById(2)
         );
      } catch (EmptyEducationalUnitException |
            WrongDisciplineIndexException |
            WrongMarksAmountException |
            IllegalMarkValueException |
            EmptyNameException ex) {
         System.out.println(ex.getClass().getName()+": "+ex.getMessage());
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }
   }
}
