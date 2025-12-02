# LangChain4j 学习路线

## 📚 学习前提

### 必备基础
- Java 基础扎实（JDK 17+）
- Maven/Gradle 项目管理
- Spring Boot 基础（推荐）
- 了解 RESTful API 概念
- 基本的 AI/LLM 概念认知

### 推荐准备
- 了解 OpenAI API 基本概念
- 准备好 API Key（OpenAI / 其他国内大模型）
- IDE：IntelliJ IDEA

---

## 📅 第一周：基础入门

### Day 1：认识 LangChain4j
- [ ] 什么是 LangChain？什么是 LangChain4j？
- [ ] LangChain4j 与 Python LangChain 的关系与区别
- [ ] LangChain4j 核心特性概览
- [ ] 适用场景分析
- [ ] 官方文档导读：https://docs.langchain4j.dev/

### Day 2：环境搭建与第一个程序
- [ ] JDK 17+ 环境确认
- [ ] Maven/Gradle 依赖配置
- [ ] 选择大模型提供商（OpenAI / 智谱 / 通义千问 / Ollama 本地）
- [ ] API Key 配置与安全管理
- [ ] Hello World：第一次调用大模型

### Day 3：核心概念 - ChatLanguageModel
- [ ] ChatLanguageModel 接口详解
- [ ] 同步调用 vs 流式调用（Streaming）
- [ ] UserMessage / AiMessage / SystemMessage 消息类型
- [ ] 模型参数配置（temperature, maxTokens 等）
- [ ] 不同模型提供商的实现差异

### Day 4：核心概念 - Prompt 提示词
- [ ] 什么是 Prompt Engineering
- [ ] PromptTemplate 模板使用
- [ ] 结构化 Prompt 设计
- [ ] SystemMessage 的作用与最佳实践
- [ ] Few-shot 示例的使用

### Day 5：核心概念 - AiServices
- [ ] AiServices 是什么？解决什么问题？
- [ ] 声明式 AI 服务定义
- [ ] @SystemMessage 注解
- [ ] @UserMessage 注解
- [ ] 方法参数与返回值映射

### Day 6：结构化输出
- [ ] 为什么需要结构化输出？
- [ ] 返回 POJO 对象
- [ ] 返回 List / Map 等集合
- [ ] @Description 注解优化输出
- [ ] JSON Schema 的底层原理

### Day 7：周末复习与实践
- [ ] 复习本周核心概念
- [ ] 动手项目：简单的 AI 问答助手
- [ ] 整理学习笔记
- [ ] 梳理遇到的问题与解决方案

---

## 📅 第二周：Memory 与上下文管理

### Day 8：对话记忆（ChatMemory）基础
- [ ] 为什么 AI 需要记忆？
- [ ] ChatMemory 接口详解
- [ ] MessageWindowChatMemory 使用
- [ ] TokenWindowChatMemory 使用
- [ ] 记忆窗口大小的权衡

### Day 9：ChatMemory 进阶
- [ ] 多轮对话实现
- [ ] 记忆与 AiServices 集成
- [ ] @MemoryId 注解：多用户记忆隔离
- [ ] 自定义 ChatMemory 实现
- [ ] 记忆的生命周期管理

### Day 10：持久化记忆
- [ ] 为什么需要持久化？
- [ ] ChatMemoryStore 接口
- [ ] 内存存储 vs 持久化存储
- [ ] 集成 Redis 存储记忆
- [ ] 集成数据库存储记忆

### Day 11：上下文压缩与优化
- [ ] Token 限制问题
- [ ] 对话历史摘要（Summarization）
- [ ] 消息压缩策略
- [ ] 成本与效果的平衡
- [ ] 实战：长对话优化

### Day 12：流式响应（Streaming）
- [ ] 什么是流式响应？用户体验优势
- [ ] StreamingChatLanguageModel
- [ ] TokenStream 处理
- [ ] 流式响应与 AiServices 结合
- [ ] 错误处理与超时控制

### Day 13：模型切换与多模型策略
- [ ] 支持的模型提供商列表
- [ ] OpenAI / Azure OpenAI 配置
- [ ] 国内模型：智谱 GLM / 通义千问 / 文心一言
- [ ] 本地模型：Ollama 集成
- [ ] 模型选择策略与成本优化

### Day 14：周末项目实践
- [ ] 项目：多轮对话聊天机器人
- [ ] 支持记忆持久化
- [ ] 支持流式输出
- [ ] 代码整理与最佳实践总结

---

## 📅 第三周：RAG 检索增强生成

### Day 15：RAG 概念入门
- [ ] 什么是 RAG？为什么需要 RAG？
- [ ] RAG 解决的核心问题（知识截止、幻觉、私有数据）
- [ ] RAG 工作流程详解
- [ ] LangChain4j 中的 RAG 架构
- [ ] RAG vs Fine-tuning 对比

### Day 16：文档加载（Document Loaders）
- [ ] Document 数据结构
- [ ] 加载文本文件（.txt）
- [ ] 加载 PDF 文件
- [ ] 加载 Word 文档
- [ ] 加载网页内容
- [ ] 自定义 DocumentLoader

### Day 17：文档分割（Text Splitters）
- [ ] 为什么需要分割文档？
- [ ] DocumentSplitter 接口
- [ ] 按字符分割
- [ ] 按段落分割
- [ ] 递归分割策略
- [ ] Overlap（重叠）的作用与配置

### Day 18：Embedding 向量嵌入
- [ ] 什么是 Embedding？原理简述
- [ ] EmbeddingModel 接口
- [ ] OpenAI Embedding 模型
- [ ] 国内 Embedding 模型选择
- [ ] 本地 Embedding 方案
- [ ] Embedding 维度与性能权衡

### Day 19：向量存储（Embedding Store）
- [ ] 什么是向量数据库？
- [ ] EmbeddingStore 接口
- [ ] 内存存储（InMemoryEmbeddingStore）
- [ ] Chroma 集成
- [ ] Milvus 集成
- [ ] PGVector（PostgreSQL）集成
- [ ] 其他向量数据库选项

### Day 20：检索与查询
- [ ] 相似度搜索原理
- [ ] EmbeddingStoreContentRetriever
- [ ] 检索参数调优（topK, minScore）
- [ ] 元数据过滤（Metadata Filtering）
- [ ] 混合检索策略

### Day 21：RAG 完整实战
- [ ] 端到端 RAG 流程整合
- [ ] EasyRAG 快速上手
- [ ] 使用 AiServices 集成 RAG
- [ ] @ContentRetriever 注解
- [ ] 实战项目：私有知识库问答系统

---

## 📅 第四周：RAG 进阶与优化

### Day 22：高级检索策略
- [ ] 查询转换（Query Transformation）
- [ ] 查询扩展（Query Expansion）
- [ ] 查询分解（Query Decomposition）
- [ ] 假设性文档嵌入（HyDE）
- [ ] 多查询检索

### Day 23：检索重排序（Re-ranking）
- [ ] 为什么需要重排序？
- [ ] ContentAggregator 使用
- [ ] Cohere Rerank 集成
- [ ] 自定义重排序逻辑
- [ ] 检索精度评估

### Day 24：RAG 评估与优化
- [ ] RAG 评估指标介绍
- [ ] 检索质量评估
- [ ] 生成质量评估
- [ ] 常见问题诊断
- [ ] 分块策略优化
- [ ] 提示词优化技巧

### Day 25：多模态 RAG
- [ ] 图片内容理解
- [ ] 图片 + 文本混合检索
- [ ] 表格数据处理
- [ ] 实战：多模态文档问答

### Day 26：生产环境 RAG 架构
- [ ] 大规模文档处理
- [ ] 增量更新策略
- [ ] 向量索引优化
- [ ] 缓存策略
- [ ] 监控与日志

### Day 27-28：RAG 项目实战
- [ ] 综合项目：企业知识库系统
- [ ] 支持多种文档格式
- [ ] 支持增量更新
- [ ] Web 界面集成
- [ ] 性能优化与测试

---

## 📅 第五周：Tools 与 Agent

### Day 29：Tools 工具概念
- [ ] 什么是 Tools？为什么 AI 需要工具？
- [ ] Function Calling 原理
- [ ] @Tool 注解详解
- [ ] 工具参数定义
- [ ] 工具返回值处理

### Day 30：内置工具使用
- [ ] 搜索工具（Web Search）
- [ ] 计算器工具
- [ ] 代码执行工具
- [ ] 文件操作工具
- [ ] HTTP 请求工具

### Day 31：自定义工具开发
- [ ] 设计工具的最佳实践
- [ ] 工具描述的重要性
- [ ] 参数校验与错误处理
- [ ] 异步工具实现
- [ ] 实战：天气查询工具、数据库查询工具

### Day 32：Agent 智能体基础
- [ ] 什么是 Agent？
- [ ] Agent 与普通 AI 调用的区别
- [ ] Agent 的核心循环（思考-行动-观察）
- [ ] LangChain4j 中的 Agent 实现
- [ ] 工具选择与调用流程

### Day 33：Agent 进阶
- [ ] 多工具协作
- [ ] 工具调用链
- [ ] Agent 记忆管理
- [ ] 错误恢复机制
- [ ] 调用次数限制与超时控制

### Day 34：ReAct 模式
- [ ] ReAct 论文思想
- [ ] 推理（Reasoning）+ 行动（Acting）
- [ ] 在 LangChain4j 中实现 ReAct
- [ ] 调试与日志追踪
- [ ] 常见问题与解决

### Day 35：Agent 实战项目
- [ ] 综合项目：AI 助手（支持搜索、计算、文件操作）
- [ ] 工具组合与优化
- [ ] 用户交互设计
- [ ] 异常处理与降级策略

---

## 📅 第六周：Spring Boot 集成与高级特性

### Day 36：Spring Boot 集成基础
- [ ] langchain4j-spring-boot-starter 介绍
- [ ] 自动配置详解
- [ ] application.yml 配置项
- [ ] Bean 注入与使用
- [ ] 多模型配置

### Day 37：Spring Boot 集成进阶
- [ ] AiServices 自动注入
- [ ] 配置多个 AI 服务
- [ ] Profile 环境配置
- [ ] 健康检查集成
- [ ] Actuator 监控端点

### Day 38：可观测性与调试
- [ ] 日志配置最佳实践
- [ ] Token 使用量追踪
- [ ] 请求/响应日志
- [ ] 调用链追踪
- [ ] 成本监控与告警

### Day 39：安全与限流
- [ ] API Key 安全管理
- [ ] 用户输入校验
- [ ] Prompt 注入防护
- [ ] 速率限制（Rate Limiting）
- [ ] 敏感内容过滤

### Day 40：异步与并发
- [ ] 异步调用模式
- [ ] CompletableFuture 集成
- [ ] 响应式编程支持
- [ ] 批量处理优化
- [ ] 并发控制策略

### Day 41：缓存与性能优化
- [ ] 响应缓存策略
- [ ] Embedding 缓存
- [ ] 连接池优化
- [ ] 超时配置
- [ ] 性能测试与基准

### Day 42：周末综合实践
- [ ] 综合项目：生产级 AI 应用
- [ ] 完整的 Spring Boot 项目
- [ ] 包含 RAG + Tools + Agent
- [ ] 可观测性集成
- [ ] 部署与运维考虑

---

## 📅 第七周：高级主题与实战

### Day 43：多模态能力
- [ ] 图像理解（Vision）
- [ ] 图像生成（Image Generation）
- [ ] 语音转文字
- [ ] 文字转语音
- [ ] 多模态应用场景

### Day 44：模型微调与自定义
- [ ] 何时需要微调？
- [ ] OpenAI Fine-tuning 集成
- [ ] 自定义模型接入
- [ ] 模型适配器开发
- [ ] 本地模型部署

### Day 45：高级 Prompt 技术
- [ ] Chain of Thought（思维链）
- [ ] Tree of Thoughts
- [ ] Self-Consistency
- [ ] 少样本学习优化
- [ ] Prompt 版本管理

### Day 46：分类与意图识别
- [ ] 文本分类实现
- [ ] 意图识别系统
- [ ] 情感分析
- [ ] 实体抽取
- [ ] 构建智能路由

### Day 47：对话流程管理
- [ ] 多轮对话状态机
- [ ] 对话槽位填充
- [ ] 对话流程编排
- [ ] 上下文切换处理
- [ ] 对话超时与恢复

### Day 48：测试策略
- [ ] 单元测试最佳实践
- [ ] Mock AI 服务
- [ ] 集成测试
- [ ] 评估指标设计
- [ ] 回归测试策略

### Day 49：项目架构设计
- [ ] AI 应用架构模式
- [ ] 分层设计
- [ ] 模块化与扩展性
- [ ] 错误处理规范
- [ ] 配置管理

---

## 📅 第八周：综合项目与部署

### Day 50-52：综合项目开发（一）
**项目：智能客服系统**
- [ ] 需求分析与技术选型
- [ ] 系统架构设计
- [ ] 知识库构建（RAG）
- [ ] 意图识别模块
- [ ] 多轮对话管理

### Day 53-54：综合项目开发（二）
- [ ] 工具集成（查订单、查物流等）
- [ ] 人工转接机制
- [ ] 对话日志与分析
- [ ] 前端界面开发
- [ ] 接口文档编写

### Day 55：部署与运维
- [ ] Docker 容器化
- [ ] Kubernetes 部署
- [ ] 环境变量与配置管理
- [ ] 日志收集与分析
- [ ] 监控与告警

### Day 56：总结与展望
- [ ] 学习路线回顾
- [ ] 核心知识点总结
- [ ] 常见问题 FAQ
- [ ] LangChain4j 生态展望
- [ ] 持续学习资源推荐

---

## 📖 推荐学习资源

### 官方资源
- 官方文档：https://docs.langchain4j.dev/
- GitHub 仓库：https://github.com/langchain4j/langchain4j
- 官方示例：https://github.com/langchain4j/langchain4j-examples

### 社区资源
- Discord 社区
- Stack Overflow 标签
- 中文社区与博客

### 延伸学习
- Prompt Engineering 指南
- 向量数据库深入学习
- LLM 原理与发展
- AI 应用架构设计

---

## ✅ 学习建议

1. **循序渐进**：按照路线顺序学习，不要跳跃
2. **动手实践**：每个知识点都要写代码验证
3. **做好笔记**：记录问题、解决方案、心得体会
4. **项目驱动**：每周都有实战项目，认真完成
5. **参与社区**：遇到问题多交流，也可以帮助他人
6. **关注更新**：LangChain4j 迭代快，及时关注新特性

---

> 💡 **提示**：此路线预计需要 8 周（约 2 个月）完成，每天学习 2-3 小时。可根据个人情况调整进度。