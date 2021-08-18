
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper

import java.io.File
import scala.collection.mutable.ListBuffer

object Factura extends App{


  var filename = new File("/home/agiannoni/Downloads/EBFF630482946BF180D60050569C42C3.pdf")
  val pdf = PDDocument.load(filename)
  val stripper = new PDFTextStripper
  stripper.setStartPage(0)
  stripper.setEndPage(pdf.getNumberOfPages)
  val listaString = new ListBuffer[String]()
  var stringlist = new StringBuilder()
  val texto = stripper.getText(pdf).map(c => if (c.toString != "\n") {
    stringlist += c
  } else {
    listaString += stringlist.toString
    stringlist.clear()
  })
  listaString.foreach(l=>
    if (l.trim.startsWith("DESCRIPCION")){
      val cabecera = l
      println(cabecera)
    }
    else if(l.trim.startsWith("SEM")){
    println(l.substring(0,22))
    println(l.substring(21,33))
  })




}
