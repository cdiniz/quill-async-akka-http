package rest

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import persistence.entities.{SimpleSupplier, Supplier}
import persistence.JsonProtocol
import JsonProtocol._
import SprayJsonSupport._

import scala.concurrent.Future
import akka.http.scaladsl.model.StatusCodes._

class RoutesSpec extends AbstractRestTest {

  def actorRefFactory = system
  val modules = new Modules {}
  val suppliers = new SupplierRoutes(modules)
  lazy val generatedId = UUID.randomUUID().toString

  "Supplier Routes" should {

    "return an empty array of suppliers" in {
     modules.suppliersDal.findById(generatedId) returns Future(None)

      Get(s"/supplier/$generatedId") ~> suppliers.routes ~> check {
        handled shouldEqual true
        status shouldEqual NotFound
      }
    }

    "return an array with 1 suppliers" in {
      modules.suppliersDal.findById(generatedId) returns Future(Some(Supplier(generatedId,"name 1", "desc 1")))
      Get(s"/supplier/$generatedId") ~> suppliers.routes ~> check {
        handled shouldEqual true
        status shouldEqual OK
        responseAs[Option[SimpleSupplier]].isEmpty shouldEqual false
      }
    }

    "create a supplier with the json in post" in {
      modules.suppliersDal.insert(any)(any) returns  Future(1)
      Post("/supplier",SimpleSupplier("name 1","desc 1")) ~> suppliers.routes ~> check {
        handled shouldEqual true
        status shouldEqual Created
      }
    }

    "not handle the invalid json" in {
      Post("/supplier","{\"name\":\"1\"}") ~> suppliers.routes ~> check {
        handled shouldEqual false
      }
    }

    "not handle an empty post" in {
      Post("/supplier") ~> suppliers.routes ~> check {
        handled shouldEqual false
      }
    }

  }

}