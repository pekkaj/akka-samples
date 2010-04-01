/*
sealed trait Event

case class Login(username:String) extends Event
case class Logout(username:String) extends Event

case class ChatMessage(fromUser:String, message:String) extends Event

case class GetChatLog(fromUser:String) extends Event
case class ChatLog(messages:List[String]) extends Event

class ChatClient(val name:String) {

	import Actor.Sender.Self

	val chat:ChatServer = ...

	def login = ChatService ! Login(name)
	def logout = ChatService ! Logout(name)

	def post(message:Strig) = ChatService ! ChatMessage(name, name + ": "+ message)

	def chatLog:ChatLog = {
		val option = ChatService !! (GetChatLog(name), 1000) //timeout 1000ms
		option.getOrElse(throw new Exception("Couldn't get the chat log"))
	}
}

class Session(user:String, storage:Actor) extends Actor {

	private val loginTime = System.currentTimeMillis
	private var userLog:List[String] = Nil

	log.info("new session fro user [%s] has been created at [%s]", user, loginTime)

	def receive = {
		case event:ChatMessage =>
			userLog ::= event.message
			storage ! event
		case event:GetChatLog =>
			storage forward event
	}
}
*/
