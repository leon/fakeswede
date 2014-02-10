package se.radley.fakeswede

import play.api.libs.json.Json

case class Identity(
  name: Name,
  address: Address
)

object Identity {

  implicit val format = Json.format[Identity]

  def random = Identity(Name.random, Address.random)

}
