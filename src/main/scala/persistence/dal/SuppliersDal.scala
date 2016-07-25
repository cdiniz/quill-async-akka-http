package persistence.dal

import persistence.entities.Supplier
import io.getquill._

import scala.concurrent.{Awaitable, ExecutionContext, Future}

trait SuppliersDal {
  def insert(supplierToInsert: Supplier)(implicit ec: ExecutionContext): Future[Long]
  def findById(supId: String)(implicit ec: ExecutionContext) : Future[Option[Supplier]]
  def delete(supId: String)(implicit ec: ExecutionContext): Future[Long]
}

class SuppliersDalImpl(context: PostgresAsyncContext[SnakeCase]) extends SuppliersDal {

  import context._

  override def insert(supplierToInsert: Supplier)(implicit ec: ExecutionContext): Future[Long] = {
    context.run(query[Supplier].insert)(supplierToInsert :: Nil).map(_.head)
  }

  override def findById(supId: String)(implicit ec: ExecutionContext) : Future[Option[Supplier]] = {
    val q = quote {
      query[Supplier].filter(s => s.id == lift(supId))
    }
    context.run(q).map(_.headOption)
  }

  override def delete(supId: String)(implicit ec: ExecutionContext): Future[Long] = {
    val q = quote {
      query[Supplier].filter(s => s.id == lift(supId)).delete
    }
    context.run(q)
  }
}
