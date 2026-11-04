# Create: Reactive Stress

根据机器的工作状态调整 Create 的应力计算。

## 开发环境

- Java 21（`JAVA_HOME` 指向 JDK 21）
- Minecraft 1.21.1
- NeoForge 21.1.244
- Create 6.0.10
- Gradle 8.14.3（使用仓库内的 Wrapper，无需单独安装 Gradle）

版本和模组信息统一配置在 `gradle.properties`。

## 构建和运行

在项目根目录执行：

```powershell
.\gradlew.bat build
.\gradlew.bat runClient
```

Linux / macOS 使用 `bash ./gradlew build` 和 `bash ./gradlew runClient`。

首次执行会下载 Gradle、Minecraft 和模组依赖。客户端会自动加载本项目及 Create 的依赖，运行目录为 `run/`，日志位于 `run/logs/latest.log`。构建产物位于 `build/libs/`。

也可使用 `runServer` 启动开发服务端、使用 `runData` 生成数据。项目目前没有自动化测试；`build` 会完成编译、资源处理和打包。
