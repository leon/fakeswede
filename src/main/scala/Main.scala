import play.api.libs.json.Json
import se.radley.fakeswede._
import se.radley.fakeswede.imports._

object Main extends App {
  override def main(args: Array[String]): Unit = {
    new GeonameImport().generateAll()
    new AreaCodesImport().generateAll()

    println("Addressses")
    println("")
    for (i <- 0 until 5) {
      println(Json.prettyPrint(Json.toJson(Address.random)))
    }

    println("Names")
    println("")
    for (i <- 0 until 5) {
      println(Json.prettyPrint(Json.toJson(Name.random)))
    }

    println("Identity")
    println("")
    for (i <- 0 until 5) {
      println(Json.prettyPrint(Json.toJson(Identity.random)))
    }
  }
}
