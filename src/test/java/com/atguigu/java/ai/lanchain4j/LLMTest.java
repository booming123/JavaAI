package com.atguigu.java.ai.lanchain4j;

import com.atguigu.java.ai.langchain4j.XiaozhiApp;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = XiaozhiApp.class)
public class LLMTest {

    /**
     * Gpt 4o-mini 语言接入模型测试
     */
    @Test
    public void testGptDemo(){
        // 初始化模型
        OpenAiChatModel model = OpenAiChatModel.builder() // //LangChain4j提供的代理服务器
                .baseUrl("http://langchain4j.dev/demo/openai/v1") // 显式指定 baseUrl
                .apiKey("demo")   // 设置模型apiKey
                .modelName("gpt-4o-mini") // 设置模型名称
                .build();
        String res = model.chat("你好");
        System.out.println(res);
    }

    @Autowired
    OpenAiChatModel openAiChatModel;
    @Test
    public void testSpringBoot(){
        String res = openAiChatModel.chat("你是谁");
        System.out.println(res);
    }

    @Autowired
    private OllamaChatModel ollamaChatModel;
    @Test
    public void testOllama(){
        String res = ollamaChatModel.chat("你是谁");
        System.out.println(res);
    }

    /**
     * 通义千问大模型
     */
    @Autowired
    private QwenChatModel qwenChatModel;
    @Test
    public void testDashScopeQwen() {
//向模型提问
        String answer = qwenChatModel.chat("你好");
//输出结果
        System.out.println(answer);
    }

    @Test
    public void testDashScopeWanx(){
        WanxImageModel wanxImageModel = WanxImageModel.builder()
                .modelName("wanx2.1-t2i-plus")
                .apiKey("sk-3a5b1753b99e45358a9ee2a4bdd1b53b")
                .build();
        Response<Image> response = wanxImageModel.generate("奇幻森林精灵：在一片弥漫着轻柔薄雾的\n" +
                "                古老森林深处，阳光透过茂密枝叶洒下金色光斑。一位身材娇小、长着透明薄翼的精灵少女站在一朵硕大的蘑菇上。她\n" +
                "                有着海藻般的绿色长发，发间点缀着蓝色的小花，皮肤泛着珍珠般的微光。身上穿着由翠绿树叶和白色藤蔓编织而成的\n" +
                "                连衣裙，手中捧着一颗散发着柔和光芒的水晶球，周围环绕着五彩斑斓的蝴蝶，脚下是铺满苔藓的地面，蘑菇和蕨类植\n" +
                "                物丛生，营造出神秘而梦幻的氛围。");
                System.out.println(response.content().url());
    }


}
