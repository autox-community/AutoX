# rxjs
v6.5.5新增
> <font color="#FF34FF17">稳定性: 稳定</font>

RxJS 是一个通过使用可观察序列来编写<font color="#ef5952">异步</font>和基于事件的程序的库。 它提供了一个核心类型，可观察对象，卫星类型（观察者、调度器、主体）和受 Array 方法启发的运算符（map、filter、reduce、every 等），以允许将异步事件作为集合处理。

使用前请先阅读[中文文档](https://rx.nodejs.cn/guide/overview)理解概念，这里只介绍一些常用方法。
## 例子
```js
"ui";
let { fromEvent } = require("rxjs");
ui.layout(
  <vertical padding="16">
    <button id="y" text="普通按钮" w="auto" />
    <vertical id="box"></vertical>
  </vertical>,
);
//从按钮的点击事件创建一个可观察对象
let ob = fromEvent(ui.y, "click");
let box = ui.box;
//订阅这个对象
ob.subscribe(() => {
  ui.inflate(<text text="1234"></text>, box, true);
});
```
很多时候我们不希望这个按钮触发的太快，使用纯js就需要添加额外的变量用于判断，使用rxjs只需要添加一个`throttleTime`操作符
```js
"ui";
let { fromEvent,throttleTime } = require("rxjs");
ui.layout(
  <vertical padding="16">
    <button id="y" text="普通按钮" w="auto" />
    <vertical id="box"></vertical>
  </vertical>,
);
//从按钮的点击事件创建一个可观察对象
let ob = fromEvent(ui.y, "click").pipe(throttleTime(1000))
let box = ui.box;
//订阅这个对象
ob.subscribe(() => {
  ui.inflate(<text text="1234"></text>, box, true);
});
```
可以将大部分采用回调、事件、Promise的api转换成Observable
```js
//回调
let { bindCallback } = require("rxjs");

let httpGet = bindCallback(http.get);

httpGet("https://m.baidu.com", {}).subscribe({
  next: (res, err) => {
    console.log("body:", res.body.string().length);
  },
  complete(){
    console.log('done')
  }
});
//事件
fromEvent(events, "exit").subscribe({
  next() {
    console.log("event on exit");
  }
});
//Promise
from(Promise.delay(1000)).subscribe({
  next: () => {
    console.log("Promise res");
  },
  complete() {
    console.log("done");
  },
});

```
## 创建操作符
* `of(...args)` 将参数转换为可观察的序列。
```js
let { of } = require("rxjs");
of(10, 20, 30).subscribe({
    next: value => console.log('next:', value),
    error: err => console.log('error:', err),
    complete: () => console.log('the end'),
  });
// Outputs
// next: 10
// next: 20
// next: 30
// the end
```

* `from(input,scheduler?)` 从数组、类数组对象、Promise、可迭代对象或类 Observable 对象创建 Observable。
```js
let {from} = require("rxjs");
const array = [10, 20, 30];
const result = from(array);
result.subscribe(x => console.log(x));
// Logs:
// 10
// 20
// 30
```
* `fromEvent(target,eventName,options?)` 创建一个 Observable，它发出来自给定事件目标的特定类型的事件。
* `intervallink(period=0,period=asyncScheduler)` 创建一个 Observable，该 Observable 在指定的时间间隔内每隔指定的时间间隔发出序列号
* 

## 调度器
rxjs中内置了几种调度器，其中最常用的是asyncScheduler，这是多数处理异步操作符使用的默认调度器，在autox环境中，只支持asyncScheduler调度器。
### autox特有的调度器
v6.5.6新增
由于autox中存在比较复杂的多线程环境，处理ui和阻塞操作时经常需要切换线程，因此为此库添加了几个特殊的调度器简化这些操作
需要使用以下方式导入
```js
let {
  ioScheduler,
  uiScheduler,
  mainScheduler,
  workScheduler,
  newSingleScheduler,
} = require("rxjs/ext");
```
* `uiScheduler` 在ui线程中运行
* `mainScheduler` 在脚本主线程中运行，若是ui脚本则和`uiScheduler`一致
* `newSingleScheduler()` <font color="red">这是一个函数</font>，创建一个独立的线程作为调度器，使用完毕后需要调用`recycle`回收资源
```js
let { from } = require("rxjs");
let {newSingleScheduler} = require("rxjs/ext");
let t = newSingleScheduler();
from([1,2,3],t).subscribe({
    next: (v) => {
      console.log(v);
      console.log(threads.currentThread());
    },
    complete(){
      t.recycle()
    }
  })
```
* `workScheduler` 在一个默认的线程池中运行，用于处理密集计算操作。
  <font color="red">注意:</font> 此调度器是不安全的，由于并发问题，只能配合fromEvent这样永远不会'结束'的Observable来使用，下面这个示例就不会按预期执行
 ```js
let { from } = require("rxjs");
let {workScheduler} = require("rxjs/ext");
from([1,2,3],workScheduler).subscribe((v)=>{
  log(v);//可能看到0-3个输出，且是乱序的
})
```
原因在于Observable执行complete或error后再调用next产生的值将被忽略，就算next调用在complete前面，通过此调度器可能会导致next真正执行时在complete后面，此外某些操作符在这个调度器下也会工作异常。
* `ioScheduler` 和`workScheduler`类似，区别在于每次触发会生成一个新线程来运行，比较耗费资源，适用于io操作等长时间阻塞任务
### 例子
```js
"ui";
let {
  fromEvent,
  scan,
  map,
  observeOn,
  throttleTime,
} = require("rxjs");
let {
  ioScheduler,
  uiScheduler,
  mainScheduler,
  workScheduler,
  newSingleScheduler,
} = require("rxjs/ext");
ui.layout(
  <vertical padding="16">
    <button id="y" text="普通按钮" w="auto" />
    <vertical id="box"></vertical>
  </vertical>,
);
//从按钮的点击事件创建一个可观察对象
let ob = fromEvent(ui.y, "click");
let box = ui.box;
//订阅这个对象
ob.pipe(
  scan((a) => a + 1, 0),
  //转到线程池调度器
  observeOn(workScheduler),
  map((v) => {
    //模拟一些阻塞耗时任务
    sleep(1000);
    return v;
  }),
  //回到ui调度器
  observeOn(uiScheduler),
).subscribe((v) => {
  ui.inflate(<text text={"已计算: 第" + v + "次"}></text>, box, true);
});
```
