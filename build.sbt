javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

lazy val root = (project in file(".")).
  settings(
    name := "fastly-lambda",
    version := "1.0.23",
    scalaVersion := "2.11.7",
    retrieveManaged := true,
    libraryDependencies ++= Seq( 
      "com.amazonaws" % "aws-lambda-java-core" % "1.1.0",
      "com.amazonaws" % "aws-lambda-java-events" % "1.1.0",
      "com.amazonaws" % "aws-java-sdk-s3" % "1.10.45",
      "com.amazonaws" % "aws-java-sdk-logs" % "1.10.45",
      "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.4",
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.6.3",
      "org.json4s" %% "json4s-native" % "3.3.0",
      "org.json4s" %% "json4s-jackson" % "3.3.0",
      "com.typesafe.play" %% "play-ws" % "2.4.4",
      "com.typesafe" % "config" % "1.3.0",
      "org.specs2" %% "specs2-core" % "3.7" % Test
    )
  )

fork in Test := true 

javaOptions in Test += "-Dconfig.resource=application.test.conf"

assemblyMergeStrategy in assembly  := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

