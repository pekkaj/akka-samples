package samples

import se.scalablesolutions.akka.actor.Actor

import javax.ws.rs.{GET, Path, Produces}

@Path("/")
class IndexService extends Actor {

	private case object Welcome

	@GET
	@Produces(Array("text/html"))
	def hello = (this !! Welcome).getOrElse("couldn't say hello")

	val html = <html>
		<head></head>
		<body>
			<h1>Welcome to my Akka samples</h1>
		</body>
	</html>

	def receive = {
		case Welcome => reply(html)
		case _ => reply("unkown command")
	}
}
