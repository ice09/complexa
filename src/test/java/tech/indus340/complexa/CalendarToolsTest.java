package tech.indus340.complexa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.indus340.complexa.chatbot.Assistant;

@SpringBootTest
class CalendarToolsTest {

	@Autowired
	private Assistant assistant;

	@Test
	public void shouldCallAssistantWithTools() {
		chat("Was steht heute im Kalender?");
		chat("Was passiert morgen ab 12 Uhr?");
		chat("Gibt es morgen einen Termin zwischen 20 und 21 Uhr?");
		chat("Wann ist der Tablet Einführungstermin?");
		chat("Was hat emilia übermorgen für stunden?");
		chat("Hat Emilia morgen um 14 Uhr noch schule?");
		chat("Was ist mit Simon?");
	}

	public void chat(String message) {
		System.out.println(("\nQ: " + message));
		System.out.println(("A: " + assistant.chat(message)));
	}
}
