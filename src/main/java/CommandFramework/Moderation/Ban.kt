package CommandFramework.Moderation

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.core.Permission

class Ban : Command() {

    init {
        name = "ban"
        userPermissions = listOf(Permission.BAN_MEMBERS).toTypedArray()
        botPermissions = listOf(Permission.BAN_MEMBERS).toTypedArray()
    }

    override fun execute(event: CommandEvent) {
        val message = event.message
        val channel = event.channel
        val guild = event.guild
        val author = event.member
        val me = event.guild.selfMember
        val mentionedMembers = message.mentionedMembers
        var canInteract = true
        var meCanInteract = true
        if(!event.member.permissions.containsAll(userPermissions.toList())){
            event.reply("Do you really think i would allow you to do this ?")
        }

        else if (!event.selfMember.permissions.containsAll(botPermissions.toList())){
            event.reply("I don't have required permissions")
        } else
        when (mentionedMembers.size) {

            0 -> channel.sendMessage("I can't ban no one, specify a member").queue()
            in 0..30 -> {
                for (member in mentionedMembers) {
                    meCanInteract = me.canInteract(member)
                    canInteract = author.canInteract(member)
                    if (!canInteract || !meCanInteract) {
                        channel.sendMessage("Can't ban this member he has a higher rank.").queue()
                        break
                    }
                }
                if (canInteract && meCanInteract) {
                    mentionedMembers.forEach { guild.controller.ban(it, 0).queue() }
                    channel.sendMessage("User got banned from the Matrix. Cheers.").queue()
                }
            }
            else -> {
                channel.sendMessage("I can't ban more than 30 user per ban command.")
            }
        }

    }
}


