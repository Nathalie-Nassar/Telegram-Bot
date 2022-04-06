
// import CourSeera:

package telegrambotdemo;

import java.util.Arrays;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TBot extends TelegramLongPollingBot {
	public void onUpdateReceived(Update update) {
		// if there is a message that has text
		if (update.hasMessage() && update.getMessage().hasText()) {
			
			// get the text of the message
			String receivedText = "I received: " + update.getMessage().getText();
			
			// send a reply
			SendMessage message = new SendMessage();
			message.setChatId(update.getMessage().getChatId().toString());
			
			// ----//
			
			message.setText(generatedReply(receivedText));
			
			// ----//
			try {
				execute(message);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	private String generatedReply(String receivedText) {
		/* 
		 * STEPS:
		 * 1. Parse the received text: ignore '/' - get the fct name and the parameters(if any)
		 * 2. Map the fct name to the appropriate fct in CourSeera and pass to it the pars(if any)
		 * 3. Return the string which represents the reply
		 * */
		
		String[] botFunctions = new String[] {"allroomschedule", "roomschedule", "roomscheduletime", "roomscheduleday", "whowastherelast", "profschedule", "whereisprof", "wherewillprofbe", "whoistherenow"};
		
		return ParsedString(receivedText.substring(1), botFunctions);  // ignoring the '/'
		
	}

	
	private String ParsedString(String receivedText, String[] botFunctions) {
		 
		String[] st = receivedText.split(" ");
		String function = st[0]; // get the function name of the bot
		
		if (st.length > 1) {String param = st[1];} // if any parameters
		
		// check which function it is an ordered in the botFunctions array
		 int functionIndex = Arrays.asList(botFunctions).indexOf(function);
	
		
		// use switch to call on the appropriate CourSeera function
		
		switch (functionIndex) {
		
			// Create a CourSeera object
		
			case 0: // allroomschedule = roomSchedule : lists the schedule for every room in alphabetical room number order
				return null;
			 case 1: //roomschedule = roomSchedule(Room room) : lists the schedule for a specific room
				 return null;
			 case 2: // roomscheduletime =  roomSchedule(Room room, java.time.LocalDate date) : lists the schedule for a specific room for a specific date
				 return null;
			 case 3:// roomscheduleday = roomSchedule(Room room, DayOfWeek day) : lists the schedule for a specific room for a specific day of week
				 return null;
			 case 4: // whowastherelast = whoWasThereLast(Room room) : lists the course and instructor name for the last time this room was occupied
				 return null;
			 case 5: // profschedule = profSchedule(Instructor instructor) : lists the instructor’s weekly schedule (day, time, room)
				 return null;
			 case 6: // whereisprof = whereIsProf(Instructor instructor) : lists the room where a prof is currently teaching (if any)
				 return null;
			 case 7: // wherewillprofbe = whereWillProfBe(Instructor instructor) : lists the instructor’s schedule for today
				 return null;
			 case 8: // whoistherenow = whoIsThereNow(Room room) : lists the course and instructor name currently occupying a specific room
				 return null;
				 
			 default:
					 throw new Error("Error");
				 
		}
		
	
	}
	
	public String getBotUsername() {
		return "nathalie_melhem_telegram_bot";
	}

	@Override
	public String getBotToken() {
		return "2143994897:AAFTWYbYpnSt-CWU49_nsLHCpLIJ-G38azk";
	}
}