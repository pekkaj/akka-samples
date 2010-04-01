package samples

import se.scalablesolutions.akka.actor.SupervisorFactory
import se.scalablesolutions.akka.config.ScalaConfig._

class Boot {
	
	val factory = SupervisorFactory(
		SupervisorConfig(
			RestartStrategy(OneForOne, 3, 100, List(classOf[Exception])),
			Supervise(new HelloActor, LifeCycle(Permanent)) :: Nil
		)
	)

	println("======================================")

	factory.newInstance.start
}
