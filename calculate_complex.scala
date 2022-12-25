import scala.io.StdIn.{readLine,readInt}


class ComplexNumber(var realNum: Double, var imagNum: Double) {

  def printNumber() : Unit =
    println(realNum + " + " + imagNum + "i")


  def + (other: ComplexNumber) = new ComplexNumber (
    ((realNum + other.realNum) * 100).round / 100.toDouble,
    ((imagNum + other.imagNum) * 100).round / 100.toDouble
  )

  def - (other: ComplexNumber) = new ComplexNumber (
    ((realNum - other.realNum) * 100).round / 100.toDouble,
    ((imagNum - other.imagNum) * 100).round / 100.toDouble
  )

  def * (other: ComplexNumber) = new ComplexNumber (
    ((realNum * other.realNum - imagNum * other.imagNum) * 1000).round / 1000.toDouble,
    ((imagNum * other.realNum + other.imagNum * realNum) * 1000).round / 1000.toDouble
  )

  def / (other: ComplexNumber) = new ComplexNumber (
    (((realNum * other.realNum + imagNum * other.imagNum) / (other.realNum * other.realNum + other.imagNum * other.imagNum)) * 1000).round / 1000.toDouble,
    (((imagNum * other.realNum - realNum * other.imagNum) / (other.realNum * other.realNum + other.imagNum * other.imagNum)) * 1000).round / 1000.toDouble
  )
}


def createNumber(): ComplexNumber = {
  println("Enter a real number")
  val _realNum = readLine().toDouble
  println("Enter a imaginary number")
  val _imagNum = readLine().toDouble
  new ComplexNumber(_realNum, _imagNum)
}

def calculate() : Unit = {
  println("First complex number: ")
  val num1 = createNumber()
  println()
  println("Second complex number: ")
  val num2 = createNumber()
  var num3 = new ComplexNumber(0, 0)
  println("Choose an operation (+,-,*,/): ")
  val operation = readLine()
  operation match {
    case "+" => num3 = num1 + num2
    case "-" => num3 = num1 - num2
    case "*" => num3 = num1 * num2
    case "/" => num3 = num1 / num2
    case _ => println("Please, choose from available operations")
  }
  println("The result is: ")
  num3.printNumber()
}


@main def CalculateComplex : Unit =
  calculate()