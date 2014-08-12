package com.indix.bootcamp.parser

import com.indix.bootcamp.models.{Product, Price, Result}
import org.jsoup.nodes.Document

class FlipkartParser extends Parser {
  override def parseProduct(document: Document, pageUrl: String): Product = {
    val title = document.select("h1[itemprop=name]").text()
    val description = document.select("#specifications").text()
    Product(title, description, pageUrl)
  }

  // TODO: Fix the price Extraction
  override def parsePrice(document: Document): Price = {
    val listPrice = document.select(".old-price").text().toDouble
    val salePrice = document.select("itemprop[name=price]").attr("content").toDouble
    Price(listPrice, salePrice)
  }
}

class JabongParser extends Parser {
  override def parseProduct(document: Document, pageUrl: String): Product = {
    val title = document.select("span[itemprop=name]").text()
    val description = document.select("div[id=productInfo").select("p").text()
      //select("p[itemprop=description[p").text()
    Product(title, description, pageUrl)
  }

  override def parsePrice(document: Document): Price = {
    val listPrice = document.select("div[id=product_price]").text().toDouble
    val salePrice = if(! document.select("div[id=product_special_price]").isEmpty) listPrice else document.select("div[id=product_special_price]").text().toDouble
    Price(listPrice, salePrice)
  }
}
