package com.indix.bootcamp.models

case class Product(name: String, description: String, url: String) {
  def toCsv = List(name, description, url).mkString(",")

  def isValid = {
    isValidStringIdentifier(name) &&
      isValidStringIdentifier(description) &&
      isValidStringIdentifier(url)
  }

  private def isValidStringIdentifier(string: String): Boolean = {
    Option(string).map(_.trim).exists(s => s != null && !s.isEmpty)
  }
}

case class Price(listPrice: Double, salePrice: Double) {
  def toCsv = List(listPrice, salePrice).mkString(",")

  def isValid = salePrice != 0.0
}

case class Result(product: Option[Product], price: Option[Price]) {
  def isValidProductPage = product.exists(_.isValid) && price.exists(_.isValid)

  def toCsv = s"${product.map(_.toCsv).getOrElse(",,")},${price.map(_.toCsv).getOrElse(",")}"
}
