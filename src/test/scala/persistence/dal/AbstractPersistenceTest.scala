package persistence.dal


import io.getquill.{H2Dialect, JdbcContext, Literal}
import utils._
import org.scalatest.{FunSuite, Suite}

trait AbstractPersistenceTest extends FunSuite {  this: Suite =>


  trait Modules extends ConfigurationModuleImpl  with PersistenceModuleTest {
  }


  trait PersistenceModuleTest extends PersistenceModule with DbContext{
    this: Configuration  =>
    override val context: JdbcContext[H2Dialect, Literal] = new JdbcContext[H2Dialect, Literal]("h2db")
    override val suppliersDal: SuppliersDal = new SuppliersDalImpl(context)
    val self = this

  }

}