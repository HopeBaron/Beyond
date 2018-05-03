package CommandFramework.Owners

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

class Shutdown:Command() {
    init {
        ownerCommand = true
        name = "shutdown"
    }

    override fun execute(event: CommandEvent) {
        event.reply("Turning Off...")
        event.jda.shutdownNow()

    }
}