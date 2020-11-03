package discordbot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class DiscordBot {

    private static JDA jda;
    public static boolean debugPrintouts = true;


    public static void main(String[] args) { 

        if(debugPrintouts)
            System.out.println("[DEBUG - DiscordBot] Retrieving SPECBOT_DISCORD_TOKEN...");
        EnvironmentManager.instantiate();
        String discordToken = EnvironmentManager.get("AUREBESH_DISCORD_TOKEN");

        if(debugPrintouts)
            System.out.println("[DEBUG - DiscordBot] Logging in JDA...");
        
        try {
            jda = new JDABuilder(
            		AccountType.BOT)
            		.setToken(discordToken).build();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        AurebeshListener cmd = new AurebeshListener();

        jda.addEventListener(cmd);
        
        if(debugPrintouts) {
        	System.out.println("[DEBUG - DiscordBot] Logged in as: @" + jda.getSelfUser().getAsTag());
        	System.out.println("[DEBUG - DiscordBot] List of guilds: " + jda.getGuilds());
            System.out.println("[DEBUG - DiscordBot] Awaiting connection...");
        }
        try {
        	jda.awaitReady();
        	if(debugPrintouts)
        		System.out.println("[DEBUG - DiscordBot] Successfully connected.");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public static JDA getJda() {
    	return jda;
    }

}