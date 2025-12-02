package cn.abaabaai.controller;

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhixuan
 * @version 1.0
 * @project langchain4j-learning-path
 * @description
 * @date 2025/12/2 10:44:28
 */
@RestController
public class HelloAiController {

    // 1. 注入 LangChain4j 自动配置好的聊天模型
    private final ChatModel chatModel;

    public HelloAiController(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    // 2. 写一个简单的接口
    @GetMapping("/hello")
    public String chat(@RequestParam(value = "message", defaultValue = "讲个笑话") String message) {
        // 3. 调用 generate 方法，把消息发给 AI，并获取返回的 String
        String response = chatModel.chat(message);
        return response;
    }
}
