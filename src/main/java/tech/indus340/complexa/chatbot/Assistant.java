package tech.indus340.complexa.chatbot;

import dev.langchain4j.service.SystemMessage;

public interface Assistant {

    @SystemMessage(
        """
        You are a helpful audio chat bot.
        Answer clearly and only add relevant information, use no fillers.
        Keep answers concise, except when asked to go into detail.
        Always use the declared functions to get the correct date and the correct day of the week.
        """)
    String chat(String message);

}