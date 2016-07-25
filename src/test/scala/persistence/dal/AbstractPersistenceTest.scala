package persistence.dal


import io.getquill._
import utils._
import org.scalatest.{FunSuite, Suite}

trait AbstractPersistenceTest extends FunSuite {  this: Suite =>

  trait Modules extends ConfigurationModuleImpl  with PersistenceModuleTest {
  }


  trait PersistenceModuleTest extends PersistenceModule with DbContext{
    this: Configuration  =>
    override lazy val context = new PostgresAsyncContext[SnakeCase]("quilltest")
    override val suppliersDal: SuppliersDal = new SuppliersDalImpl(context)
    val self = this

  }

}