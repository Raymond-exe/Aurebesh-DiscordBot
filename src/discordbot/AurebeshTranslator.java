package discordbot;

public class AurebeshTranslator {

	
	//link used to generate text into the aurebesh font
	private static String translatorLink = "https://everythingfonts.com/testdrive/hKenvRq3__Z8DBqPgUDqjgeF?text=";
	
	
	//adds the link above to the input, parsed so symbols are understood
	public static String getTranslationLink(String str) {
		String output = new String(translatorLink);
		StringBuilder englishPhrase = new StringBuilder(str);
		
		char temp;
		for(int i = 0; i < englishPhrase.toString().length(); i++) {
			temp = englishPhrase.charAt(i);
			
			//System.out.println("Letter at index " + i + ": " + temp);
			
			// if this character is a letter or number, skip it. Otherwise, convert to a hex value
			if ((temp >= 'a' && temp <= 'z') ||
				(temp >= 'A' && temp <= 'Z') ||
				(temp >= '0' && temp <= '9')) {
				
				//System.out.println("It's alphanumeric! Skipping...");
				continue;				
			} else {	
				
				//System.out.println("Non-alphanumeric, converting to hex...");
				
				englishPhrase.replace(englishPhrase.indexOf(String.valueOf(temp)), 		//start index
									  englishPhrase.indexOf(String.valueOf(temp))+1, 	//end index
									  "%" + Integer.toHexString((int)temp));			//phrase we're putting in
				
				//adding to the value of i so it doesn't scan the text it just generated
				i += Integer.toHexString((int)temp).length();
				
			}
			
			//System.out.println("Updated string: " + englishPhrase);
			
		}
		
		output += englishPhrase;
		
		return output;		
	}
	
	/*
	public static String untranslateLink(String link) {
		StringBuilder output;
		
		if(link.contains("?text=")) {
			output = new StringBuilder(link.substring(link.indexOf("?text=") + 6));
			
			int startIndex = output.indexOf("%");
			while(startIndex >= 0) {
				
				
				//TODO find a way to convert hex to int
				//output.replace(startIndex, startIndex + 3, (char)(Integer.output.substring(startIndex+1, startIndex+3));
				
			}
			
			
			return output;		
			
		} else {
			throw new IndexOutOfBoundsException();
		}
		
		
	}
	//*/
	
	//*
	public static void main(String[] args) {
		System.out.println(getTranslationLink(args[0]));
	} 
	//*/
}