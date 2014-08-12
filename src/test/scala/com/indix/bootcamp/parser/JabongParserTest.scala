package com.indix.bootcamp.parser


import com.indix.bootcamp.utils.TestUtils
import org.scalatest.{Matchers, FunSuite}


/**
 * Created by lakshmi on 11/8/14.
 */
class JabongParserTest extends FunSuite with Matchers with TestUtils {

  test("should parse product page") {
    val document = readDocument("/jabong/jabong_1.html")
    val parser = new JabongParser
    val parsedProduct = parser.parseProduct(document, "http://www.jabong.com/Wills-Lifestyle-Beige-Leather-Handbag-629406.html?pos=1")
    parsedProduct.name should be("Beige Leather Handbag")
    parsedProduct.description should include("this beige coloured handbag from the house of Wills Lifestyle")
  }

  test("should parse prices from product page") {
    val document = readDocument("/jabong/jabong_1.html")
    val parser = new JabongParser
    val parsedPrice = parser.parsePrice(document)
    parsedPrice.listPrice should be(6999.0)
    parsedPrice.salePrice should be(6999.0)
  }

}
