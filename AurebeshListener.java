import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AurebeshListener extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String message = event.getMessage().getContentDisplay();
		String translation, outputLink;
		
		if(message.startsWith("||ab") && message.endsWith("||")) {
			translation = message.substring(4, message.length()-2).trim();
			
			outputLink = AurebeshTranslator.getTranslationLink(translation);
			
			try {
				event.getMessage().delete().queue();
				event.getChannel().sendMessage(outputLink).queue();	
			} catch (Exception e) {
				event.getChannel().sendMessage("Oops! I need permissions to manage messages for this channel.").queue();
			}
		}
		
	}

}
