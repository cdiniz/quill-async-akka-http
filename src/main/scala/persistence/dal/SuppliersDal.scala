package persistence.dal

import persistence.entities.Supplier

import scala.concurrent.Future

trait SuppliersDal {
  def insert(supplierToInsert: Supplier): Future[Long]
  def findById(supId: Int) : Future[Option[Supplier]]

}

class SuppliersDalImpl extends SuppliersDal {

  override def insert(supplierToInsert: Supplier): Future[Long] = ???
  override def findById(supId: Int) : Future[Option[Supplier]] = ???
}
