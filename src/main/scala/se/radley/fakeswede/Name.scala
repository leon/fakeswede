package se.radley.fakeswede

import play.api.libs.json.Json
import java.io.File
import scala.util.Random
import extensions._

case class Name(
  firstname: String,
  lastname: String
)

object Name {
  implicit val nameFormat = Json.format[Name]

  case class NameData(
    male: List[String],
    female: List[String],
    lastname: List[String]
  )
  val namesFormat = Json.format[NameData]

  lazy val data: NameData = new File("src/main/resources/names.json").fromJson(namesFormat)

  def maleFirstname = Random.shuffle(data.male).head
  def femaleFirstname = Random.shuffle(data.female).head
  def firstname = Random.shuffle(List(maleFirstname, femaleFirstname)).head
  def lastname = Random.shuffle(data.lastname).head

  def random = Name(firstname, lastname)
}
