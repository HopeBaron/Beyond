package Core

import CommandFramework.Adminstration.AutoRoleCommand
import CommandFramework.Moderation.AddRoleMe
import CommandFramework.Moderation.Ban
import CommandFramework.NoPerms.RoleMe.ListRoleMe
import CommandFramework.NoPerms.RoleMe.RoleMe
import CommandFramework.NoPerms.Source
import CommandFramework.NoPerms.Suggest
import CommandFramework.Owners.Shutdown
import Listeners.OnJoin.AutoRole
import Listeners.OnMention.CleverBot
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.jagrosh.jdautilities.command.CommandClientBuilder
import net.dv8tion.jda.core.AccountType
import net.dv8tion.jda.core.JDABuilder
import net.dv8tion.jda.core.OnlineStatus
import java.io.File


fun main(args: Array<String>) {


    val parser = Parser()
    val json: JsonObject = parser.parse(File("secure.json").path) as JsonObject
    val build = CommandClientBuilder().setOwnerId("217914474353524736")
            .setCoOwnerIds("314467770500644875")
            .setPrefix("+")
            .useHelpBuilder(false)
            .setGame(null)
            .addCommands(Ban(),
                    Shutdown(),
                    Source(),
                    Suggest(),
                    AutoRoleCommand(),
                    AddRoleMe(),
                    ListRoleMe(),
                    RoleMe()
            ).build()


    val jda = JDABuilder(AccountType.BOT)
            .setToken(json["token"].toString())
            .addEventListener(AutoRole())
            .addEventListener(CleverBot())
            .addEventListener(build)
            .setStatus(OnlineStatus.ONLINE)
            .buildBlocking()

}

