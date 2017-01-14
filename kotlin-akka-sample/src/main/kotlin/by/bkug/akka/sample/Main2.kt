package by.bkug.akka.sample

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Terminated
import akka.actor.UntypedActor
import akka.event.Logging
import by.bkug.akka.extensions.actorOf
import by.bkug.akka.extensions.createProps

fun main(args: Array<String>) {
    ActorSystem.create("Hello").apply {
        val actor = actorOf<HelloWorld>("helloWorld")
        val props = createProps<Terminator>(actor)

        actorOf(props, "terminator")
    }
}

class Terminator(private val ref: ActorRef) : UntypedActor() {
    private val log = Logging.getLogger(context.system(), this)

    init {
        context.watch(ref)
    }

    override fun onReceive(msg: Any?) {
        when (msg) {
            is Terminated -> {
                log.info("{} has terminated, shutting down system", ref.path())
                context.system().terminate()
            }
            else -> unhandled(msg)
        }
    }
}