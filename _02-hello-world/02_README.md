# Day 2：环境搭建与第一个程序

## 📚 今日学习目标

通过今天的学习，你将能够：
- 确认并配置 JDK 17+ 开发环境
- 掌握 Maven/Gradle 依赖配置方法
- 了解各大模型提供商的特点与选择策略
- 学会安全管理 API Key
- 成功运行第一个 LangChain4j 程序

---

## 一、JDK 17+ 环境确认

### 1.1 为什么需要 JDK 17+？

┌─────────────────────────────────────────────────────────────┐
│ JDK 版本要求说明 │
├─────────────────────────────────────────────────────────────┤
│ │
│ LangChain4j 最低支持：JDK 8 │
│ 推荐版本：JDK 17 或 JDK 21（LTS 版本） │
│ │
│ 推荐 JDK 17+ 的原因： │
│ ✅ Record 类型 - 简化数据类定义 │
│ ✅ Sealed Classes - 更好的类型安全 │
│ ✅ Pattern Matching - 更简洁的代码 │
│ ✅ Text Blocks - 多行字符串（适合 Prompt） │
│ ✅ 更好的性能和安全性 │
│ ✅ Spring Boot 3.x 要求 JDK 17+ │
│ │
└─────────────────────────────────────────────────────────────┘


### 1.2 检查当前 JDK 版本

打开终端/命令行，执行以下命令：

```bash
# 检查 Java 版本
java -version

# 检查 Javac 版本
javac -version

# 检查 JAVA_HOME 环境变量
echo $JAVA_HOME        # Linux/Mac
echo %JAVA_HOME%       # Windows CMD
$env:JAVA_HOME         # Windows PowerShell
```
期望输出示例：

```text
java version "17.0.9" 2023-10-17 LTS
Java(TM) SE Runtime Environment (build 17.0.9+11-LTS-201)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.9+11-LTS-201, mixed mode, sharing)
```

### 1.3 JDK 安装指南

#### 方式一：官方 Oracle JDK
```text
下载地址：https://www.oracle.com/java/technologies/downloads/

步骤：
1. 选择 JDK 17 或 JDK 21
2. 选择对应操作系统版本
3. 下载安装包
4. 运行安装程序
5. 配置环境变量
```

#### 方式二：OpenJDK（推荐）
```text
推荐发行版：
• Adoptium (Eclipse Temurin) - https://adoptium.net/
• Amazon Corretto - https://aws.amazon.com/corretto/
• Azul Zulu - https://www.azul.com/downloads/
• Microsoft OpenJDK - https://www.microsoft.com/openjdk
```

#### 方式三：使用 SDKMAN（Linux/Mac 推荐）
```shell
# 安装 SDKMAN
curl -s "https://get.sdkman.io" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"

# 查看可用的 JDK 版本
sdk list java

# 安装 JDK 17
sdk install java 17.0.9-tem

# 安装 JDK 21
sdk install java 21.0.1-tem

# 切换 JDK 版本
sdk use java 17.0.9-tem

# 设置默认版本
sdk default java 17.0.9-tem
```

#### 方式四：使用 Homebrew（Mac）
```shell
# 安装 JDK 17
brew install openjdk@17

# 配置环境变量（添加到 ~/.zshrc 或 ~/.bash_profile）
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH

# 使配置生效
source ~/.zshrc
```

#### 方式五：Windows 使用 Scoop
```shell
# 安装 Scoop（如果还没有）
Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
irm get.scoop.sh | iex

# 添加 Java bucket
scoop bucket add java

# 安装 JDK 17
scoop install temurin17-jdk

# 安装 JDK 21
scoop install temurin21-jdk
```

### 1.4 环境变量配置

#### Windows 配置
```text
1. 右键"此电脑" → 属性 → 高级系统设置 → 环境变量

2. 新建系统变量：
   变量名：JAVA_HOME
   变量值：C:\Program Files\Java\jdk-17（你的 JDK 安装路径）

3. 编辑 Path 变量，添加：
   %JAVA_HOME%\bin

4. 重新打开命令行验证
```

#### Linux/Mac 配置
```shell
# 编辑配置文件
vim ~/.bashrc    # 或 ~/.zshrc

# 添加以下内容
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk  # 根据实际路径修改
export PATH=$JAVA_HOME/bin:$PATH

# 使配置生效
source ~/.bashrc
```

### 1.5 验证安装成功
```shell
# 完整验证脚本
java -version && javac -version && echo "JAVA_HOME: $JAVA_HOME"
```

**检查清单：**

- [ ] java -version 显示 17 或更高版本
- [ ] javac -version 显示相同版本
- [ ] JAVA_HOME 环境变量已正确设置
- [ ] IDE（IntelliJ IDEA）能识别 JDK


## 二、Maven/Gradle 依赖配置

### 2.1 项目结构概览
```text
langchain4j-demo/
├── pom.xml                          # Maven 配置
├── build.gradle                     # Gradle 配置（二选一）
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/
│   │   │       └── demo/
│   │   │           └── Application.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
└── README.md
```

### 2.2 Maven 配置

#### 创建项目

##### 方式一：使用 Maven 命令
```shell
mvn archetype:generate \
  -DgroupId=com.example \
  -DartifactId=langchain4j-demo \
  -DarchetypeArtifactId=maven-archetype-quickstart \
  -DarchetypeVersion=1.4 \
  -DinteractiveMode=false
```

##### 方式二：使用 IntelliJ IDEA
```text
1. File → New → Project
2. 选择 Maven Archetype
3. 填写 GroupId: com.example
4. 填写 ArtifactId: langchain4j-demo
5. 选择 JDK 17
6. 点击 Create
```

##### 完整 pom.xml 配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>langchain4j-demo</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <name>LangChain4j Demo</name>
    <description>LangChain4j 学习示例项目</description>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        
        <!-- LangChain4j 版本 -->
        <langchain4j.version>0.36.2</langchain4j.version>
        
        <!-- 日志版本 -->
        <slf4j.version>2.0.9</slf4j.version>
        <logback.version>1.4.14</logback.version>
    </properties>

    <dependencies>
        
        <!-- ==================== LangChain4j 核心 ==================== -->
        
        <!-- LangChain4j 核心库 -->
        <dependency>
            <groupId>dev.langchain4j</groupId>
            <artifactId>langchain4j</artifactId>
            <version>${langchain4j.version}</version>
        </dependency>

        <!-- ==================== 模型提供商（按需选择）==================== -->
        
        <!-- OpenAI（包括 GPT-4, GPT-3.5）-->
        <dependency>
            <groupId>dev.langchain4j</groupId>
            <artifactId>langchain4j-open-ai</artifactId>
            <version>${langchain4j.version}</version>
        </dependency>

        <!-- 智谱 AI（GLM 系列）-->
        <dependency>
            <groupId>dev.langchain4j</groupId>
            <artifactId>langchain4j-zhipu-ai</artifactId>
            <version>${langchain4j.version}</version>
        </dependency>

        <!-- 阿里云通义千问 -->
        <dependency>
            <groupId>dev.langchain4j</groupId>
            <artifactId>langchain4j-dashscope</artifactId>
            <version>${langchain4j.version}</version>
        </dependency>

        <!-- Ollama（本地模型）-->
        <dependency>
            <groupId>dev.langchain4j</groupId>
            <artifactId>langchain4j-ollama</artifactId>
            <version>${langchain4j.version}</version>
        </dependency>

        <!-- ==================== 日志 ==================== -->
        
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>${logback.version}</version>
        </dependency>

        <!-- ==================== 测试 ==================== -->
        
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.1</version>
            <scope>test</scope>
        </dependency>

    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>17</source>
                    <target>17</target>
                </configuration>
            </plugin>
            
            <!-- 可执行 JAR -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.example.demo.Application</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

##### 按需选择依赖
```xml
<!-- 
    根据你选择的模型提供商，只需要添加对应的依赖即可。
    不需要全部添加，选择一个或几个即可。
-->

<!-- 方案 A：只用 OpenAI -->
<dependencies>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-open-ai</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>
</dependencies>

<!-- 方案 B：只用智谱 AI -->
<dependencies>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-zhipu-ai</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>
</dependencies>

<!-- 方案 C：只用 Ollama 本地模型（免费，无需 API Key）-->
<dependencies>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>
    <dependency>
        <groupId>dev.langchain4j</groupId>
        <artifactId>langchain4j-ollama</artifactId>
        <version>${langchain4j.version}</version>
    </dependency>
</dependencies>
```

### 2.3 Gradle 配置

#### build.gradle（Groovy DSL）
```groovy
plugins {
    id 'java'
    id 'application'
}

group = 'com.example'
version = '1.0-SNAPSHOT'

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

// LangChain4j 版本
def langchain4jVersion = '0.36.2'

dependencies {
    // LangChain4j 核心
    implementation "dev.langchain4j:langchain4j:${langchain4jVersion}"
    
    // 模型提供商（按需选择）
    implementation "dev.langchain4j:langchain4j-open-ai:${langchain4jVersion}"
    implementation "dev.langchain4j:langchain4j-zhipu-ai:${langchain4jVersion}"
    implementation "dev.langchain4j:langchain4j-dashscope:${langchain4jVersion}"
    implementation "dev.langchain4j:langchain4j-ollama:${langchain4jVersion}"
    
    // 日志
    implementation 'org.slf4j:slf4j-api:2.0.9'
    implementation 'ch.qos.logback:logback-classic:1.4.14'
    
    // 测试
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.1'
}

application {
    mainClass = 'com.example.demo.Application'
}

test {
    useJUnitPlatform()
}

tasks.withType(JavaCompile) {
    options.encoding = 'UTF-8'
}
```

#### build.gradle.kts（Kotlin DSL）
```Kotlin
plugins {
    java
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

val langchain4jVersion = "0.36.2"

dependencies {
    // LangChain4j 核心
    implementation("dev.langchain4j:langchain4j:$langchain4jVersion")
    
    // 模型提供商（按需选择）
    implementation("dev.langchain4j:langchain4j-open-ai:$langchain4jVersion")
    implementation("dev.langchain4j:langchain4j-zhipu-ai:$langchain4jVersion")
    implementation("dev.langchain4j:langchain4j-dashscope:$langchain4jVersion")
    implementation("dev.langchain4j:langchain4j-ollama:$langchain4jVersion")
    
    // 日志
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    
    // 测试
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
}

application {
    mainClass.set("com.example.demo.Application")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
```

### 2.4 验证依赖配置
```shell
# Maven
mvn dependency:tree
mvn compile

# Gradle
gradle dependencies
gradle build
```


## 三、选择大模型提供商

### 3.1 提供商对比总览

| 提供商 | 访问方式 | 价格 | 中文能力 | 推荐度 |
|:-------|:---------|:-----|:---------|:-------|
| **OpenAI** | 需代理 | 较贵 | 一般 | ⭐⭐⭐⭐ |
| **智谱 AI** | 国内直连 | 便宜 | 优秀 | ⭐⭐⭐⭐⭐ |
| **通义千问** | 国内直连 | 便宜 | 优秀 | ⭐⭐⭐⭐⭐ |
| **Ollama** | 本地运行 | 免费 | 取决于模型 | ⭐⭐⭐⭐ |
| **DeepSeek** | 国内直连 | 超便宜 | 优秀 | ⭐⭐⭐⭐⭐ |
### 3.2 OpenAI

#### 特点

| 项目 | 说明 |
|:-----|:-----|
| **官网** | https://platform.openai.com/ |
| **模型** | GPT-4o, GPT-4, GPT-3.5-Turbo |
| **优势** | 模型能力强，生态完善 |
| **劣势** | 国内需代理，价格较贵 |
| **计费** | 按 Token 计费 |
#### 获取 API Key

```text
1. 访问 https://platform.openai.com/
2. 注册/登录账号（需要海外手机号或使用接码平台）
3. 进入 API Keys 页面
4. 点击 "Create new secret key"
5. 复制保存 API Key（只显示一次）
```

#### 代码示例

```java
import dev.langchain4j.model.openai.OpenAiChatModel;

OpenAiChatModel model = OpenAiChatModel.builder()
    .apiKey("sk-xxxxxxxxxxxxxxxxxxxx")
    .modelName("gpt-4o-mini")  // 或 "gpt-4o", "gpt-3.5-turbo"
    .temperature(0.7)
    .build();

String response = model.generate("你好，请介绍一下你自己");
System.out.println(response);
```

### 3.3 智谱 AI（推荐国内用户）

#### 特点

| 项目 | 说明 |
|:-----|:-----|
| **官网** | https://open.bigmodel.cn/ |
| **模型** | GLM-4, GLM-4-Flash, GLM-3-Turbo |
| **优势** | 国内直连，中文能力强，有免费额度 |
| **劣势** | - |
| **计费** | 按 Token 计费，新用户赠送额度 |

#### 获取 API Key

```text
1. 访问 https://open.bigmodel.cn/
2. 注册账号（国内手机号即可）
3. 实名认证（可选，认证后额度更多）
4. 进入控制台 → API Keys
5. 创建 API Key
```

#### 代码示例

```java
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;

ZhipuAiChatModel model = ZhipuAiChatModel.builder()
        .apiKey("your-zhipu-api-key")
        .model("glm-4-flash")  // 或 "glm-4", "glm-3-turbo"
        .temperature(0.7)
        .build();

String response = model.generate("你好，请介绍一下你自己");
System.out.println(response);
```


### 3.4 阿里云通义千问（推荐国内用户）

#### 特点

| 项目 | 说明 |
|:-----|:-----|
| **官网** | https://dashscope.aliyun.com/ |
| **模型** | Qwen-Max, Qwen-Plus, Qwen-Turbo |
| **优势** | 阿里云背书，稳定可靠，中文能力强 |
| **劣势** | - |
| **计费** | 按 Token 计费，有免费额度 |

#### 获取 API Key

```text
1. 访问 https://dashscope.aliyun.com/
2. 使用阿里云账号登录
3. 开通 DashScope 服务
4. 进入 API-KEY 管理
5. 创建 API Key
```

#### 代码示例

```java
import dev.langchain4j.model.dashscope.QwenChatModel;

QwenChatModel model = QwenChatModel.builder()
        .apiKey("sk-xxxxxxxxxxxxxxxxxxxx")
        .modelName("qwen-turbo")  // 或 "qwen-plus", "qwen-max"
        .build();

String response = model.generate("你好，请介绍一下你自己");
System.out.println(response);
```

### 3.5 DeepSeek

#### 特点

| 项目 | 说明 |
|:-----|:-----|
| **官网** | https://platform.deepseek.com/ |
| **模型** | DeepSeek-Chat, DeepSeek-Coder |
| **优势** | 超级便宜，中文能力强，代码能力强 |
| **劣势** | - |
| **计费** | 极低价格 |

#### 获取 API Key

```text
1. 访问 https://platform.deepseek.com/
2. 注册账号
3. 进入 API Keys
4. 创建 API Key
```

#### 代码示例

```java
import dev.langchain4j.model.openai.OpenAiChatModel;

// DeepSeek 兼容 OpenAI API 格式
OpenAiChatModel model = OpenAiChatModel.builder()
        .baseUrl("https://api.deepseek.com")
        .apiKey("sk-xxxxxxxxxxxxxxxxxxxx")
        .modelName("deepseek-chat")
        .temperature(0.7)
        .build();

        String response = model.generate("你好，请介绍一下你自己");
System.out.println(response);
```


### 3.6 Ollama（本地模型，推荐学习使用）

#### 特点

| 项目 | 说明 |
|:-----|:-----|
| **官网** | https://ollama.ai/ |
| **模型** | Llama3, Qwen2, Mistral, Gemma 等 |
| **优势** | 完全免费，本地运行，数据隐私 |
| **劣势** | 需要较好的硬件，首次下载模型耗时 |
| **计费** | 免费 |

#### 安装 Ollama

##### Mac
```shell
# 使用 Homebrew
brew install ollama

# 或下载安装包
# https://ollama.ai/download
```
##### Linux
```shell
curl -fsSL https://ollama.ai/install.sh | sh
```

##### Windows
```text
下载安装包：https://ollama.ai/download
运行安装程序
```
#### 下载并运行模型

```shell
# 启动 Ollama 服务
ollama serve

# 下载并运行模型（新开一个终端）
ollama pull qwen2:7b           # 通义千问 7B（推荐，中文好）
ollama pull llama3:8b          # Llama3 8B
ollama pull mistral:7b         # Mistral 7B
ollama pull gemma2:9b          # Gemma2 9B

# 测试模型
ollama run qwen2:7b
>>> 你好，请介绍一下你自己
```

#### 代码示例

```java
import dev.langchain4j.model.ollama.OllamaChatModel;

OllamaChatModel model = OllamaChatModel.builder()
        .baseUrl("http://localhost:11434")
        .modelName("qwen2:7b")
        .temperature(0.7)
        .build();

String response = model.generate("你好，请介绍一下你自己");
System.out.println(response);
```

### 3.7 选择建议

```text
┌─────────────────────────────────────────────────────────────────────┐
│                         选择决策树                                   │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  你在国内吗？                                                        │
│       │                                                             │
│       ├── 是 ──→ 需要最强模型吗？                                    │
│       │              │                                              │
│       │              ├── 是 ──→ 智谱 / 通义千问    │
│       │              │                                              │
│       │              └── 否 ──→ 预算有限？                          │
│       │                            │                                │
│       │                            ├── 是 ──→ DeepSeek / Ollama 本地│
│       │                            │                                │
│       │                            └── 否 ──→ 智谱 GLM-4-Flash (免费)│
│       │                                                             │
│       └── 否 ──→ OpenAI GPT-4 / Claude                              │
│                                                                     │
│                                                                     │
│  【学习推荐】                                                        │
│  初学者：Ollama 本地 → 免费，无需配置代理                             │
│  进阶学习：智谱 AI → 国内直连，有免费额度                             │
│  生产环境：根据需求选择                                              │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```


## 四、API Key 配置与安全管理

### 4.1 为什么 API Key 安全很重要？

```text
┌─────────────────────────────────────────────────────────────────────┐
│                      API Key 泄露的后果                              │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  ⚠️  经济损失：他人使用你的 Key 产生费用，可能高达数千甚至数万元      │
│                                                                     │
│  ⚠️  账号封禁：异常使用可能导致账号被封                              │
│                                                                     │
│  ⚠️  数据泄露：攻击者可能获取你的对话历史                            │
│                                                                     │
│  ⚠️  法律风险：Key 被用于违法用途                                    │
│                                                                     │
│  ❌ 常见泄露场景：                                                   │
│     • 代码提交到 GitHub（最常见！）                                  │
│     • 配置文件上传到公开仓库                                         │
│     • 日志中打印 API Key                                            │
│     • 截图/录屏时暴露                                                │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

### 4.2 安全配置方案

#### 方案一：环境变量（推荐）

##### 设置环境变量
```shell
# Linux/Mac（添加到 ~/.bashrc 或 ~/.zshrc）
export OPENAI_API_KEY="sk-xxxxxxxxxxxxxxxxxxxx"
export ZHIPU_API_KEY="your-zhipu-api-key"
export DASHSCOPE_API_KEY="sk-xxxxxxxxxxxxxxxxxxxx"

# 使配置生效
source ~/.bashrc

# Windows CMD
set OPENAI_API_KEY=sk-xxxxxxxxxxxxxxxxxxxx

# Windows PowerShell
$env:OPENAI_API_KEY="sk-xxxxxxxxxxxxxxxxxxxx"

# Windows 永久设置（系统环境变量）
setx OPENAI_API_KEY "sk-xxxxxxxxxxxxxxxxxxxx"
```

##### 在代码中读取
```java
public class ApiKeyConfig {
    
    // 从环境变量读取
    public static String getOpenAiApiKey() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                "请设置环境变量 OPENAI_API_KEY"
            );
        }
        return apiKey;
    }
    
    public static String getZhipuApiKey() {
        String apiKey = System.getenv("ZHIPU_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                "请设置环境变量 ZHIPU_API_KEY"
            );
        }
        return apiKey;
    }
}

// 使用
OpenAiChatModel model = OpenAiChatModel.builder()
    .apiKey(ApiKeyConfig.getOpenAiApiKey())
    .build();
```

#### 方案二：配置文件 + .gitignore

##### 创建本地配置文件
```properties
# src/main/resources/application-local.properties
# ⚠️ 此文件不要提交到 Git！

openai.api.key=sk-xxxxxxxxxxxxxxxxxxxx
zhipu.api.key=your-zhipu-api-key
dashscope.api.key=sk-xxxxxxxxxxxxxxxxxxxx
```
##### 添加到 .gitignore
```gitignore
# .gitignore

# API Key 配置文件
application-local.properties
application-local.yml
.env
*.env
secrets.properties

# IDE 配置
.idea/
*.iml
.vscode/

# 编译输出
target/
build/
out/
```

##### 读取配置
```java
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    
    private static Properties properties;
    
    static {
        properties = new Properties();
        try (InputStream is = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("application-local.properties")) {
            if (is != null) {
                properties.load(is);
            }
        } catch (Exception e) {
            System.err.println("无法加载配置文件: " + e.getMessage());
        }
    }
    
    public static String getApiKey(String key) {
        // 优先从环境变量读取
        String envValue = System.getenv(key.toUpperCase().replace(".", "_"));
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }
        // 其次从配置文件读取
        return properties.getProperty(key);
    }
}
```

#### 方案三：使用 .env 文件（类似 Node.js）

##### 创建 .env 文件
```env
# .env 文件（项目根目录）
OPENAI_API_KEY=sk-xxxxxxxxxxxxxxxxxxxx
ZHIPU_API_KEY=your-zhipu-api-key
DASHSCOPE_API_KEY=sk-xxxxxxxxxxxxxxxxxxxx
```
##### 添加依赖
```xml
<dependency>
    <groupId>io.github.cdimascio</groupId>
    <artifactId>dotenv-java</artifactId>
    <version>3.0.0</version>
</dependency>
```
##### 读取 .env
```java
import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    
    private static final Dotenv dotenv = Dotenv.configure()
        .ignoreIfMissing()
        .load();
    
    public static String get(String key) {
        // 优先环境变量，其次 .env 文件
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            value = dotenv.get(key);
        }
        return value;
    }
    
    public static String getOpenAiApiKey() {
        return get("OPENAI_API_KEY");
    }
    
    public static String getZhipuApiKey() {
        return get("ZHIPU_API_KEY");
    }
}
```

### 4.3 安全检查清单

```text
┌─────────────────────────────────────────────────────────────────────┐
│                        安全检查清单                                  │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  提交代码前检查：                                                    │
│  □ 配置文件中没有硬编码的 API Key                                    │
│  □ .gitignore 已包含敏感文件                                        │
│  □ 运行 git diff 检查即将提交的内容                                  │
│  □ 代码中没有打印 API Key 的日志语句                                 │
│                                                                     │
│  定期安全维护：                                                      │
│  □ 定期轮换 API Key                                                 │
│  □ 检查 API 使用量是否异常                                          │
│  □ 设置使用量告警                                                   │
│  □ 使用 API Key 最小权限原则                                        │
│                                                                     │
│  如果 Key 已泄露：                                                   │
│  □ 立即到提供商后台删除/禁用该 Key                                   │
│  □ 创建新的 API Key                                                 │
│  □ 更新所有使用该 Key 的地方                                        │
│  □ 检查账单，确认损失范围                                           │
│  □ 使用 git filter-branch 或 BFG 清理 Git 历史                      │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

### 4.4 Git 历史清理（如果已经提交了 API Key）

```shell
# 使用 BFG Repo-Cleaner（推荐）
# 下载：https://rtyley.github.io/bfg-repo-cleaner/

# 1. 克隆裸仓库
git clone --mirror git@github.com:yourname/yourrepo.git

# 2. 运行 BFG 删除敏感信息
java -jar bfg.jar --replace-text passwords.txt yourrepo.git

# 3. 清理并推送
cd yourrepo.git
git reflog expire --expire=now --all
git gc --prune=now --aggressive
git push
```


## 五、Hello World：第一次调用大模型

### 5.1 项目结构
```text
langchain4j-demo/
├── pom.xml
├── .env                              # API Key 配置（不提交到 Git）
├── .gitignore
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── demo/
│       │               ├── Application.java
│       │               ├── config/
│       │               │   └── ApiKeyConfig.java
│       │               └── examples/
│       │                   ├── OpenAiExample.java
│       │                   ├── ZhipuExample.java
│       │                   ├── DashScopeExample.java
│       │                   └── OllamaExample.java
│       └── resources/
│           └── logback.xml
└── README.md
```

### 5.2 日志配置
```xml
<!-- src/main/resources/logback.xml -->
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <!-- LangChain4j 日志级别 -->
    <logger name="dev.langchain4j" level="DEBUG"/>
    
    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
    </root>
    
</configuration>
```

### 5.3 API Key 配置类
```java
package com.example.demo.config;

/**
 * API Key 配置管理
 * 优先从环境变量读取，保证安全性
 */
public class ApiKeyConfig {
    
    /**
     * 获取 OpenAI API Key
     */
    public static String getOpenAiApiKey() {
        return getRequiredEnv("OPENAI_API_KEY");
    }
    
    /**
     * 获取智谱 AI API Key
     */
    public static String getZhipuApiKey() {
        return getRequiredEnv("ZHIPU_API_KEY");
    }
    
    /**
     * 获取阿里云 DashScope API Key
     */
    public static String getDashScopeApiKey() {
        return getRequiredEnv("DASHSCOPE_API_KEY");
    }
    
    /**
     * 获取 DeepSeek API Key
     */
    public static String getDeepSeekApiKey() {
        return getRequiredEnv("DEEPSEEK_API_KEY");
    }
    
    /**
     * 获取必需的环境变量
     */
    private static String getRequiredEnv(String name) {
        String value = System.getenv(name);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                String.format("环境变量 %s 未设置。请设置后重试。", name)
            );
        }
        return value;
    }
    
    /**
     * 获取可选的环境变量
     */
    public static String getOptionalEnv(String name, String defaultValue) {
        String value = System.getenv(name);
        return (value == null || value.isBlank()) ? defaultValue : value;
    }
}
```

### 5.4 OpenAI 示例
```java
package com.example.demo.examples;

import com.example.demo.config.ApiKeyConfig;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * OpenAI GPT 模型示例
 */
public class OpenAiExample {
    
    public static void main(String[] args) {
        
        System.out.println("=== OpenAI GPT 示例 ===\n");
        
        // 1. 创建模型实例
        OpenAiChatModel model = OpenAiChatModel.builder()
            .apiKey(ApiKeyConfig.getOpenAiApiKey())
            .modelName("gpt-4o-mini")  // 可选: gpt-4o, gpt-4, gpt-3.5-turbo
            .temperature(0.7)          // 温度：0-1，越高越随机
            .maxTokens(500)            // 最大生成 token 数
            .build();
        
        // 2. 发送请求
        String prompt = "你好！请用一句话介绍一下你自己。";
        System.out.println("用户: " + prompt);
        
        String response = model.generate(prompt);
        System.out.println("AI: " + response);
        
        System.out.println("\n--- 测试更多问题 ---\n");
        
        // 3. 更多测试
        String[] questions = {
            "Java 和 Python 有什么区别？",
            "写一个简单的冒泡排序算法",
            "用一首诗描述春天"
        };
        
        for (String question : questions) {
            System.out.println("用户: " + question);
            System.out.println("AI: " + model.generate(question));
            System.out.println();
        }
    }
}
```

### 5.5 智谱 AI 示例
```java
package com.example.demo.examples;

import com.example.demo.config.ApiKeyConfig;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;

/**
 * 智谱 AI GLM 模型示例
 */
public class ZhipuExample {
    
    public static void main(String[] args) {
        
        System.out.println("=== 智谱 AI GLM 示例 ===\n");
        
        // 1. 创建模型实例
        ZhipuAiChatModel model = ZhipuAiChatModel.builder()
            .apiKey(ApiKeyConfig.getZhipuApiKey())
            .model("glm-4-flash")      // 可选: glm-4, glm-4-air, glm-3-turbo
            .temperature(0.7)
            .maxToken(500)
            .build();
        
        // 2. 发送请求
        String prompt = "你好！请用一句话介绍一下你自己。";
        System.out.println("用户: " + prompt);
        
        String response = model.generate(prompt);
        System.out.println("AI: " + response);
        
        System.out.println("\n--- 测试中文能力 ---\n");
        
        // 3. 中文测试
        String[] questions = {
            "请用成语接龙：一马当先",
            "翻译成英文：春眠不觉晓，处处闻啼鸟",
            "解释一下什么是 LangChain"
        };
        
        for (String question : questions) {
            System.out.println("用户: " + question);
            System.out.println("AI: " + model.generate(question));
            System.out.println();
        }
    }
}
```

### 5.6 通义千问示例
```java
package com.example.demo.examples;

import com.example.demo.config.ApiKeyConfig;
import dev.langchain4j.model.dashscope.QwenChatModel;

/**
 * 阿里云通义千问模型示例
 */
public class DashScopeExample {
    
    public static void main(String[] args) {
        
        System.out.println("=== 通义千问示例 ===\n");
        
        // 1. 创建模型实例
        QwenChatModel model = QwenChatModel.builder()
            .apiKey(ApiKeyConfig.getDashScopeApiKey())
            .modelName("qwen-turbo")   // 可选: qwen-plus, qwen-max
            .build();
        
        // 2. 发送请求
        String prompt = "你好！请用一句话介绍一下你自己。";
        System.out.println("用户: " + prompt);
        
        String response = model.generate(prompt);
        System.out.println("AI: " + response);
        
        System.out.println("\n--- 测试更多问题 ---\n");
        
        // 3. 更多测试
        String question = "请解释一下什么是大语言模型，以及它的工作原理。";
        System.out.println("用户: " + question);
        System.out.println("AI: " + model.generate(question));
    }
}
```

### 5.7 DeepSeek 示例（使用 OpenAI 兼容接口）
```java
package com.example.demo.examples;

import com.example.demo.config.ApiKeyConfig;
import dev.langchain4j.model.openai.OpenAiChatModel;

/**
 * DeepSeek 模型示例
 * DeepSeek 兼容 OpenAI API 格式
 */
public class DeepSeekExample {
    
    public static void main(String[] args) {
        
        System.out.println("=== DeepSeek 示例 ===\n");
        
        // 1. 创建模型实例（使用 OpenAI 兼容接口）
        OpenAiChatModel model = OpenAiChatModel.builder()
            .baseUrl("https://api.deepseek.com")
            .apiKey(ApiKeyConfig.getDeepSeekApiKey())
            .modelName("deepseek-chat")
            .temperature(0.7)
            .maxTokens(500)
            .build();
        
        // 2. 发送请求
        String prompt = "你好！请用一句话介绍一下你自己。";
        System.out.println("用户: " + prompt);
        
        String response = model.generate(prompt);
        System.out.println("AI: " + response);
        
        System.out.println("\n--- 测试代码能力 ---\n");
        
        // 3. 测试代码生成（DeepSeek 擅长代码）
        String codeQuestion = "用 Java 实现一个单例模式，包含双重检查锁定";
        System.out.println("用户: " + codeQuestion);
        System.out.println("AI: " + model.generate(codeQuestion));
    }
}
```

### 5.8 Ollama 本地模型示例
```java
package com.example.demo.examples;

import dev.langchain4j.model.ollama.OllamaChatModel;

/**
 * Ollama 本地模型示例
 * 
 * 前置条件：
 * 1. 安装 Ollama: https://ollama.ai/
 * 2. 启动服务: ollama serve
 * 3. 下载模型: ollama pull qwen2:7b
 */
public class OllamaExample {
    
    public static void main(String[] args) {
        
        System.out.println("=== Ollama 本地模型示例 ===\n");
        
        // 1. 创建模型实例
        OllamaChatModel model = OllamaChatModel.builder()
            .baseUrl("http://localhost:11434")  // Ollama 默认地址
            .modelName("qwen2:7b")              // 使用的模型名称
            .temperature(0.7)
            .build();
        
        // 2. 发送请求
        String prompt = "你好！请用一句话介绍一下你自己。";
        System.out.println("用户: " + prompt);
        
        try {
            String response = model.generate(prompt);
            System.out.println("AI: " + response);
        } catch (Exception e) {
            System.err.println("错误: " + e.getMessage());
            System.err.println("\n请确保：");
            System.err.println("1. Ollama 已安装并运行 (ollama serve)");
            System.err.println("2. 已下载模型 (ollama pull qwen2:7b)");
        }
        
        System.out.println("\n--- 测试更多问题 ---\n");
        
        // 3. 更多测试
        String[] questions = {
            "什么是 LangChain4j？",
            "用 Java 写一个 Hello World",
            "今天天气怎么样？"  // 测试模型对无法回答问题的处理
        };
        
        for (String question : questions) {
            System.out.println("用户: " + question);
            try {
                System.out.println("AI: " + model.generate(question));
            } catch (Exception e) {
                System.err.println("错误: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
```

### 5.9 主程序入口（统一演示）
```java
package com.example.demo;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.zhipu.ZhipuAiChatModel;

import java.util.Scanner;

/**
 * LangChain4j 入门演示程序
 */
public class Application {
    
    public static void main(String[] args) {
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     欢迎使用 LangChain4j 学习项目       ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        
        // 选择模型提供商
        ChatLanguageModel model = selectModel();
        
        if (model == null) {
            System.out.println("未选择模型，程序退出。");
            return;
        }
        
        // 交互式对话
        interactiveChat(model);
    }
    
    /**
     * 选择模型提供商
     */
    private static ChatLanguageModel selectModel() {
        
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
    private static ChatLanguageModel createOllamaModel() {
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
    private static ChatLanguageModel createZhipuModel() {
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
    private static ChatLanguageModel createOpenAiModel() {
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
    private static ChatLanguageModel createDeepSeekModel() {
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
    private static void interactiveChat(ChatLanguageModel model) {
        
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
                String response = model.generate(input);
                System.out.println(response);
            } catch (Exception e) {
                System.err.println("生成回复时出错: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}
```

### 5.10 运行程序

#### 方式一：使用 IDE

```text
1. 用 IntelliJ IDEA 打开项目
2. 右键点击 Application.java
3. 选择 "Run 'Application.main()'"
```

#### 方式二：使用 Maven

```shell
# 编译
mvn clean compile

# 运行
mvn exec:java -Dexec.mainClass="com.example.demo.Application"

# 或者运行特定示例
mvn exec:java -Dexec.mainClass="com.example.demo.examples.OllamaExample"
```

#### 方式三：使用 Gradle

```shell
# 编译并运行
gradle run

# 或者
gradle build
java -jar build/libs/langchain4j-demo-1.0-SNAPSHOT.jar
```

### 5.11 预期输出

```text
╔════════════════════════════════════════╗
║     欢迎使用 LangChain4j 学习项目       ║
╚════════════════════════════════════════╝

请选择模型提供商：
1. Ollama（本地模型，免费）
2. 智谱 AI（国内直连）
3. OpenAI
4. DeepSeek
0. 退出

请输入选项: 1

正在连接 Ollama...

连接成功！开始对话吧（输入 'quit' 退出）

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

你: 你好

AI: 你好！我是一个人工智能助手，很高兴见到你！有什么我可以帮助你的吗？

你: 什么是 LangChain4j？

AI: LangChain4j 是一个 Java 库，旨在简化将大语言模型（LLM）集成到 Java 应用程序中的过程。
它提供了统一的 API 来访问不同的 LLM 提供商，并提供了诸如对话记忆、RAG（检索增强生成）、
工具调用等高级功能。它的设计灵感来自 Python 的 LangChain 框架。

你: quit

再见！👋
```

## 六、常见问题排查

### 6.1 问题诊断清单

```text
┌─────────────────────────────────────────────────────────────────────┐
│                         问题诊断清单                                 │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  编译错误：                                                          │
│  □ JDK 版本是否正确？(java -version)                                 │
│  □ Maven/Gradle 是否正确配置？                                       │
│  □ 依赖是否下载完成？                                                │
│  □ IDE 是否识别了项目？                                              │
│                                                                     │
│  运行错误：                                                          │
│  □ 环境变量是否设置？                                                │
│  □ API Key 是否正确？                                                │
│  □ 网络是否通畅？                                                    │
│  □ Ollama 服务是否启动？                                             │
│                                                                     │
│  超时错误：                                                          │
│  □ 网络连接是否稳定？                                                │
│  □ 是否需要代理？                                                    │
│  □ API 是否过载？                                                    │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

### 6.2 常见错误及解决方案

#### 错误 1：找不到类或方法

```text
错误信息：
java.lang.ClassNotFoundException: dev.langchain4j.model.openai.OpenAiChatModel

解决方案：
1. 确认 pom.xml 中添加了正确的依赖
2. 运行 mvn clean install
3. 刷新 IDE 的 Maven 项目
```

#### 错误 2：API Key 无效

```text
错误信息：
Incorrect API key provided

解决方案：
1. 确认环境变量已正确设置
2. 确认 API Key 没有多余的空格
3. 确认 API Key 没有过期
4. 确认账户余额充足
```

#### 错误 3：连接 Ollama 失败

```text
错误信息：
Connection refused: localhost/127.0.0.1:11434

解决方案：
1. 确认 Ollama 已安装: ollama --version
2. 启动 Ollama 服务: ollama serve
3. 确认端口未被占用: lsof -i :11434
4. 确认已下载模型: ollama list
```

#### 错误 4：网络超时

```text
错误信息：
java.net.SocketTimeoutException: Read timed out

解决方案：
1. 检查网络连接
2. 如果使用 OpenAI，确认代理设置
3. 增加超时时间：
   .timeout(Duration.ofSeconds(60))
4. 尝试使用国内模型提供商
```

#### 错误 5：中文乱码

```text
错误信息：
控制台输出乱码

解决方案：
1. 设置 JVM 参数: -Dfile.encoding=UTF-8
2. IDE 设置文件编码为 UTF-8
3. 控制台设置编码为 UTF-8
```



## 七、今日总结

### 7.1 知识点回顾

```text
┌─────────────────────────────────────────────────────────────────────┐
│                        Day 2 知识点                                  │
├─────────────────────────────────────────────────────────────────────┤
│                                                                     │
│  ✅ JDK 17+ 环境配置                                                 │
│     • 推荐使用 SDKMAN 管理多版本                                     │
│     • 确认 JAVA_HOME 环境变量                                        │
│                                                                     │
│  ✅ Maven/Gradle 依赖配置                                            │
│     • 核心依赖：langchain4j                                          │
│     • 模型依赖：langchain4j-{provider}                               │
│     • 按需选择，不需要全部添加                                        │
│                                                                     │
│  ✅ 模型提供商选择                                                   │
│     • 国内推荐：智谱 AI、通义千问、DeepSeek                           │
│     • 学习推荐：Ollama 本地模型（免费）                               │
│     • 国际：OpenAI（需代理）                                         │
│                                                                     │
│  ✅ API Key 安全管理                                                 │
│     • 使用环境变量存储                                               │
│     • 配置 .gitignore                                                │
│     • 不要硬编码在代码中                                             │
│                                                                     │
│  ✅ Hello World 程序                                                 │
│     • ChatLanguageModel 接口                                         │
│     • generate() 方法调用                                            │
│     • 不同提供商的构建方式                                           │
│                                                                     │
└─────────────────────────────────────────────────────────────────────┘
```

### 7.2 代码速记卡

```java
// 1. OpenAI
OpenAiChatModel.builder()
    .apiKey("...")
    .modelName("gpt-4o-mini")
    .build();

// 2. 智谱 AI
ZhipuAiChatModel.builder()
    .apiKey("...")
    .model("glm-4-flash")
    .build();

// 3. 通义千问
QwenChatModel.builder()
    .apiKey("...")
    .modelName("qwen-turbo")
    .build();

// 4. Ollama
OllamaChatModel.builder()
    .baseUrl("http://localhost:11434")
    .modelName("qwen2:7b")
    .build();

// 5. 调用
String response = model.generate("你好");
```

## 八、课后任务

### 8.1 必做任务

**任务 1：搭建开发环境**

- 确认 JDK 17+ 安装成功
- 创建 Maven 或 Gradle 项目
- 添加 LangChain4j 依赖

**任务 2：选择并配置模型提供商**

- 至少选择一个提供商
- 正确配置 API Key（使用环境变量）
- 确认 .gitignore 已配置

**任务 3：运行 Hello World**

- 成功调用大模型
- 获得 AI 的回复
- 截图记录成功运行的结果

### 8.2 选做任务

**任务 4：尝试多个提供商**

- 对比不同模型的回复质量
- 对比响应速度
- 记录使用体验

**任务 5：安装 Ollama 本地模型**

- 安装 Ollama
- 下载 qwen2:7b 模型
- 用代码成功调用

### 8.3 思考问题

- 不同模型提供商的回复有什么差异？
- 本地模型和云端模型各有什么优缺点？
- 如何在生产环境中安全管理 API Key？


## 📝 学习笔记区

```text
今日学习时间：______ 分钟

完成的任务：
□ 环境搭建
□ 依赖配置
□ API Key 设置
□ Hello World 运行成功

选择的模型提供商：__________________

遇到的问题：
1. 
2. 

解决方案：
1. 
2. 

模型回复效果评价：

```

## 🔜 明日预告

**Day 3：核心概念 - ChatLanguageModel**

- ChatLanguageModel 接口详解
- 同步调用 vs 流式调用
- UserMessage / AiMessage / SystemMessage 消息类型
- 模型参数配置详解
- 不同模型提供商的实现差异



> 💡 **提示**：今天的重点是成功运行第一个程序。如果遇到问题，不要急躁，按照排查清单逐一检查。成功看到 AI 的回复是一个重要的里程碑！