import com.mle.sbtutils.SbtProjects
import sbt.Keys._
import sbt._

/**
 * A scala build file template.
 */
object StmBuild extends Build {

  lazy val template = SbtProjects.testableProject("stm").settings(projectSettings: _*)

  lazy val projectSettings = Seq(
    version := "0.0.1",
    scalaVersion := "2.11.7",
    fork in Test := true,
    libraryDependencies ++= Seq(
      "com.github.malliina" %% "util-base" % "0.8.0",
      "org.scala-stm" %% "scala-stm" % "0.7",
      "org.specs2" %% "specs2-core" % "3.6.4" % "test"
    ),
    scalacOptions in Test ++= Seq("-Yrangepos")
  )
}
