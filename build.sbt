organization := "org.example"
name := "DuplicatedNoneBug"
version := "0.1.0"

/* Defines default project */
lazy val root = project in file(".")

scalaVersion := "2.12.20"

libraryDependencies += "org.flinkextended" %% "flink-scala-api" % "1.19.1_1.1.7"
libraryDependencies += "org.apache.flink" % "flink-streaming-java" % "1.19.1" classifier "tests"
libraryDependencies += "org.apache.flink" % "flink-test-utils" % "1.19.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.5.6"

/* Testing configuration  */
Test / fork := true // needed to apply custom options and configuration, and to close the test DB after the tests are done
Test / testOptions += Tests.Argument("-oD") // show the time taken by each test
Test / testForkedParallel := true // run tests in parallel on the forked JVM
