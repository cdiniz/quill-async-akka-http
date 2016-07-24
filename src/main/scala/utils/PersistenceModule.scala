package utils

import io.getquill.{H2Dialect, JdbcContext, Literal}
import persistence.dal.{SuppliersDal, SuppliersDalImpl}

trait DbContext {
	val context : JdbcContext[H2Dialect, Literal]
}

trait PersistenceModule {
  val suppliersDal : SuppliersDal
}


trait PersistenceModuleImpl extends PersistenceModule with DbContext{
	this: Configuration  =>

	override val context = new JdbcContext[H2Dialect, Literal]("h2db")

	override val suppliersDal = new SuppliersDalImpl(context)

}
