import org.specs2.mutable._
import se.radley.fakeswede.imports.GeonameImport

class GeonameImportSpec extends Specification {
  "GeonameImport" should {
    "contain addresses" in {
      val rows = new GeonameImport().rows
      rows must have size(16079)
    }
  }
}