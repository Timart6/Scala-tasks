class Student (name:String  = "No data", surname:String  = "No data", age:Int = 0, email:String = "No data",
              tokens:Int = 0, money:Double = 0) extends Human(name, surname, age, email, tokens, money) {
    override val role = "Student"
    val rand: scala.util.Random = scala.util.Random
    var desire_to_learn = rand.nextInt(5)
    var skills: Double = 10
    var list_of_grades: List[Int] = Nil
    var is_studying = true

    override def printHuman(): Unit = {
        super.printHuman()
        if(is_studying) println("Studying now")
        else println("Already work")
        println("Average grade = " + averageGrade())
    }

    def averageGrade(): Double = {
        var average: Double = 0
        for (grade <- list_of_grades) average += grade
        average / list_of_grades.length
    }

    def doTask(taskComplexity: Int = 1): Int = {
        var grade = (skills - taskComplexity + 1).toInt
        if(grade < 0) grade = 0
        if(grade > 10) grade = 10
        list_of_grades = grade :: list_of_grades
        grade
    }

    def work(): Unit = {
        if(skills >= 300 && is_studying == false) {
            _money += skills * 3 - skills / 1.5
            Market.money_availible += skills / 2
        }
    }
}
