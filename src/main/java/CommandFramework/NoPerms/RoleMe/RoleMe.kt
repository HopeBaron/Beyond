package CommandFramework.NoPerms.RoleMe

import CommandFramework.Moderation.AddRoleMe
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.core.Permission

class RoleMe : Command() {
    init {
        name = "roleme"
        children = listOf(AddRoleMe(),ListRoleMe()).toTypedArray()
        botPermissions = listOf(Permission.MANAGE_ROLES).toTypedArray()
    }

    override fun execute(event: CommandEvent) {
        val list = AddRoleMe.list
        val roles = event.message.mentionedRoles
        val filter = roles.filter { !event.selfMember.canInteract(it) }

        if (!event.selfMember.permissions.containsAll(botPermissions.toList())) {
            event.reply("I Don't have required permissions.")
        } else if (!list.containsAll(roles)) {
            event.reply("This role is not available for roleme command")
        } else if (filter.isNotEmpty()) {
            event.reply("Can't give you this role, it's higher than me")
        } else if (roles.isEmpty()) {
            event.reply("Mention a role to add for you.")
        } else {
            event.guild.controller.addRolesToMember(event.member, roles).queue()
            event.reply("Added mentioned roles to you.")
        }


    }
}