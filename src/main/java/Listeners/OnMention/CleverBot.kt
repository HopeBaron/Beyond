package Listeners.OnMention

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import frederikam.jca.JCA
import frederikam.jca.JCABuilder
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter
import java.io.File

class CleverBot : ListenerAdapter() {

    val parser = Parser()
    val json: JsonObject = parser.parse("secure.json") as JsonObject

    val cleverbot = JCABuilder().setKey(json["key"].toString())
            .setUser(json["user"].toString())
            .buildBlocking()


    override fun onMessageReceived(event: MessageReceivedEvent) {
        val contentDisplay = event.message.contentDisplay
        val me = event.jda.selfUser
        if (contentDisplay.contains("beyond",true) && event.author != me) {
            var response = ""
            do {
                event.channel.sendTyping().queue()
                val cleverMsg = contentDisplay.replace("beyond", "cleverbot",true)
                response = cleverbot.getResponse(cleverMsg)
            } while (response.isEmpty())
            event.channel.sendMessage(response).queue()

        }
    }
}