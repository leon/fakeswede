name := "fake-swede"

version := "1.0"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.2.1",
  "org.specs2" %% "specs2" % "2.3.7" % "test"
)