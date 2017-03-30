
name := "selenium-session"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "org.seleniumhq.selenium" % "selenium-server" % "3.3.1",
  "org.seleniumhq.selenium" % "selenium-firefox-driver" % "2.52.0",
  "org.seleniumhq.selenium" % "selenium-support" % "3.3.1",
  "org.scalatest" % "scalatest_2.11" % "3.0.1"
)
