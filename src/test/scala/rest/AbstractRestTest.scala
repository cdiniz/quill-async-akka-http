package rest

import org.scalatest.{Matchers, WordSpec}
import org.specs2.mock.Mockito
import persistence.dal.SuppliersDal
import utils.{ActorModule, ConfigurationModuleImpl, DbContext, PersistenceModule}
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import akka.http.scaladsl.testkit.ScalatestRouteTest
import io.getquill.{H2Dialect, JdbcContext, Literal}

trait AbstractRestTest  extends WordSpec with Matchers with ScalatestRouteTest with Mockito{

  trait Modules extends ConfigurationModuleImpl with ActorModule with PersistenceModule with DbContext {
    val system = AbstractRestTest.this.system

    override val context: JdbcContext[H2Dialect, Literal] = ???
    override val suppliersDal: SuppliersDal = ???

  }

  def getConfig: Config = ConfigFactory.empty();


}
