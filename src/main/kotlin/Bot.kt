import kotlinx.coroutines.runBlocking
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import java.io.IO

import java.time.LocalDate

class MessageListener : ListenerAdapter() {

    override fun onMessageReceived(message: MessageReceivedEvent) {

        try {
            if (message.author.isBot) return
            val channelSender = message.channel.asTextChannel()

            runBlocking {
                openProcessSite(message)
            }

            if (LocalDate.now().monthValue == 12 && LocalDate.now().dayOfMonth == 25) {

                channelSender.sendMessage("It's Christmas Time").queue()
            }

        } catch (e: Exception) {
            IO.println(e.message)
        }
    }
}

fun main() {

    val client: JDA = JDABuilder.createDefault("")
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .disableCache(CacheFlag.VOICE_STATE)
        .setActivity(Activity.playing("I am Funny"))
        .build()

    client.addEventListener(MessageListener())
}