import Dependencies._

ThisBuild / scalaVersion     := "2.12.14"  // Change Scala version to 2.12.14
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "simple",
    libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-sql" % "3.1.2",
      "org.scalatest" %% "scalatest" % "3.2.9" % Test,
      munit % Test
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.

