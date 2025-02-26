# 压缩与解压

## 压缩

```js

//压缩文件路径(必须是完整路径)
var filePath = "/sdcard/脚本.7z";
//目录路径(必须是完整路径)
var dirPath = "/sdcard/脚本";
//压缩类型
//支持的压缩类型包括:
//  zip 7z bz2 bzip2 tbz2 tbz gz gzip tgz tar wim swm xz txz。
var type = "7z";
//压缩密码
var password = "password"

//7z加密压缩(若文件已存在则跳过)
//zips.A(type, filePath, dirPath, password)

//压缩
switch (zips.A(type, filePath, dirPath)) {
    case 0:
        toastLog("压缩成功！文件已保存为： " + filePath)
        break;
    case 1:
        toastLog("压缩结束，存在非致命错误（例如某些文件正在被使用，没有被压缩）")
        break;
    case 2:
        toastLog("致命错误")
        break;
    case 7:
        toastLog("命令行错误")
        break;
    case 8:
        toastLog("没有足够内存")
        break;
    case 255:
        toastLog("用户中止操作")
        break;
    default: toastLog("未知错误")
}

```


## 解压

```js
//压缩文件路径(必须是完整路径)
var filePath = files.path("./bonus.rar");
//目录路径(必须是完整路径)
var dirPath = "/sdcard/脚本";
//压缩密码
var password = "password"

//支持的解压缩类型包括：
// zip、7z、bz2、bzip2、tbz2、tbz、gz、gzip、tgz、tar、
// wim、swm、xz、txz以及rar、chm、iso、msi等众多格式。

//解压无加密的压缩包(若文件已存在则跳过)
//zips.X(filePath, dirPath)

//解压加密的压缩包(若文件已存在则跳过)
switch (zips.X(filePath, dirPath, password)) {
    case 0:
        toastLog("解压缩成功！请到 " + dirPath + " 目录下查看。")
        break;
    case 1:
        toastLog("压缩结束，存在非致命错误（例如某些文件正在被使用，没有被压缩）")
        break;
    case 2:
        toastLog("致命错误")
        break;
    case 7:
        toastLog("命令行错误")
        break;
    case 8:
        toastLog("没有足够内存")
        break;
    case 255:
        toastLog("用户中止操作")
        break;
    default: toastLog("未知错误")
}

```