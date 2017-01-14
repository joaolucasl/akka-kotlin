package by.bkug.akka.sample

import akka.actor.UntypedActor

class Greeter : UntypedActor() {

    enum class Msg {
        GREET, DONE
    }

    override fun onReceive(msg: Any) {
        if (msg === Msg.GREET) {
            println("Hello World!")
            sender.tell(Msg.DONE, self)
        } else
            unhandled(msg)
    }

}
