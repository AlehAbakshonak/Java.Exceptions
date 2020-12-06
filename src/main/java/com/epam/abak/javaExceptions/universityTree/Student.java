package com.epam.abak.javaExceptions.universityTree;

import com.epam.abak.javaExceptions.universityTree.Group;

public class Student extends Group {
   private String studentName;
   private int[][] disciplinesMarks;

   public Student(String universityName,
                  String facultyName,
                  String groupName,
                  String studentName,
                  int[][] disciplinesMarks) {
      super(universityName, facultyName, groupName);
      this.studentName = studentName;
      this.disciplinesMarks = disciplinesMarks;
   }

   public float countAverageMark() {
      int sumOfMarks = 0;
      for (int i = 0; i < disciplinesMarks.length; i++) {
         sumOfMarks += disciplinesMarks[i][1];
      }
      return sumOfMarks / disciplinesMarks.length;
   }

   public String getStudentName() {
      return studentName;
   }

   public int[][] getDisciplinesMarks() {
      return disciplinesMarks;
   }
}
