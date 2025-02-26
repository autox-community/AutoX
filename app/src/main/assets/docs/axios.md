# axios
v6.3.9新增
> <font color="#ec5315">稳定性: 实验</font>

<font color="#ef5952">**注意**: 这个模块是异步的，返回的全部都是`Promise`，如果你的程序有大量阻塞函数，请谨慎使用</font>
这个模块不会自动加载，如需使用，请用
```js
const axios = require('axios');
```
模块使用方法与axios完全一致，请参阅[官方网站](https://www.axios-http.cn/docs/intro)，以下只介绍一些在autox中特有的内容。   

这个模块通过模拟浏览器关键对象`XMLHttpRequest`得以运行，内部使用okhttp进行网络请求，行为与浏览器中有些许差异。

不支持的内容:
* `ArrayBuffer` 不支持处理和解析
* `XMLHttpRequest.overrideMimeType()`
* `XMLHttpRequest.timeout` 暂不支持设置
* 上传和下载进度事件

支持的`responseType`:
* `text`
* `json`
* `blob`
* `inputstream` java输入流
* `stream` Readable可读流 \*v6.4.0新增

支持的请求体数据类型:
* `RequestBody` okhttp3.RequestBody对象
* `FormData` 
* `Blob`
* `InputStream` java输入流
* `String`
* `plain object` 会解析成json

一个简单的示例
```js
const axios = require("axios");
const FormData = axios.browser.FormData;

/*
  下载文件
*/
axios('https://m.baidu.com', {
    responseType: 'blob'
}).then((res) => {
    const blob = res.data
    log('blob:', blob);
    //保存blob
    //return axios.utils.saveBlobToFile(blob, savePath)
}).then(() => {
    log('下载成功')
}).catch(console.error)


/*
  使用表单
*/
let form = new FormData()
form.set('a', 'b')
form.append('b', '123')
form.append('b', '测试')
axios.post('http://baidu.com', form).then(function (res) {
    log('请求成功1');
}).catch(console.error)

/*
  使用表单上传文件
*/
let blob = axios.utils.openFile('./使用axios.js')

form.enctype = 'multipart/form-data'
form.set('file', blob)
axios.post('http://baidu.com', form).then(function (res) {
    log('请求成功2');
}).catch(console.error)

/*
  也可以使用直接传输
*/
axios.post('http://baidu.com', blob).then(function (res) {
    log('请求成功3');
}).catch(console.error)
```

## axios.browser
用于模拟浏览器环境的对象，包含`XMLHttpRequest`、`FormData`等，除了`FormData`，其他对象都不建议使用。

## axios.utils
包含一些操作blob对象方法
### utils.saveBlobToFile(blob, path)
* `blob` {Blob} 要保存的对象
* `path` {String} 保存路径  

保存blob对象到指定路径，返回一个`Promise`。

### utils.openFile(path)
* `path` {String} 要打开的文件路径

打开一个文件，返回一个blob对象  
### utils.copyInputStream(inputstream, outputstream)
* `inputstream`java输入流
* `outputstream` java输出流

拷贝输入流到输出流，这个函数是阻塞的，且不会自动关闭流。
### utils.ThreadPool
此对象用于将一个同步函数转成异步方法运行，返回一个`Promise`，例如
```js
let promise = ThreadPool.run(()>{
  //同步代码，返回值就是Promise的返回值
})
```
