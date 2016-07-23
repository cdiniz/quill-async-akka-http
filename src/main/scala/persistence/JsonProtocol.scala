package persistence

import persistence.entities.Supplier
import spray.json.DefaultJsonProtocol

object JsonProtocol extends DefaultJsonProtocol {
  implicit val supplierFormat = jsonFormat2(Supplier)
}