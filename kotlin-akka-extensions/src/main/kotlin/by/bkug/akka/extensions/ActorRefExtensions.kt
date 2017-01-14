package by.bkug.akka.extensions

import akka.actor.ActorRef
import akka.actor.ActorRefFactory

inline fun <reified T : Any> ActorRefFactory.actorOf(name: String): ActorRef {
    return actorOf(createProps<T>(), name)
}