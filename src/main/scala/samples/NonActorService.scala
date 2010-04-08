package samples

import javax.ws.rs._
import javax.ws.rs.core.MediaType._
import se.scalablesolutions.akka.serialization.Serializer
import collection.mutable.HashMap

@Path("/nonactor")
class NonActorService {

	val message = Map("message"->"hello, world")

	@GET 
	@Produces(Array(TEXT_HTML))
	def asHTML = <h1>{ message("message") }</h1>

	@GET
	@Produces(Array(APPLICATION_JSON))
	def asJSON = Serializer.ScalaJSON.out(message)

	@GET
	@Produces(Array(APPLICATION_XML))
	def asXML = <message>{ message("message") }</message>
}
