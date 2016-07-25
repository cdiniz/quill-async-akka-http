package persistence.dal

import java.util.UUID

import persistence.entities.Supplier
import org.junit.runner.RunWith
import org.scalatest.{BeforeAndAfterAll, FunSuite}
import org.scalatest.junit.JUnitRunner

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.util.Timeout

@RunWith(classOf[JUnitRunner])
class SuppliersDALTest extends FunSuite with AbstractPersistenceTest with BeforeAndAfterAll{
  implicit val timeout = Timeout(5.seconds)
  lazy val generatedId = UUID.randomUUID().toString
  val modules = new Modules {
  }

  test("SuppliersActor: Testing Suppliers DAL") {
    val numberOfEntities : Long = Await.result((modules.suppliersDal.insert(Supplier(generatedId,"sup","desc"))),5.seconds)
    assert (numberOfEntities == 1)
    val supplier : Option[Supplier] = Await.result((modules.suppliersDal.findById(generatedId)),5.seconds)
    assert (! supplier.isEmpty &&  supplier.get.name.compareTo("sup") == 0)
    val empty : Option[Supplier] = Await.result((modules.suppliersDal.findById("not an uuid")),5.seconds)
    assert (empty.isEmpty)
  }

  override def  afterAll() {
    Await.result((modules.suppliersDal.delete(generatedId)),5.seconds)
  }

}