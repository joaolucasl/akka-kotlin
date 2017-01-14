package by.bkug.akka.sample

import java.time.Clock.system
import java.awt.SystemColor.info



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

        fun onReceive(msg: Any) {
            if (msg is Terminated) {
                log.info("{} has terminated, shutting down system", ref.path())
                getContext().system().terminate()
            } else {
                unhandled(msg)
            }
        }

    }
}