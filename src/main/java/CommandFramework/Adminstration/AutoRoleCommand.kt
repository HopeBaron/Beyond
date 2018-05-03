package CommandFramework.Adminstration

import Listeners.OnJoin.AutoRole
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.core.Permission

class AutoRoleCommand : Command() {


    init {

        name = "autorole"
        userPermissions = listOf(Permission.MANAGE_ROLES).toTypedArray()
        botPermissions = listOf(Permission.MANAGE_ROLES).toTypedArray()

    }

    override fun execute(event: CommandEvent) {
        val mentionedRoles = event.message.mentionedRoles
        val channel = event.channel
        val contentDisplay = event.message.contentDisplay
        val split = contentDisplay.split("\\s+".toRegex())

        if (!event.member.permissions.containsAll(userPermissions.toList())) {
            event.reply("Do you really think i would allow you to do this ?")
        } else if (!event.selfMember.permissions.containsAll(botPermissions.toList())) {
            event.reply("I don't have required permissions")
        } else

            when (split.size) {
                1 -> {
                    if (AutoRole.active) {
                        AutoRole.active = false
                        channel.sendMessage("Auto role is now off").queue()


                    } else if (AutoRole.autoRoleID == "") {
                        channel.sendMessage("Please provide a role, you didn't specify any before").queue()
                    } else channel.sendMessage("Auto role is now on").queue();AutoRole.active = true
                }


                else -> {

                    when (mentionedRoles.size) {
                        0 -> channel.sendMessage("Please, declare a Role.").queue()
                        1 -> {
                            val role = mentionedRoles[0]
                            AutoRole.active = true
                            AutoRole.autoRoleID = role.id
                            channel.sendMessage("AutoRole -> ${role.name}").queue()

                        }

                        else -> channel.sendMessage("Too many roles declared.").queue()
                    }

                }


            }

    }


}