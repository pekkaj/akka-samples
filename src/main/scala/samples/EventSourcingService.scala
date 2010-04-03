package samples

/*
import se.scalablesolutions.akka.actor.Actor

trait SnapshotEventStore extends EventStore {
	def appendSnapshotEvent(type:String, snapshotEvent:DomainEvent)
}

trait EventSourcingRepository[T <: EventSourcedAggregateRoot] {

	val eventStore:EventStore

	def doSave(aggregate:T) {
		eventStore.appendEvents(getTypeIdentifier, aggregate.getUncommittedEvents)
	}

	// this is pretty complex process, but is responsible for all the magic
	def doLoad(aggId:UUID):T = {

		// read the events from the event store based on the type and agg id
		val events = eventStore.readEvents(getTypeIdentifier, aggId)

		// 
		val aggregate = instantiateAggregate(aggId, events.peek)
		aggregate.initializeState(events)
		aggregate
	}

	def instantiateAggregate(aggid:UUID, firstEvent:DomainEvent):T = {
		// try and create a new instance of the aggregate object
	}
}

class EventSourcingService extends Actor {

	def receive = {
		case "" => 
	}
}
*/
