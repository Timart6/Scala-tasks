object Market {
   var tokens_availible:Double = 2000
   var money_availible: Double = 10000
   var all_tokens = 2000
   var course: Double = money_availible / all_tokens

   def sellTokens(human: Human, number: Int): Unit = {
      var _number = number
      if(number * course > Market.money_availible) {
         println("Sorry, Market doesn't have enough money now. Please, try later or sell less tokens")
         return
      }
      if(human._tokens >= number) human._tokens -= number
      else {
         _number -= human._tokens
         human._tokens = 0
      }
      human.money += (_number * course).toInt
      Market.money_availible -= _number * course
      Market.tokens_availible += _number
      recalculation()
   }

   def buyTokens(human: Human, number: Int): Unit = {
      var _number = number
      if (number > Market.tokens_availible) {
         println("Sorry, Market doesn't have enough tokens now. Please, try later or buy less tokens")
         return
      }
      if (human._money >= number * course) human._money -= (number * course).toInt
      else {
         _number -= (human._money / course).toInt
         human._money = 0
      }
      human.tokens += number
      Market.tokens_availible -= _number
      Market.money_availible += (_number * course).toInt
      recalculation()
   }
   
   def recalculation(): Unit = {
      course = money_availible / all_tokens
   }
   
   def printMarket():Unit = {
      println(s"All tokens = ${all_tokens}, tokens availible = ${tokens_availible}, money = ${money_availible}, coruse = $course")
   }
}
