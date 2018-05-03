package CommandFramework.Moderation

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.core.Permission
import net.dv8tion.jda.core.entities.Role

class AddRoleMe : Command() {
    init {
        name = "add"
        userPermissions = listOf(Permission.MANAGE_ROLES).toTypedArray()
        botPermissions = listOf(Permission.MANAGE_ROLES).toTypedArray()
    }

    companion object {
        val list = ArrayList<Role>()

    }

    override fun execute(event: CommandEvent) {
        val roles = event.message.mentionedRoles
        val filter = roles.filter { !event.selfMember.canInteract(it) }


        if (!event.member.permissions.containsAll(userPermissions.toList())) {
            event.reply("Do you really think i would allow you to do this ?")
        } else if (roles.isEmpty()) {
            event.reply("Mention a role to add for you.")
        } else if (filter.isNotEmpty()) {
            event.reply("Can't add this role, it's higher than me")
        } else if (!event.selfMember.permissions.containsAll(botPermissions.toList())) {
            event.reply("I don't have required permissions")
        } else {
            list.addAll(roles)
            val stringBuilder = StringBuilder("")
            stringBuilder.append("These roles can be choosen by members now.")
            roles.forEach {
                stringBuilder.append("\n${it.name}")
            }

            event.reply(stringBuilder.toString())
        }
    }
}