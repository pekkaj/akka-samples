package samples

import scala.reflect.BeanInfo
import javax.ws.rs._
import javax.ws.rs.core.MediaType._
import se.scalablesolutions.akka.actor.Actor
import se.scalablesolutions.akka.serialization.Serializer
import collection.mutable.HashMap

@BeanInfo
case class User(id:String, name:String) {
	def this() = this(null, null)
}

trait UserService extends Actor {

	private val users = new HashMap[String,User]

	trait Command

	case object GetUserList extends Command
	case class CreateNewUser(user:Array[Byte]) extends Command
	
	def receive = {
		case GetUserList => 
			val userList = users map { u => u._2 }
			reply(Serializer.ScalaJSON.out(userList.toList))
		case CreateNewUser(in) => 
			val newUser = Serializer.ScalaJSON.in[User](in).asInstanceOf[User]
			users += (newUser.id -> newUser)
			reply("done")
		case x => reply("not implemented")
	}
}

@Path("/users")
class BasicJsonRestService extends UserService {

	def send(cmd:Command):Array[Byte] = {
		this.!![Array[Byte]](cmd) getOrElse "nothing found".getBytes
	}

	@GET 
	@Produces(Array(APPLICATION_JSON))
	//def get = this.!![Array[Byte]](GetUserList) getOrElse "not found"
	def get = send(GetUserList)

	@POST
	@Consumes(Array(APPLICATION_JSON))
	def post(in:Array[Byte]) = (this !! CreateNewUser(in)) getOrElse "user not created"

}
