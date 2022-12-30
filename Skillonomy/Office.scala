class Office {
   val country = "Ukraine"
//   var list_of_teachers = new HumanList()
//   var list_of_investors = new HumanList()
//   var list_of_students = new HumanList()
   var list_of_teachers: List[Teacher] = Nil
   var list_of_investors: List[Investor] = Nil
   var list_of_students: List[Student] = Nil


   def addTeacher(teacher: Teacher): Unit = {
      list_of_teachers = teacher :: list_of_teachers
   }
   def addInvestor(investor: Investor): Unit = {
      list_of_investors = investor :: list_of_investors
   }
   def addStudent(student: Student): Unit = {
      list_of_students = student :: list_of_students
   }


   def printOffice(): Unit = {
      println("<---Office--->")
      println("Country: " + country)
      println("Teachers: ")
      for(teacher <- list_of_teachers) teacher.printHuman()
      println(" ")
      println("Investors: ")
      for(investor <- list_of_investors) investor.printHuman()
      println("--------------------------------------------")
   }

   def addTokens(): Unit = {
      Market.tokens_availible += 100
      Market.all_tokens += 100
      Market.recalculation()
   }
}
