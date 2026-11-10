package com.tayeb.chatbot;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.ToolCallAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class agent_AI {
    ChatClient chatClient;

    public agent_AI(ChatClient.Builder chatClient , ChatMemory chatMemory , ToolCallbackProvider tools) {
        this.chatClient = chatClient
                .defaultSystem("""
Vous êtes un assistant médical.
Appelez TOUJOURS les outils MCP pour obtenir des données fraîches.
Après avoir reçu les résultats des outils, reformatez TOUJOURS
la réponse en texte naturel lisible en français.
Ne retournez JAMAIS du JSON brut à l'utilisateur.
N'ajoute pas des informations.
Si hors contexte repond par je ne sais pas.  
Exemple : "Voici les patients : 1- Nom: X, Prénom: Y, Ville: Z"
""")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .defaultToolCallbacks(tools)
                .build();
    }
    public String ask(Prompt prompt) {
        return chatClient.prompt(prompt).call().content();
    }

}
