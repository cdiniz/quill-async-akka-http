package persistence

import persistence.entities.SimpleSupplier
import spray.json.DefaultJsonProtocol

object JsonProtocol extends DefaultJsonProtocol {
  implicit val supplierFormat = jsonFormat2(SimpleSupplier)
}