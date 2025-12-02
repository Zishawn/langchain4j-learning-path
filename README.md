# Java AI Agent 学习实战 (Java AI Agent Practice)

[![Java](https://img.shields.io/badge/Java-17%2B-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)](https://spring.io/projects/spring-boot)
[![LangChain4j](https://img.shields.io/badge/AI-LangChain4j-blue)](https://github.com/langchain4j/langchain4j)

这是一个记录从零开始构建 Java AI Agent 的学习仓库。
本项目旨在探索如何利用 Java 生态（Spring Boot, LangChain4j 等）构建大模型应用，涵盖 Prompt 工程、Function Calling、RAG（检索增强生成）以及多 Agent 协作等核心概念。

## 🚀 学习路线 (Roadmap)

- [x] **01-hello-world**: 基础环境搭建，对接 OpenAI/LLM 接口，实现简单对话。
- [ ] **02-function-calling**: 让 AI 学会使用工具（查询时间、天气、数据库）。
- [ ] **03-memory-management**: 实现聊天上下文记忆，让 AI 记住你说过的话。
- [ ] **04-rag-basic**: 简单的 RAG 实现，基于本地文档回答问题。
- [ ] **05-agent-collaboration**: 探索多智能体协作模式。

## 🛠️ 技术栈

*   **语言**: Java 17 / 21
*   **框架**: Spring Boot 3.x
*   **AI SDK**: LangChain4j (主攻) / Spring AI
*   **LLM**: OpenAI (GPT-3.5/4), Ollama (Local models)

## 快速开始 (Getting Started)

### 前置要求
1.  JDK 17+
2.  Maven 3.x
3.  OpenAI API Key (或者使用 Ollama 本地模型)

### 配置 API Key
为了安全起见，**请不要将 API Key 提交到代码仓库中**。
建议设置环境变量，或在本地创建 `application-local.properties`。

```bash
export OPENAI_API_KEY=sk-xxxxxxxxxxxxxxx
```

### 运行示例
进入对应的章节目录（例如 `01-hello-world`），运行 Spring Boot 应用即可。

## 📚 参考资料
*   [LangChain4j 官方文档](https://docs.langchain4j.dev/)
*   [Spring AI 官方文档](https://docs.spring.io/spring-ai/reference/)

---
*本项目仅供学习交流使用。*
