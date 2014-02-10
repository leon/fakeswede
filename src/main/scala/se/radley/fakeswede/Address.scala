package se.radley.fakeswede

import play.api.libs.json.Json
import scala.io.Source
import java.io.File
import scala.util.Random
import extensions._

case class Address(
  address: String,
  postalCode: String,
  city: String
)

object Address {
  implicit val format = Json.format[Address]

  lazy val streetNames = new File("src/main/resources/addresses.json").fromJsonList
  lazy val postalCodes = new File("src/main/resources/postalCodes.json").fromJsonList
  lazy val cities = new File("src/main/resources/cities.json").fromJsonList

  def streetName = Random.shuffle(streetNames).head
  def streetNumber = Random.nextInt(30)
  def street = s"$streetName $streetNumber"
  def postalCode = Random.shuffle(postalCodes).head
  def city = Random.shuffle(cities).head

  def random = Address(street, postalCode, city)
}
