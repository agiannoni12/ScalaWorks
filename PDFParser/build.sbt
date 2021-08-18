name := "PDFParser"

version := "0.1"

scalaVersion := "2.12.0"


// https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox
libraryDependencies ++= Seq("org.apache.pdfbox" % "pdfbox" % "2.0.12"
                            ,"org.json4s" %% "json4s-jackson" % "3.5.0")

