# event-stream
v6.4.0新增
> 稳定性: 稳定

来自npm模块event-stream，如需使用，请用
```js
const es = require('event-stream')
```
该模块用于便捷的创建流，完整说明见[项目主页](https://github.com/dominictarr/event-stream)
可读流:
```js
es.readable(function (count, callback) {
  if(streamHasEnded)
    return this.emit('end')
  
  //...
  
  this.emit('data', data) //use this way to emit multiple chunks per call.
      
  callback() // you MUST always call the callback eventually.
             // the function will not be called again until you do this.
})
```
转换流:
```js
var es = require('event-stream')
es.map(function (data, callback) {
  //transform data
  // ...
  callback(null, data)
})
 
```
可写流:
```
var es = require('event-stream')
es.map(function (data, callback) {
  // ...
  callback(null, null)
})
 
```
例子:
```js
const es = require("event-stream");

//创建一个可读流
let e = 0
let r = es.readable(function(count, callback) {
    if (e > (10)) return this.emit('end')
    this.emit('data', String(e));
    e++;
    return callback()
});
//转换流
let map = es.map(function (data, callback) {
  data = `--${data}--`
  callback(null, data)
})

//可写流，这里是将数据打印出来
let w = es.map(function (data, callback) {
  log(data)
  callback(null, null)
})

w.on('close',()=>{
    log('操作完成')
});

r.pipe(map).pipe(w);//将3个流连起来
```
例子2（配合io流使用）:
```js
const es = require("event-stream");
const stream = require("stream")

let r = stream.createFileReadStream(
    '/sdcard/文本文件.txt'
)

//可写流，这里是将数据打印出来
let w = es.map(function (data, callback) {
  log(data)
  callback(null, null)
})

//连通流，其中es.split()是一个流，负责将数据按行输出
r.pipe(es.split()).pipe(w)
```
