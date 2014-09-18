import com.typesafe.sbt.SbtScalariform._
import scalariform.formatter.preferences._
import sbtrelease.ReleasePlugin._
import sbtrelease.ReleasePlugin.ReleaseKeys._
import sbtrelease._
import ReleaseStateTransformations._

name := "CSV4S"

version := "1.0.1"

scalaVersion := "2.11.2"

resolvers ++= Seq(
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
  "org.slf4j" % "slf4j-simple" % "1.7.7",
  "com.chuusai" %% "shapeless" % "2.0.0",
  "com.typesafe.play" %% "play-iteratees" % "2.3.0",
  "org.scalatest" %% "scalatest" % "2.2.2" % "test"
)

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:reflectiveCalls",
  "-Ywarn-dead-code",
  "-Ywarn-inaccessible",
  "-Ywarn-nullary-unit",
  "-Ywarn-nullary-override",
  "-Ywarn-value-discard"
)

scalariformSettings

ScalariformKeys.preferences := ScalariformKeys.preferences.value
  .setPreference(DoubleIndentClassDeclaration, true)
  .setPreference(AlignSingleLineCaseStatements, true)
  .setPreference(AlignParameters, true)
  .setPreference(CompactControlReadability, true)
  .setPreference(IndentLocalDefs, true)
  .setPreference(PreserveDanglingCloseParenthesis, true)
  .setPreference(RewriteArrowSymbols, true)
  .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 100)

net.virtualvoid.sbt.graph.Plugin.graphSettings

releaseSettings

useGlobalVersion := false

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  setNextVersion,
  commitNextVersion,
  pushChanges
)

incOptions := incOptions.value.withNameHashing(nameHashing = true)