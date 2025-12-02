package com.example.demo;

import dev.langchain4j.community.model.zhipu.ZhipuAiChatModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.Scanner;

/**
 * LangChain4j 入门演示程序
 * @author wangzhixuan
 * @version 1.0
 * @project langchain4j-learning-path
 * @description
 * @date 2025/12/2 15:08:31
 */
public class Application {
    public static void main(String[] args) {

    }

    /**
     * 选择模型提供商
     */
    private static ChatModel selectModel() {
        System.out.println("请选择模型提供商：");
        System.out.println("1. Ollama（本地模型，免费）");
        System.out.println("2. 智谱 AI（国内直连）");
        System.out.println("3. OpenAI");
        System.out.println("4. DeepSeek");
        System.out.println("0. 退出");
        System.out.print("\n请输入选项: ");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();

        try {
            switch (choice) {
                case "1":
                    return createOllamaModel();
                case "2":
                    return createZhipuModel();
                case "3":
                    return createOpenAiModel();
                case "4":
                    return createDeepSeekModel();
                case "0":
                    return null;
                default:
                    System.out.println("无效选项，使用 Ollama 作为默认值");
                    return createOllamaModel();
            }
        } catch (Exception e) {
            System.err.println("创建模型失败: " + e.getMessage());
            return null;
        }
    }

    /**
     * 创建 Ollama 模型
     */
    private static ChatModel createOllamaModel() {
        System.out.println("\n正在连接 Ollama...");
        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("qwen2:7b")
                .temperature(0.7)
                .build();
    }

    /**
     * 创建智谱模型
     */
    private static ChatModel createZhipuModel() {
        String apiKey = System.getenv("ZHIPU_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("请设置环境变量 ZHIPU_API_KEY");
        }
        System.out.println("\n正在连接智谱 AI...");
        return ZhipuAiChatModel.builder()
                .apiKey(apiKey)
                .model("glm-4-flash")
                .temperature(0.7)
                .build();
    }

    /**
     * 创建 OpenAI 模型
     */
    private static ChatModel createOpenAiModel() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("请设置环境变量 OPENAI_API_KEY");
        }
        System.out.println("\n正在连接 OpenAI...");
        return OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gpt-4o-mini")
                .temperature(0.7)
                .build();
    }

    /**
     * 创建 DeepSeek 模型
     */
    private static ChatModel createDeepSeekModel() {
        String apiKey = System.getenv("DEEPSEEK_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("请设置环境变量 DEEPSEEK_API_KEY");
        }
        System.out.println("\n正在连接 DeepSeek...");
        return OpenAiChatModel.builder()
                .baseUrl("https://api.deepseek.com")
                .apiKey(apiKey)
                .modelName("deepseek-chat")
                .temperature(0.7)
                .build();
    }

    /**
     * 交互式对话
     */
    private static void interactiveChat(ChatModel model) {

        System.out.println("\n连接成功！开始对话吧（输入 'quit' 退出）\n");
        System.out.println("━".repeat(50));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n你: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("quit") ||
                    input.equalsIgnoreCase("exit") ||
                    input.equals("退出")) {
                System.out.println("\n再见！👋");
                break;
            }

            if (input.isEmpty()) {
                continue;
            }

            try {
                System.out.print("AI: ");
                String response = model.chat(input);
                System.out.println(response);
            } catch (Exception e) {
                System.err.println("生成回复时出错: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
