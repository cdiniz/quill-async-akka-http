package persistence.entities

import java.util.UUID

case class Supplier(id : String, name: String, description: String) {
  def toSimpleSupplier = SimpleSupplier(this.name,this.description)
}

case class SimpleSupplier(name: String, desc: String){
  def toSupplier = Supplier(UUID.randomUUID().toString,this.name,this.desc)
}