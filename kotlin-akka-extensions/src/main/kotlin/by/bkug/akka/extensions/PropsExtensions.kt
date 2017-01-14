package by.bkug.akka.extensions

import akka.actor.ActorRef
import akka.actor.Props

inline fun <reified T : Any> createProps(): Props {
    return Props.create(T::class.java)
}

inline fun <reified T : Any> createProps(actor: ActorRef): Props {
   return Props.create(T::class.java, actor)
}

