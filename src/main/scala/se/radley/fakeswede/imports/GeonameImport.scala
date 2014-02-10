package se.radley.fakeswede.imports

import scala.io.Source
import play.api.libs.json.Json
import java.nio.file._
import se.radley.fakeswede.extensions._

case class GeonameRow(
  countryCode: String,
  postalCode: String,
  city: String,
  county: String,
  lat: Double,
  lng: Double
)

object GeonameRow {

  implicit val jsonFormat = Json.format[GeonameRow]

  def fromLine(line: String): GeonameRow = {
    val columns = line.split("\t")
    GeonameRow(
      countryCode = columns(0),
      postalCode = columns(1),
      city = columns(2),
      county = columns(3),
      lat = columns(9).toDouble,
      lng = columns(10).toDouble
    )
  }
}

class GeonameImport {

  val tsv = Source.fromFile("src/main/resources/SE.txt")
  val rows = tsv.getLines().map(GeonameRow.fromLine).toList.sortBy(_.city)

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

  def generateCityNames() {
    save("cities.json") {
      val cities = rows.groupBy(_.city).keys.toList.sortBy(x => x)
      Json.toJson(cities).toString()
    }
  }

  def generatePostalCodes() {
    save("postalCodes.json") {
      val postalCodes = rows.groupBy(_.postalCode).keys.toList.sortBy(x => x)
      Json.toJson(postalCodes).toString()
    }
  }

  def generateAll() {
    generateData()
    generateCityNames()
    generatePostalCodes()
  }
}
