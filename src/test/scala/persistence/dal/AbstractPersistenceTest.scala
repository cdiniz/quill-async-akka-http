package persistence.dal


import io.getquill.{H2Dialect, JdbcContext, Literal}
import utils._
import org.scalatest.{FunSuite, Suite}

trait AbstractPersistenceTest extends FunSuite {  this: Suite =>


  trait Modules extends ConfigurationModuleImpl  with PersistenceModuleTest {
  }


  trait PersistenceModuleTest extends PersistenceModule with DbContext{
    this: Configuration  =>
    override val context: JdbcContext[H2Dialect, Literal] = ???
    override val suppliersDal: SuppliersDal = ???
    val self = this

  }

}