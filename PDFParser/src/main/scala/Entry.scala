import org.apache.pdfbox.pdmodel.{PDDocument, PDDocumentNameDictionary}
import org.apache.pdfbox.text.PDFTextStripper
import org.json4s.{Formats, NoTypeHints}
import org.json4s.jackson.Serialization
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization._
import java.io.File
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex


object Entry extends App {
  implicit val formats: AnyRef with Formats = Serialization.formats(NoTypeHints)
  case class informacion (Entidad:String, Concepto:String, Importe: String)

  var filename = new File("/home/agiannoni/Downloads/comprobante50050435045210505.pdf")
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

  println(listaString)

  val keywords = List("Entidad", "Concepto", "Importe")
  var informacionPDF = Map[String, String]()

  listaString.map(l=>keywords.map(k=> if(l.startsWith(k)){
    informacionPDF += (k->l.split(k).tail.mkString(""))
  }))
  val jsona = parse(write(informacionPDF)).extract[informacion]
  println(jsona.Entidad)
  println(jsona.Concepto)
  println(jsona.Importe)
  informacionPDF.foreach(println)
}
/*
  var info = Map[String, String]()
  for (linea <- lista) {
    if (linea contains "Entidad" ) {
      val pattern = "Entidad"
      info += ("Entidad"-> linea.split(pattern).tail.mkString("") )
    } else if(linea contains "Concepto" ){
      val pattern = "Concepto"
      info += ("Concepto"->linea.split(pattern).tail.mkString(""))
    }else if (linea contains "Importe" ){
      val pattern = "Importe"
      info += ("Importe"->linea.split(pattern).tail.mkString(""))
    }
  }
  */
