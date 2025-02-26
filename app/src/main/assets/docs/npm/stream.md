# stream
v6.4.0新增
> 稳定性: 稳定

注意: <font color="#ef5952">这个模块是异步的，使用此模块请勿阻塞线程</font>
该模块不会自动加载，如需使用，请用
```
const stream = require('stream');
```
使用方法参考node文档中stream，下方是一些autox中特有的内容
## stream.fromInputStream(inputStream[,options])
* inputStream {InputStream} java输入流
* options {object} 选项,详细见node文档

将java流转为Readable可读流，为提高处理速度，默认的缓冲区大小被设为64kb，io操作由内部io线程处理
## stream.fromOutputStream(outputStream[,options])
* outputStream {OutputStream} java输出流
* options {object} 选项,详细见node文档

将java流转为Writable可写流，为提高处理速度，默认的缓冲区大小被设为64kb，io操作由内部io线程处理
## stream.createFileReadStream(path[,bufferSize])
* path {String} 文件路径
* bufferSize {Number} 缓冲区大小，默认256k

从文件创建一个可读流
## stream.createFileWriteStream(path[,bufferSize])
* path {String} 文件路径
* bufferSize {Number} 缓冲区大小，默认256k

从文件创建一个可写流
