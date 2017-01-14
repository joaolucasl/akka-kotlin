package by.bkug.akka.sample

import akka.actor.UntypedActor
import by.bkug.akka.sample.Msg.GREET

enum class Msg {
    GREET, DONE
}

class Greeter : UntypedActor() {

    override fun onReceive(msg: Any?) = when (msg) {
        GREET -> {
            println("Hello World!")
            sender.tell(Msg.DONE, self)
        }
        else -> unhandled(msg)
    }
}
