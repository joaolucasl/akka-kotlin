package by.bkug.akka.sample

import akka.actor.UntypedActor
import by.bkug.akka.extensions.actorOf
import by.bkug.akka.extensions.tell
import by.bkug.akka.sample.Msg.DONE
import by.bkug.akka.sample.Msg.GREET

class HelloWorld : UntypedActor() {

    override fun preStart() {
        // create the greeter actor
        val greeter = actorOf<Greeter>("greeter")

        // tell it to perform the greeting
        tell(greeter, GREET)
    }

    override fun onReceive(msg: Any?) = when (msg) {
        DONE -> {
            // when the greeter is done, stop this actor and with it the application
            context.stop(self)
        }
        else -> unhandled(msg)
    }
}
