package persistence.dal

import persistence.entities.Supplier
import io.getquill._
import scala.concurrent.{ExecutionContext, Future}

trait SuppliersDal {
  def insert(supplierToInsert: Supplier)(implicit ec: ExecutionContext): Future[Long]
  def findById(supId: Long)(implicit ec: ExecutionContext) : Future[Option[Supplier]]

}

class SuppliersDalImpl(context: JdbcContext[H2Dialect, Literal]) extends SuppliersDal {

  import context._

  override def insert(supplierToInsert: Supplier)(implicit ec: ExecutionContext): Future[Long] = {
    Future { context.run(query[Supplier].insert)(supplierToInsert :: Nil).head }
  }

  override def findById(supId: Long)(implicit ec: ExecutionContext) : Future[Option[Supplier]] = {
    val q = quote {
      query[Supplier].filter(s => s.id == lift(supId))
    }
    Future { context.run(q).headOption }
  }
}
