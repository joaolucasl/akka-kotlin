package by.bkug.akka.extensions

import akka.actor.ActorRef
import akka.actor.Props
import akka.actor.UntypedActor

inline fun <reified T : Any> create() = Props.create(T::class.java)

inline fun <reified T : Any> UntypedActor.actorOf(name: String) =
        context.actorOf(Props.create(T::class.java), name)

fun UntypedActor.tell(actor: ActorRef, msg: Any?) = actor.tell(msg, this.self)