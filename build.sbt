name := "reikuaa-proxy"

version := "1.0-SNAPSHOT"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sedis repository" at "http://pk11-scratch.googlecode.com/svn/trunk/",
  "jBCrypt Repository" at "http://repo1.maven.org/maven2/org/"
)

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "com.google.inject" % "guice" % "3.0",
  "log4j" % "log4j" % "1.2.17",
  "org.jboss.resteasy" % "resteasy-jaxrs" % "2.3.7.Final",
  "com.wordnik" % "swagger-core_2.10.0" % "1.2.5",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "org.mindrot" % "jbcrypt" % "0.3m",
  "ws.securesocial" %% "securesocial" % "2.1.4",
  "com.typesafe" %% "play-plugins-redis" % "2.1.1",
  "net.sf.opencsv" % "opencsv" % "2.1"
)

play.Project.playJavaSettings
