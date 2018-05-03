package CommandFramework.NoPerms.RoleMe

import CommandFramework.Moderation.AddRoleMe
import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

class ListRoleMe : Command() {
    init {
        name = "list"

    }

    override fun execute(event: CommandEvent) {
        val list = AddRoleMe.list
        if (list.isEmpty()) event.reply("No roles available.")
        else {
            val builder = StringBuilder()
            list.forEach { builder.append("\n$it") }
            event.reply(builder.toString())
        }
    }
}