import sbt._

class Project(info: ProjectInfo) extends DefaultWebProject(info) {

	override def jettyWebappPath  = webappPath
	override def scanDirectories  = Nil

	override def repositories = Set(
		ScalaToolsSnapshots,
		"jBoss" at "http://repository.jboss.org/maven2",
		"Multiverse Releases" at "http://multiverse.googlecode.com/svn/maven-repository/releases/",
		"GuiceyFruit" at "http://guiceyfruit.googlecode.com/svn/repo/releases/",
		"DataBinder" at "http://databinder.net/repo",
		"Configgy" at "http://www.lag.net/repo",
		"Akka Maven Repository" at "http://scalablesolutions.se/akka/repository")

	override def libraryDependencies = Set(
 
		/* templating engine */
		"org.freemarker" % "freemarker" % "2.3.16" % "compile",

		/* servlet implementation */
		"org.eclipse.jetty"  % "jetty-server"   % "7.0.1.v20091125" % "test",
		"org.eclipse.jetty"  % "jetty-webapp"   % "7.0.1.v20091125" % "test",

		/* akka dependencies */
		"se.scalablesolutions.akka" % "akka-core_2.7.7"    % "0.7" % "compile",
		"se.scalablesolutions.akka" % "akka-rest_2.7.7"    % "0.7" % "compile")
}
