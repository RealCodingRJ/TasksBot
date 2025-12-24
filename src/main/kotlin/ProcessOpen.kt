import fuel.Fuel
import fuel.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import java.awt.Desktop
import java.net.URI

suspend fun openProcessSite(message: MessageReceivedEvent) {
    val url = message.message.contentRaw

    val desk = Desktop.getDesktop()
    val urlOpen = withContext(Dispatchers.IO) {
        desk.browse(URI.create(url))
    }

    if (url == "Github") {
        val githubLink = Fuel.get("https://www.github.com/realcodingry")

        val openGithubPage = withContext(Dispatchers.IO) {

            desk.browse(URI.create(githubLink.toString()))
        }

        println(openGithubPage)
    }

    val input = Fuel.get(urlOpen.toString())
    println(input)
}