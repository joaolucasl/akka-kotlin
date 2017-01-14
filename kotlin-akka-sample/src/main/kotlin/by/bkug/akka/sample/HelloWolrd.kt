package by.bkug.akka.sample

import akka.actor.Props
import akka.actor.UntypedActor
import akka.actor.ActorRef

class HelloWorld : UntypedActor() {

    override fun preStart() {
        // create the greeter actor
        val greeter = getContext().actorOf(Props.create(Greeter::class.java), "greeter")
        // tell it to perform the greeting
        greeter.tell(Greeter.Msg.GREET, getSelf())
    }

    override fun onReceive(msg: Any) {
        if (msg === Greeter.Msg.DONE) {
            // when the greeter is done, stop this actor and with it the application
            getContext().stop(getSelf())
        } else
            unhandled(msg)
    }
}
