# AutoX

[📄 更新日志](CHANGELOG.md)

只是做一个原版备份, 后续没有重大更新, 日常使用基本没啥问题, 

重度使用, 建议使用其他开发者经常更新的版本

## 开发指南

**Android Studio 版本:**

2023.3.1 Patch 2

**国内镜像加速下载依赖项**

创建 `C:\Users\{你的用户名}\.gradle\init.gradle`

```groovy
def repoConfig = {  
    maven { url 'https://maven.aliyun.com/repository/central' }
    maven { url 'https://maven.aliyun.com/repository/public' }
    maven { url 'https://maven.aliyun.com/repository/google' }
    maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
    maven { url 'https://maven.aliyun.com/repository/releases' }
    // maven { url 'https://maven.aliyun.com/repository/jcenter' }
    maven { url "https://jitpack.io"}
    
    google()
    jcenter()
    mavenLocal()
    mavenCentral()    
}
allprojects {
    buildscript {
        repositories repoConfig
    }
    repositories repoConfig
}
```

## 说明

最后的原版在分支 "old/v6.6.8"

最后一个 "可用原版" 在分支 "old/v6.5.8"

已发现的 bug :

- 659 引入 "系统不兼容" 的 bug, 导致无法安装 apk
  - 需要将打包时的签名功能整体迁移到 "com.github.TimScriptov:apksigner"
- 661 打开闪退 (也是 bug, 没有去研究, 不知道什么原因引起的)
- 661 引入打包时 map 的 bug, 导致打包后的 apk 中没有 so 文件
  - 需要将 [此处代码](https://github.com/autox-community/AutoX/blob/41a608bcebdc7a3d0327352ab7862bb00fb0e88d/common/src/main/java/com/stardust/io/Zip.kt#L19) 的 map 改为 forEach

658 之后 一直在大改, 改完也不测试, 全是 bug

所以这个仓库主分支从 658 开始

