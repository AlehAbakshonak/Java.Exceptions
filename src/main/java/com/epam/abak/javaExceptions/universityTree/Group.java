package com.epam.abak.javaExceptions.universityTree;

import com.epam.abak.javaExceptions.educationProcess.EducationDisciplines;
import com.epam.abak.javaExceptions.userExceptions.*;

import java.util.ArrayList;

public class Group extends Faculty {
   private String groupName;
   private ArrayList<Student> studentList = new ArrayList<>();

   public Group(String universityName, String facultyName, String groupName) {
      super(universityName, facultyName);
      this.groupName = groupName;
   }

   public float countAverageMark() throws EmptyEducationalUnitException {
      if (this.studentList.size() < 0) {
         throw new EmptyEducationalUnitException("Group " + groupName + " have no students");
      }
      int sumOfMarks = 0;
      for (int i = 0; i < this.studentList.size(); i++) {
         sumOfMarks += this.studentList.get(i).countAverageMark();
      }
      return sumOfMarks / this.studentList.size();
   }

   public float countAverageMarkInDisciplineById(int id) throws EmptyEducationalUnitException {
      if (this.studentList.size() < 0) {
         throw new EmptyEducationalUnitException("Group " + groupName + " have no students");
      }
      int amountOfStudentsInDiscipline = 0;
      int sumOfMarks = 0;
      for (int i = 0; i < this.studentList.size(); i++) {
         int[][] currentDisciplineMarks = this.studentList.get(i).getDisciplinesMarks();
         for (int j = 0; j < currentDisciplineMarks.length; j++) {
            if (currentDisciplineMarks[j][0] == id) {
               amountOfStudentsInDiscipline++;
               sumOfMarks += currentDisciplineMarks[j][1];
            }
         }
      }
      if (amountOfStudentsInDiscipline == 0) {
         return 0;
      }
      else {
         return sumOfMarks / amountOfStudentsInDiscipline;
      }
   }

   public String getGroupName() {
      return groupName;
   }

   public ArrayList<Student> getStudentList() {
      return studentList;
   }

   public Student[] addNewStudents(String[] studentNames, int[][][] disciplinesMarks)
         throws EmptyNameException, WrongMarksAmountException, IllegalMarkValueException, WrongDisciplineIndexException {
      Student[] newStudents = new Student[studentNames.length];
      for (int i = 0; i < studentNames.length; i++) {
         newStudents[i] = addNewStudent(studentNames[i], disciplinesMarks[i]);
      }
      return newStudents;
   }

   public Student addNewStudent(String studentName, int[][] disciplinesMarks)
         throws EmptyNameException, WrongMarksAmountException, IllegalMarkValueException, WrongDisciplineIndexException {
      if (studentName.equals("")) {
         throw new EmptyNameException("Student name cannot be empty");
      }
      if (disciplinesMarks.length < 1 || disciplinesMarks.length > EducationDisciplines.getCount()) {
         throw new WrongMarksAmountException(
               "Student " + studentName +
                     " have wrong amount of marks. Expected from 1 to " + EducationDisciplines.getCount() +
                     "; found " + disciplinesMarks.length
         );
      }

      for (int i = 0; i < disciplinesMarks.length; i++) {
         if (disciplinesMarks[i][0] < 0
               || disciplinesMarks[i][0] > EducationDisciplines.getCount() - 1) {
            throw new WrongDisciplineIndexException(
                  "Student " + studentName +
                        " have wrong discipline index. at index " + i +
                        "; Expected from 0 to " + (EducationDisciplines.getCount() - 1) +
                        "; found " + disciplinesMarks[i][0]
            );
         }
         if (disciplinesMarks[i][1] < 1) {
            throw new IllegalMarkValueException("Student " + studentName + " have <0 mark at index " + i);
         }
         if (disciplinesMarks[i][1] > 10) {
            throw new IllegalMarkValueException("Student " + studentName + " have >10 mark at index " + i);
         }
      }
      Student newStudent = new Student(this.universityName, this.facultyName, this.groupName, studentName, disciplinesMarks);
      this.studentList.add(newStudent);
      return newStudent;
   }
}