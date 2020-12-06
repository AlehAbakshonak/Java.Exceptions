package com.epam.abak.javaExceptions.universityTree;

import com.epam.abak.javaExceptions.userExceptions.EmptyEducationalUnitException;
import com.epam.abak.javaExceptions.userExceptions.EmptyNameException;

import java.util.ArrayList;

public class Faculty extends University {
   protected String facultyName;
   protected int groupAmount;
   protected ArrayList<Group> groupList = new ArrayList<>();

   public Faculty(String universityName, String facultyName) {
      super(universityName);
      this.facultyName = facultyName;
      this.groupAmount = 0;
   }

   public float countAverageMark() throws EmptyEducationalUnitException {
      if (this.groupList.size() < 0) {
         throw new EmptyEducationalUnitException("Faculty " + facultyName + " have no groups");
      }
      int sumOfMarks = 0;
      for (int i = 0; i < this.groupList.size(); i++) {
         sumOfMarks += this.groupList.get(i).countAverageMark();
      }
      return sumOfMarks / this.groupList.size();
   }

   public float countAverageMarkInDisciplineById(int id) throws EmptyEducationalUnitException {
      if (this.groupList.size() < 0) {
         throw new EmptyEducationalUnitException("Faculty " + facultyName + " have no groups");
      }
      int amountOfGroupsInDiscipline = 0;
      int sumOfMarks = 0;
      for (int i = 0; i < this.groupList.size(); i++) {
         float currentGroupMarkInDiscipline = this.groupList.get(i).countAverageMarkInDisciplineById(id);
         if (currentGroupMarkInDiscipline > 0) {
            amountOfGroupsInDiscipline++;
            sumOfMarks += currentGroupMarkInDiscipline;
         }
      }
      if (amountOfGroupsInDiscipline == 0) {
         return 0;
      } else {
         return sumOfMarks / amountOfGroupsInDiscipline;
      }
   }

   public String getFacultyName() {
      return facultyName;
   }

   public int getGroupAmount() {
      return groupAmount;
   }

   public ArrayList<Group> getGroupList() {
      return groupList;
   }

   public Group[] addNewGroups(String[] groupNames) throws EmptyNameException {
      Group[] newGroups = new Group[groupNames.length];
      for (int i = 0; i < groupNames.length; i++) {
         newGroups[i] = addNewGroup(groupNames[i]);
      }
      return newGroups;
   }

   public Group addNewGroup(String groupName) throws EmptyNameException {
      if (!groupName.equals("")) {
         Group newGroup = new Group(this.universityName, this.facultyName, groupName);
         this.groupList.add(newGroup);
         this.groupAmount++;
         return newGroup;
      } else {
         throw new EmptyNameException("Group name cannot be empty");
      }
   }
}
