package samples

import se.scalablesolutions.akka.actor.Actor

import javax.ws.rs.{GET, Path, Produces}

@Path("/hello")
class HelloService extends Actor {

	private case object Hello

	@GET
	@Produces(Array("text/html"))
	def hello = (this !! Hello).getOrElse("couldn't say hello")

	def receive = {
		case Hello => reply(<h1>Hello, World</h1>.toString)
	}
}
