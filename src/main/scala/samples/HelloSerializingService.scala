package samples

import scala.reflect.BeanInfo
import javax.ws.rs._
import se.scalablesolutions.akka.actor.Actor
import se.scalablesolutions.akka.serialization.Serializer
import collection.mutable.HashMap

@BeanInfo
case class User(id:String, name:String) {
	def this() = this(null, null)
}

@Path("/users")
class UserService extends Actor {

	private case object GetUserList
	private case class CreateNewUser(user:Array[Byte])

	private val users = new HashMap[String,User]
	
	@GET 
	@Produces(Array("application/json"))
	def get = ((this !! GetUserList) getOrElse "nothing found").asInstanceOf[Array[Byte]]

	@POST
	@Consumes(Array("application/json"))
	def post(in:Array[Byte]) = (this !! CreateNewUser(in)) getOrElse "user not created"

	def receive = {
		case GetUserList => 
			val userList = users map { u => u._2 }
			reply(Serializer.ScalaJSON.out(userList.toList))
		case CreateNewUser(in) => 
			val newUser = Serializer.ScalaJSON.in[User](in).asInstanceOf[User]
			users += (newUser.id -> newUser)
			reply("done")
		case "test" => reply("foo")
		case x => reply("not implemented")
	}
}
