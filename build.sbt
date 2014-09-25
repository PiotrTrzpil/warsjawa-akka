name := """warsjawa-akka"""

version := "1.0"

scalaVersion := "2.11.2"

resolvers += "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"


libraryDependencies ++= Seq(
  "com.typesafe.akka"  %% "akka-actor"       % "2.3.6",
  "com.typesafe.akka"  %% "akka-slf4j"       % "2.3.6",
  "com.typesafe.akka"  %% "akka-remote"      % "2.3.6",
  "io.spray"           %% "spray-can"        % "1.3.1",
  "io.spray"           %% "spray-routing"    % "1.3.1",
  "io.spray"           %% "spray-json"       % "1.2.6",
  "org.json4s"         %% "json4s-native"    % "3.2.10",
  "io.dropwizard.metrics" % "metrics-core"   % "3.1.0",
  "ch.qos.logback"        % "logback-classic"% "1.1.2",
   "org.reactivemongo" %% "reactivemongo" % "0.10.5.akka23-SNAPSHOT",
  "io.spray"           %% "spray-testkit"    % "1.3.1"        % "test",
  "com.typesafe.akka"  %% "akka-testkit"     % "2.3.6"        % "test",
  "com.novocode"        % "junit-interface"  % "0.7"          % "test->default"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Ywarn-dead-code",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
