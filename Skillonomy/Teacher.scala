class Teacher (name:String  = "No data", surname:String  = "No data", age:Int = 0, email:String = "No data",
               tokens:Int = 0, money:Double = 0, list_of_students: List[Student] = Nil)
               extends Human(name, surname, age, email, tokens, money) {
   override val role = "Teacher"
   val rand: scala.util.Random = scala.util.Random
   private val teaching_skills = rand.nextInt(5)
   var _list_of_students = list_of_students

   def addStudent(student: Student): Unit = {
      _list_of_students = student :: _list_of_students
   }
   def deleteStudent(student: Student): Unit = {
      val newlist = _list_of_students.filter(_ == student)
      _list_of_students = newlist
      student.is_studying = false
   }
   def printStudents(): Unit = {
      println(s"${name} ${surname} students: ")
      for(student <- _list_of_students) student.printHuman()
   }

   override def printHuman(): Unit = {
      super.printHuman()
      printStudents()
   }

   def giveTask(month: Int = 1, student: Student): Unit = {
      val taskComplexity = month * 10 + rand.nextInt(5)
      val grade = student.doTask(taskComplexity)
      if(grade > 2) student._tokens += grade * 5
      _money += grade * 30 + 50
   }

   def teaching(month: Int = 1): Unit = {
      for(student <- _list_of_students) {
         if(student.is_studying) {
            student.skills += student.desire_to_learn + teaching_skills / 2 + 7
            giveTask(month = month, student = student)
            if(rand.nextInt(4) != 1) Market.sellTokens(student, student._tokens)
         }
      }
   }
}
