package com.epam.abak.javaExceptions.educationProcess;

import com.epam.abak.javaExceptions.educationProcess.EducationDiscipline;

import java.util.Arrays;
import java.util.List;

public class EducationDisciplines {

   private static List<EducationDiscipline> disciplines = Arrays.asList(
         new EducationDiscipline("Chemistry"),
         new EducationDiscipline("Math"),
         new EducationDiscipline("Physics"),
         new EducationDiscipline("History")
   );

   public static int getCount() {
      return disciplines.size();
   }

   public static EducationDiscipline getDisciplineByName(String name) throws Exception {
      for (int i = 0; i < disciplines.size(); i++) {
         if (disciplines.get(i).getName() == name) {
            return disciplines.get(i);
         }
      }
      throw new Exception("No discipline with such name");
   }

   public static EducationDiscipline getDisciplineById(int id) throws Exception {
      for (int i = 0; i < disciplines.size(); i++) {
         if (disciplines.get(i).getId() == id) {
            return disciplines.get(i);
         }
      }
      throw new Exception("No discipline with such ID");
   }

   public static void addDiscipline(EducationDiscipline newEducationDiscipline) {
      disciplines.add(newEducationDiscipline);
   }
}
