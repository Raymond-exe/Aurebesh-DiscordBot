package discordbot;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AurebeshListener extends ListenerAdapter {
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String message = event.getMessage().getContentDisplay().trim();
		String translation, output;
		
		if(message.startsWith(DiscordBot.getJda().getSelfUser().getAsMention())) {
			String[] args = message.split(" ");
			
			//if the message has args, figure out which commands they are. Otherwise, just send the help message.
			if(args.length > 1) {
				
				//switch case to sort commands
				switch(args[1].toLowerCase()) {
				case "help":
					helpCmd(event);
					break;
				case "key":
				case "guide":
				case "alphabet":
					keyCmd(event);
					break;
				default:
					event.getChannel().sendMessage("Unknown command. Use `" 
													+ DiscordBot.getJda().getSelfUser().getAsMention() 
													+ " help` to see the list of commands!").queue();;
				}
				
			} else {
				helpCmd(event);
			}
			
			
		} else if(message.startsWith("||ab") && message.endsWith("||")) {
			translation = message.substring(4, message.length()-2).trim();
			
			output = AurebeshTranslator.getTranslationLink(translation + " -@" + event.getAuthor().getName());
			
			try {
				event.getMessage().delete().queue();
				event.getChannel().sendMessage(output).queue();	
			} catch (Exception e) {
				event.getChannel().sendMessage("Oops! I need permissions to manage messages for this channel.").queue();
			}
		}
		
	}
	
	private void helpCmd(GuildMessageReceivedEvent event) {
		
	}
	
	private void keyCmd(GuildMessageReceivedEvent event) {
		
	}

}
