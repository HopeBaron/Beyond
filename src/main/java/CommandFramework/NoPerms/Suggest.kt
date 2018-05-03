package CommandFramework.NoPerms

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.core.EmbedBuilder
import net.dv8tion.jda.core.events.message.MessageReceivedEvent
import java.awt.Color

class Suggest : Command() {
    init {
        name = "suggest"

    }

    override fun execute(event: CommandEvent) {
        val contentDisplay = event.message.contentDisplay.replaceFirst("+$name", "")
        val author = event.author
        val embedBuilder = EmbedBuilder()
        embedBuilder
                .setColor(Color.RED)
                .setThumbnail(author.avatarUrl)
                .addField("Suggestion:", contentDisplay, false)
                .addField("Author:", author.asMention, false)
                .addField("Message ID:", event.message.id, false)


        event.jda.getUserById("217914474353524736").openPrivateChannel().queue({
            it.sendMessage(embedBuilder.build()).queue()
        })
    }

}