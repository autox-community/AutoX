# Console

> 稳定性: 稳定

控制台模块提供了一个和Web浏览器中相似的用于调试的控制台。用于输出一些调试信息、中间结果等。
console模块中的一些函数也可以直接作为全局函数使用，例如log, print等。
## console.show(autoHide)
* `autoHide` {boolean} 是否自动隐藏，默认false当程序结束的时候是否自动隐藏控制
显示控制台。这会显示一个控制台的悬浮窗(需要悬浮窗权限)。

```js
console.show(true); //程序结束自动 隐藏控制台
console.show();   //结束不会自动隐藏控制台
```
## console.hide()

隐藏控制台悬浮窗。

## console.clear()

清空控制台。

## console.log([data][, ...args])
* `data` {any}
* `...args` {any}

打印到控制台，并带上换行符。 可以传入多个参数，第一个参数作为主要信息，其他参数作为类似于 [printf(3)](http://man7.org/linux/man-pages/man3/printf.3.html) 中的代替值（参数都会传给 util.format()）。

```js
const count = 5;
console.log('count: %d', count);
// 打印: count: 5 到 stdout
console.log('count:', count);
// 打印: count: 5 到 stdout
```

详见 util.format()。

该函数也可以作为全局函数使用。

## console.verbose([data][, ...args])
* `data` {any}
* `...args` {any}

与console.log类似，但输出结果以灰色字体显示。输出优先级低于log，用于输出观察性质的信息。

## console.info([data][, ...args])
* `data` {any}
* `...args` {any}

与console.log类似，但输出结果以绿色字体显示。输出优先级高于log, 用于输出重要信息。

## console.warn([data][, ...args])
* `data` {any}
* `...args` {any}

与console.log类似，但输出结果以蓝色字体显示。输出优先级高于info, 用于输出警告信息。

## console.error([data][, ...args])
* `data` {any}
* `...args` {any}

与console.log类似，但输出结果以红色字体显示。输出优先级高于warn, 用于输出错误信息。

## console.assert(value, message)
* `value` {any} 要断言的布尔值
* `message` {string} value为false时要输出的信息

断言。如果value为false则输出错误信息message并停止脚本运行。

```js
var a = 1 + 1;
console.assert(a == 3, "加法出错啦");
```
## console.time([label])
**[v4.1.0新增]**
* `label` {String} 计时器标签，可省略

启动一个计时器，用以计算一个操作的持续时间。
计时器由一个唯一的 `label` 标识。
若`label`重复，则会覆盖上一个同名`label`的计时器。
以同名 `label`调用 `console.timeEnd()` 来停止计时器，并以毫秒为单位将持续时间输出到控制台。

## console.timeEnd(label)
**[v4.1.0新增]**
* `label` {String} 计时器标签

停止之前通过调用 `console.time()` 启动的定时器，并打印结果到控制台。
调用 `console.timeEnd()` 后定时器会被删除。如果不存在标签指定的定时器则会打印 `NaNms`。
```js
console.time('求和');
var sum = 0;
for(let i = 0; i < 100000; i++){
    sum += i;
}
console.timeEnd('求和');
// 打印 求和: xxx ms
```

## console.trace([data][, ...args])
**[v4.1.0新增]**
* `data` {any}
* `...args` {any}

与console.log类似，同时会打印出调用这个函数所在的调用栈信息（即当前运行的文件、行数等信息）。

```js
console.trace('Show me');
// 打印: (堆栈跟踪会根据被调用的跟踪的位置而变化)
// Show me
//  at <test>:7
```

## console.input(data[, ...args])
* `data` {any}
* `...args` {any}

与`console.log`一样输出信息，并在控制台显示输入框等待输入。按控制台的确认按钮后会将输入的字符串用`eval`计算后返回。

**部分机型可能会有控制台不显示输入框的情况，属于bug。**

例如：
```js
var n = console.input("请输入一个数字:"); 
//输入123之后：
toast(n + 1);
//显示124
```

## console.rawInput(data[, ...args])
* `data` {any}
* `...args` {any}

与console.log一样输出信息，并在控制台显示输入框等待输入。按控制台的确认按钮后会将输入的字符串直接返回。

部分机型可能会有控制台不显示输入框的情况，属于bug。

例如：
```js
var n = console.rawInput("请输入一个数字:"); 
//输入123之后：
toast(n + 1);
//显示1231
```

## console.setSize(w, h)
* `w` {number} 宽度
* `h` {number} 高度

设置控制台的大小，单位像素。
```js
console.show();
//设置控制台大小为屏幕的四分之一
console.setSize(device.width / 2, device.height / 2);
```

## console.setPosition(x, y)
* `x` {number} 横坐标
* `y` {number} 纵坐标

设置控制台的位置，单位像素。

```js
console.show();
console.setPosition(100, 100);
```

## console.setGlobalLogConfig(config)
**[v4.1.0新增]**
* `config` {Object} 日志配置，可选的项有：
    * `file` {string} 日志文件路径，将会把日志写入该文件中
    * `maxFileSize` {number} 最大文件大小，单位字节，默认为512 * 1024 (512KB)
    * `rootLevel` {string} 写入的日志级别，默认为"ALL"（所有日志），可以为"OFF"(关闭), "DEBUG", "INFO", "WARN", "ERROR", "FATAL"等。
    * `maxBackupSize` {number} 日志备份文件最大数量，默认为5
    * `filePattern` {string} 日志写入格式，参见[PatternLayout](http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PatternLayout.html)

设置日志保存的路径和配置。例如把日志保存到"/sdcard/1.txt":
```js
console.setGlobalLogConfig({
    "file": "/sdcard/1.txt"
});
```


## print(text)
* text {string} | {Object} 要打印到控制台的信息

相当于`log(text)`。


## console.setTitle(title,color,size)
**[v4.2.5新增]**
* `title`  {string} 标题
* `color`  {string} 颜色值 #AARRGGBB
* `size`  {number}  标题高度，字号会随高度变化，单位是dp  

设置标题名称，字体颜色，标题栏高度


```js
console.setTitle("中文","#ff11ee00",30);
console.setTitle("中文");
console.setTitle("中文","#ff11ee00");
  
```

## console.setLogSize(size)
**[v4.2.5新增]**
* `size`  {number}  字号大小，单位是dp或sp 20以内比较合适  
设置log字号大小

**需要在显示控制台之后才能设置，否则空指针**

```js

function myrandom(min,max){
    return Math.floor(Math.random() * (max - min + 1) ) + min;
}

threads.start(function () {
    console.show();
    console.setTitle("中文","#ff11ee00",30);
    console.setCanInput(false);
    var i=0;    
    do {
        console.setLogSize(myrandom(4,20) );
        console.setCanInput(i%2==0);
        i++;
        console.log("i----->"+i);
        sleep(3000);
    } while (true);

}); 

  
```


## console.setCanInput(can)
**[v4.2.5新增]**
* `can`  {boolean}  true 或 false 可以输入或不可以输入

控制 console 是否可以输入文字 


```js
console.setCanInput(false);
```

## console.setBackgroud(color)
**[v4.2.5新增]**
* `color`  {string} 颜色值 #AARRGGBB

设置 console 背景色,**需要在显示控制台之后才能设置，否则空指针**

```js
console.setBackgroud("#33ef0000");
```

## console.setMaxLines(maxLines);
**[v5.0.2新增]**
* `maxLines`  {number}  最大行数 如 10 行
设置 console 显示最大行数，默认-1，不限 ，超出行数系统会清空，从0开始显示
不限制，显示列表过长，android内存又不足，系统会回收console的引用，即console 将不显示。

```js
console.setMaxLines(500);  
```
## console.setBackground()
