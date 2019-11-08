resolvers += "CodeLibs Repository" at "http://maven.codelibs.org/"

libraryDependencies ++= Seq(
  "org.codelibs" % "lucene-analyzers-kuromoji-ipadic-neologd" % "8.2.0-20191031",
  "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)
