import scala.io.StdIn.readLine

class OfficeSimulation(months_to_simulate: Int) {
    
    private val office = new Office()
    private val teacher1 = new Teacher("Bob", "Odinkerk", 50, "bobody@gmail.com", 500, 4000)
    private val teacher2 = new Teacher("Peter", "Griffin", 34, "hehe@gmail.com", 0, 2000)
    private val investor1 = new Investor("Elon", "Tusk", 41, "iamveryrich@gmail.com", 0, 10000)
    private val investor2 = new Investor("Ivan", "Palpenko", 70, "unlimitedpower@gmail.com", 1000, 2000)
    private val student1_1 = new Student("John", "Shephard", 30, "savethegalaxy@gmail.com", 0, 1000)
    private val student1_2 = new Student("Roman", "Viktorovich", 16, "vikoko@gmail.com", 0, 0)
    private val student1_3 = new Student("Irina", "Kovach", 18, "g56542@hmail.com", 0, 0)
    private val student2_1 = new Student("Lisa", "Polik", 20, "lizew@gmail.com", 100, 500)
    private val student2_2 = new Student("Olexandr", "Troyanov", 24, "troyan@gmail.com", 0, 250)
    private val student2_3 = new Student("Margarita", "Jeshko", 21, "jejeje23@mail.com", 0, 1000)

    office.addTeacher(teacher1)
    office.addTeacher(teacher2)
    office.addInvestor(investor1)
    office.addInvestor(investor2)
    for(student <- teacher1._list_of_students) {
        office.addStudent(student)
    }
    for (student <- teacher2._list_of_students) {
        office.addStudent(student)
    }

    teacher1.addStudent(student1_1)
    teacher1.addStudent(student1_2)
    teacher1.addStudent(student1_3)
    teacher2.addStudent(student2_1)
    teacher2.addStudent(student2_2)
    teacher2.addStudent(student2_3)

    def printSimulation(): Unit = {
        office.printOffice()
        Market.printMarket()
    }

    def createAccount(): Human = {
        println("Choose role to register. 1 - Teacher, 2 - Investor, 3 - Student")
        val role_number = readLine().toInt
        role_number match
            case 1 => {
                val account = new Teacher(
                readLine("Name: "),
                readLine("Surname: "),
                readLine("Age: ").toInt,
                readLine("Email: "),
                readLine("Tokens: ").toInt,
                readLine("Money: ").toDouble
              )
             account
            }
            case 2 => {
                val account = new Investor(
                    readLine("Name: "),
                    readLine("Surname: "),
                    readLine("Age: ").toInt,
                    readLine("Email: "),
                    readLine("Tokens: ").toInt,
                    readLine("Money: ").toDouble,
                    readLine("Money that already invested: ").toDouble
                )
                 account
            }
            case 3 => {
                val account = new Student(
                    readLine("Name: "),
                    readLine("Surname: "),
                    readLine("Age: ").toInt,
                    readLine("Email: "),
                    readLine("Tokens: ").toInt,
                    readLine("Money: ").toDouble
                )
                return account
            }

            case _ => {
                println("Wrong command, try again")
                createAccount()
            }
    }

    def investorsActions(): Unit = {
        val rand: scala.util.Random = scala.util.Random
        if(rand.nextInt(4) == 1) {
            if(investor1._money == 0) investor1.getMoney()
            else investor1.investMoney()
        }
        else if(rand.nextInt(4) == 2) {
            if (investor2._money == 0) investor2.getMoney()
            else investor2.investMoney()
        }
    }

    def studentsAction(): Unit = {
        for(student <- teacher1._list_of_students) {
            if(student.skills > 300 && student.averageGrade() > 5) {
                student.is_studying = false
                student.work()
            }
        }
        for (student <- teacher2._list_of_students) {
            if (student.skills > 300 && student.averageGrade() > 5) {
                teacher2.deleteStudent(student)
                student.work()
            }
        }
    }

    def start(): Unit = {
        investor1.investMoney()
        investor2.investMoney()

        printSimulation()

        for (i <- 1 to months_to_simulate) {
            teacher1.teaching(i)
            teacher2.teaching(i)
            if (i > 5) {
                investorsActions()
                studentsAction()
            }
            if (Market.money_availible == 0) office.addTokens()
        }

        printSimulation()
    }
}
