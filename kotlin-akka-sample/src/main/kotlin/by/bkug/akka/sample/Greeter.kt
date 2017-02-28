package by.bkug.akka.sample

import akka.actor.AbstractActor
import by.bkug.akka.sample.Msg.GREET

enum class Msg {
    GREET, DONE
}

class Greeter : AbstractActor() {
    override fun createReceive(): AbstractActor.Receive {
        return receiveBuilder()
            .matchEquals(GREET, {
                println("Hello World!")
                sender.tell(Msg.DONE, self)
            })
            .matchAny { msg ->
                unhandled(msg)
            }
            .build()
    }
}
