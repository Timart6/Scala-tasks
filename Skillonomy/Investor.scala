class Investor (name:String  = "No data", surname:String  = "No data", age:Int = 0, email:String = "No data",
                tokens:Int = 0, money:Double = 0, money_invested:Double = 0) extends Human(name, surname, age, email, tokens, money) {
   override val role = "Investor"
   var _money_invested = money_invested

   def investMoney(): Unit = {
      _money_invested += _money
      Market.buyTokens(this, (money / Market.course).toInt)
   }

   def getMoney(): Unit = {
      Market.sellTokens(this, _tokens)
//      _money_invested = 0
   }

   override def printHuman(): Unit = {
      super.printHuman()
      println(s"Money invested: ${_money_invested}")
   }
}

