package samples

import se.scalablesolutions.akka.actor._

object SimpleReplyService {

 def main(args : Array[String]) : Unit = {
   val actor2 = new Actor2
   actor2.start

   val actor1 = new Actor1(actor2)
   actor1.start

   actor1 ! "m1"

	actor1.stop
	actor2.stop

 }
}

class Actor1(actor: Actor) extends Actor {
 def receive = {
   case "m1" => println("Actor1 got m1"); actor ! "m2";
   case "m3" => println("Actor1 got m3")
 }
}

class Actor2 extends Actor {
 def receive = {
   case "m2" => {
     println("Actor2 got m2")

     //throws java.util.NoSuchElementException
     println(sender)
	// sender.get ! "m3"

     //does nothing
     reply("m3")
   }
 }
}
