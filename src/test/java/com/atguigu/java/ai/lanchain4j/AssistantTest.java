package com.atguigu.java.ai.lanchain4j;

import com.atguigu.java.ai.langchain4j.XiaozhiApp;
import com.atguigu.java.ai.langchain4j.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = XiaozhiApp.class)
public class AssistantTest {

    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testChat() {
//创建AIService
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
//调用service的接口
        String answer = assistant.chat("Hello");
        System.out.println(answer);
    }

    @Autowired
    private Assistant assistant;

    @Test
    public void testAssistant(){
        String hello = assistant.chat("Hello");
        System.out.println(hello);
    }


}
