package samples

import se.scalablesolutions.akka.actor.SupervisorFactory
import se.scalablesolutions.akka.config.ScalaConfig._

class Boot {
	
	val factory = SupervisorFactory(
		SupervisorConfig(
			RestartStrategy(OneForOne, 3, 100, List(classOf[Exception])),
			Supervise(new IndexService, LifeCycle(Permanent)) :: 
			Supervise(new BasicJsonRestService, LifeCycle(Permanent)) :: Nil))


	factory.newInstance.start
}
