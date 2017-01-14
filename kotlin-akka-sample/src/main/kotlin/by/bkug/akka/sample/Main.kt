package by.bkug.akka.sample

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.Terminated
import akka.actor.UntypedActor
import akka.event.Logging



object Main {
    @JvmStatic fun main(args: Array<String>) {
        akka.Main.main(arrayOf(HelloWorld::class.java.name))
    }
}

object Main2 {
    @JvmStatic fun main(args: Array<String>) {
        val system = ActorSystem.create("Hello")
        val a = system.actorOf(Props.create(HelloWorld::class.java), "helloWorld")
        system.actorOf(Props.create(Terminator::class.java, a), "terminator")
    }

    class Terminator(private val ref: ActorRef) : UntypedActor() {

        private val log = Logging.getLogger(getContext().system(), this)

        init {
            getContext().watch(ref)
        }

        override fun onReceive(msg: Any) {
            if (msg is Terminated) {
                log.info("{} has terminated, shutting down system", ref.path())
                getContext().system().terminate()
            } else {
                unhandled(msg)
            }
        }

    }
}