package se.radley.fakeswede

import java.io._
import scala.io.Source
import play.api.libs.json._
import scala.reflect.ClassTag

package object extensions {

  implicit class FileExtensions(val f: File) extends AnyVal {
    def write(s: String) {
      var writer: PrintWriter = null
      try {
        writer = new PrintWriter(f)
        writer.write(s)
      } finally {
        writer.close()
      }
    }

    def fromJsonList = {
      val json = Source.fromFile(f).mkString
      Json.parse(json).as[List[String]]
    }

    def fromJson[T](implicit reads: Reads[T]): T = {
      val json = Source.fromFile(f).mkString
      Json.parse(json).validate.asOpt.getOrElse(sys.error(s"Faulty json file: $f"))
    }
  }
}
