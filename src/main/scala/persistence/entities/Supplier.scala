package persistence.entities

case class Supplier(id : Option[Long], name: String, desc: String) {
  def toSimpleSupplier = SimpleSupplier(this.name,this.desc)
}

case class SimpleSupplier(name: String, desc: String){
  def toSupplier = Supplier(None,this.name,this.desc)
}