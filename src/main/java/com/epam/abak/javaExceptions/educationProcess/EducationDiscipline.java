package com.epam.abak.javaExceptions.educationProcess;

public class EducationDiscipline {
   private String name;
   private static int amountOfDisciplines = 0;
   private int id;

   public EducationDiscipline(String name) {
      this.name = name;
      this.id = amountOfDisciplines;
      amountOfDisciplines++;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }
}
