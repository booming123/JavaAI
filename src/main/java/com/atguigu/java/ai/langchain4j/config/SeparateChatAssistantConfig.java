package com.atguigu.java.ai.langchain4j.config;

import com.atguigu.java.ai.langchain4j.assistant.SeparateChatAssistant;
import com.atguigu.java.ai.langchain4j.store.MongoChatMemoryStore;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeparateChatAssistantConfig {

    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Autowired
    private QwenChatModel qwenChatModel;

    /**
     * 用于根据对话的 ID（memoryId）动态构建一个 ChatMemory（对话记忆）对象。
     * @return
     */
    @Bean
    ChatMemoryProvider chatMemoryProvider(){
        // 按内存ID生成对应的 ChatMemory 实例（聊天记忆）
        return memoryId -> MessageWindowChatMemory
                .builder()
                .id(memoryId)
                .maxMessages(10)
                .chatMemoryStore(mongoChatMemoryStore) // 配置持久化对象
                .build();
    }
//    @Bean
//    SeparateChatAssistant separateChatAssistant(ChatMemoryProvider chatMemoryProvider) {
//        return AiServices.builder(SeparateChatAssistant.class)
//                .chatLanguageModel(qwenChatModel)
//                .chatMemoryProvider(chatMemoryProvider)
//                .build();
//    }
}
