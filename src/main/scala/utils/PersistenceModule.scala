package utils

import io.getquill.{PostgresAsyncContext, SnakeCase}
import persistence.dal.{SuppliersDal, SuppliersDalImpl}

trait DbContext {
	val context : PostgresAsyncContext[SnakeCase]
}

trait PersistenceModule {
  val suppliersDal : SuppliersDal
}


trait PersistenceModuleImpl extends PersistenceModule with DbContext{
	this: Configuration  =>

	override lazy val context = new PostgresAsyncContext[SnakeCase]("quill")
	override val suppliersDal = new SuppliersDalImpl(context)

}
