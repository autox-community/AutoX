# 协程

见 App 中 `示例代码 / 协程`

## Promise.coroutine()
**v6.3.7 新增**
在该版本引入bluebird后你可以使用bluebird带来的一项协程特性，这个方法接收一个**Generator 函数**作为参数，函数内部可以像**async函数**一样编写，返回一个返回值为`Promise`的函数，说起来可能有点难以理解，具体看下方例子
```
//async函数写法
let main = async function(s) {
    var result = await Promise.resolve("value:"+s);
    return result;
}
//Generator 函数写法
let main = Promise.coroutine(function*(s) {
    var result = yield Promise.resolve("value:"+s);
    return result;
})

main('test').then(log)
```
可以看到只要将await关键换成yield就能够代替还不支持的async函数，下方是一个异步循环的例子
```
let main = Promise.coroutine(function*(size) {
    for (var i = 0; i < size; i++) {
        yield Promise.delay(1000);
        log(i);
    }
    log('end')
})
main(10);//在控制台每秒输出一个数字
```
与async函数不同的是yield关键字作为表达式使用时必须带上圆括号，否则会有语法错误，在默认情况下yield后面只能是Promise或带有then方法的对象，要处理其他类型数据需要使用`Promise.coroutine.addYieldHandler()`添加处理器
## Promise.coroutine.addYieldHandler(handler)
- handler {function} 处理函数

函数的参数为yield 的导出，反回值为yield的反回
