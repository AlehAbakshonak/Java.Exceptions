package com.epam.abak.javaExceptions.universityTree;

import com.epam.abak.javaExceptions.userExceptions.EmptyEducationalUnitException;
import com.epam.abak.javaExceptions.userExceptions.EmptyNameException;

import java.util.ArrayList;

public class University {
   protected String universityName;
   protected int facultyAmount;
   protected ArrayList<Faculty> facultyList = new ArrayList<>();

   public University(String universityName) {
      this.universityName = universityName;
      this.facultyAmount = 0;
   }

   public float countAverageMark() throws EmptyEducationalUnitException {
      if (this.facultyList.size() < 0) {
         throw new EmptyEducationalUnitException("University " + universityName + " have no faculties");
      }
      int sumOfMarks = 0;
      for (int i = 0; i < this.facultyList.size(); i++) {
         sumOfMarks += this.facultyList.get(i).countAverageMark();
      }
      return sumOfMarks / this.facultyList.size();
   }

   public float countAverageMarkInDisciplineById(int id) throws EmptyEducationalUnitException {
      if (this.facultyList.size() < 0) {
         throw new EmptyEducationalUnitException("University " + universityName + " have no faculties");
      }
      int amountOfFacultiesInDiscipline = 0;
      int sumOfMarks = 0;
      for (int i = 0; i < this.facultyList.size(); i++) {
         float currentFacultyMarkInDiscipline = this.facultyList.get(i).countAverageMarkInDisciplineById(id);
         if (currentFacultyMarkInDiscipline > 0) {
            amountOfFacultiesInDiscipline++;
            sumOfMarks += currentFacultyMarkInDiscipline;
         }
      }
      if (amountOfFacultiesInDiscipline == 0) {
         return 0;
      } else {
         return sumOfMarks / amountOfFacultiesInDiscipline;
      }
   }

   public static University createNewUniversity(String universityName) throws EmptyNameException {
      if (!universityName.equals("")) {
         return new University(universityName);
      } else {
         throw new EmptyNameException("University name cannot be empty");
      }
   }

   public String getUniversityName() {
      return universityName;
   }

   public int getFacultyAmount() {
      return facultyAmount;
   }

   public ArrayList<Faculty> getFacultyList() {
      return facultyList;
   }

   public Faculty[] addNewFaculties(String[] facultyNames) throws EmptyNameException {
      Faculty[] newFaculties = new Faculty[facultyNames.length];
      for (int i = 0; i < facultyNames.length; i++) {
         newFaculties[i] = addNewFaculty(facultyNames[i]);
      }
      return newFaculties;
   }

   public Faculty addNewFaculty(String facultyName) throws EmptyNameException {
      if (!facultyName.equals("")) {
         Faculty newFaculty = new Faculty(this.universityName, facultyName);
         this.facultyList.add(newFaculty);
         this.facultyAmount++;
         return newFaculty;
      } else {
         throw new EmptyNameException("Faculty name cannot be empty");
      }
   }
}
