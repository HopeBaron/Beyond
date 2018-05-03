package CommandFramework.NoPerms

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.core.events.message.MessageReceivedEvent

class Source : Command() {

    init {
        name = "source"
    }

    override fun execute(event: CommandEvent) {
        event.channel.sendMessage("https://github.com/HopeBaron/Beyond").queue()
    }
}