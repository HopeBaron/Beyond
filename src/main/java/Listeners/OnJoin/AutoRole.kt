package Listeners.OnJoin

import net.dv8tion.jda.core.events.guild.GuildJoinEvent
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent
import net.dv8tion.jda.core.hooks.ListenerAdapter

class AutoRole : ListenerAdapter() {
    companion object {

         var active = false
         var autoRoleID = ""
    }

    override fun onGuildMemberJoin(event: GuildMemberJoinEvent) {

        println("$active, $autoRoleID")
        if (active) {
            val member = event.member
            val role = event.guild.getRoleById(autoRoleID)
            event.guild.controller.addSingleRoleToMember(member, role).queue()
        }
    }


}


