# Hello World - LangChain4j 入门

我们从最简单的 Hello World 开始。

我们的目标是：创建一个简单的 Spring Boot 应用，通过一个接口发送问题，让 AI 回答。

为了让上手最顺滑，我们使用 LangChain4j 框架，因为它的配置最简单。

## 🛠️ 第一步：准备工作

- **开发工具**: IntelliJ IDEA (或者 Eclipse/VS Code)。
- **JDK 环境**: 建议 JDK 17 或 JDK 21 (现代 AI 框架通常需要 JDK 17+)。
- **大模型 API Key**:
    - 如果你有 OpenAI 的 Key，可以直接用。
    - 如果你在国内且没有 Key：为了跑通 Hello World，LangChain4j 提供了一个供测试用的免费 Demo Key（仅限测试，不能用于生产）。

## 📦 第二步：创建 Spring Boot 项目

- 打开 IDEA，新建项目 (Spring Initializr)。
- 选择 Maven。
- Spring Boot 版本选择 3.2.x 或 3.3.x。
- 不需要在向导里勾选特殊的依赖，我们手动加。

## 第三步：添加依赖 (pom.xml)

在 pom.xml 的 `<dependencies>` 标签中加入以下依赖。这是 LangChain4j 整合 OpenAI 的启动器。

```xml
<dependencies>
    <!-- Spring Boot Web (为了写个接口测试) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- LangChain4j OpenAI 整合包 -->
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-open-ai-spring-boot-starter</artifactId>
        <version>1.9.1-beta17</version>
    </dependency>
</dependencies>
```

记得刷新 Maven 以下载包。

## ⚙️ 第四步：配置 API Key (application.properties)

找到 `src/main/resources/application.properties` 文件。

我们需要告诉程序用哪个模型以及 Key 是多少。

### 选项 A：如果你有 OpenAI Key (并且有魔法上网环境)

```properties
langchain4j.open-ai.chat-model.api-key=你的sk-xxxxxxx
langchain4j.open-ai.chat-model.model-name=gpt-3.5-turbo
```

### 选项 B：如果你什么都没有 (使用测试专用 Demo Key)

LangChain4j 官方为了让大家体验，提供了一个特殊的 demo key。

```properties
# 这是一个公开的测试 Key，仅用于 Hello World 演示，随时可能失效或限流
langchain4j.open-ai.chat-model.api-key=demo
langchain4j.open-ai.chat-model.model-name=gpt-3.5-turbo
```

## 💻 第五步：编写 Java 代码

我们写一个极其简单的 Controller。LangChain4j 会自动配置好一个 ChatLanguageModel 供你注入使用。

在你的启动类同级目录下，创建一个 `HelloAiController.java`

```java
package com.example.demo; // 你的包名

import dev.langchain4j.model.chat.ChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
```

## 🚀 第六步：运行与测试

运行 Spring Boot 的 main 方法启动项目。

打开浏览器，访问以下地址：

```
http://localhost:8080/hello?message=你好，请用Java代码写一个Hello World
```

**预期结果：**

浏览器页面上应该会显示一段文字，类似：

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

## 🎉 恭喜！

你已经跑通了第一个 Java AI Agent（虽然目前它只是个简单的聊天机器人，还不算 Agent，因为还不会使用工具）。

## ⚠️ 可能遇到的问题

- **超时/连接失败**: 如果你用的是 demo key，可能是官方演示服务人太多或者网络不通。如果你用自己的 Key，请确保你的网络能访问 OpenAI。
- **国内模型替代方案**: 如果 OpenAI 实在连不上，我们可以换成国内的 智谱 (Zhipu AI) 或 千帆 (Qianfan)，LangChain4j 都支持，只需要改依赖和配置即可。

如果这一步跑通了，请告诉我，我们进入下一步：让 AI 学会使用"工具"（Function Calling），这才是 Agent 的灵魂。
