package se.radley.fakeswede.imports

import scala.io.Source
import play.api.libs.json.Json
import java.nio.file._
import se.radley.fakeswede.extensions._

case class AreaCodeRow(
  city: String,
  areaCode: String
)

object AreaCodeRow {

  implicit val jsonFormat = Json.format[AreaCodeRow]

  def fromLine(line: String): AreaCodeRow = {
    val columns = line.split("\t")
    AreaCodeRow(
      city = columns(0),
      areaCode = columns(1)
    )
  }
}

class AreaCodesImport {

  val tsv = Source.fromFile("src/main/resources/AreaCodes.txt")
  val rows = tsv.getLines().map(AreaCodeRow.fromLine).toList

  def save(file: String)(jsonString: String) {
    val path = Paths.get("src", "main", "resources", file)
    Files.deleteIfExists(path)
    val dataFile = Files.createFile(path).toFile
    dataFile.write(jsonString)
  }

  def generateData() {
    save("data.json") {
      Json.toJson(rows).toString()
    }
  }

  def generateAreaCodes() {
    save("areaCodes.json") {
      val areaCodes = rows.map(_.areaCode).sortBy(x => x)
      Json.toJson(areaCodes).toString()
    }
  }

  def generateAll() {
    generateData()
    generateAreaCodes()
  }
}
