# module (模块)

> 稳定性: 稳定

Auto.js 有一个简单的模块加载系统。 在 Auto.js 中，文件和模块是一一对应的（每个文件被视为一个独立的模块）。

例子，假设有一个名为 foo.js 的文件：
```js
var circle = require('./circle.js');
console.log("半径为 4 的圆的面积是 %d", circle.area(4));
```
在第一行中，foo.js 加载了同一目录下的 circle.js 模块。

circle.js 文件的内容为：
```js
const PI = Math.PI;

exports.area = function (r) {
  return PI * r * r;
};

exports.circumference = (r) => 2 * PI * r;

```
circle.js 模块导出了 area() 和 circumference() 两个函数。 通过在特殊的 exports 对象上指定额外的属性，函数和对象可以被添加到模块的根部。

模块内的本地变量是私有的。 在这个例子中，变量 PI 是 circle.js 私有的，不会影响到加载他的脚本的变量环境。

module.exports属性可以被赋予一个新的值（例如函数或对象）。

如下，bar.js 会用到 square 模块，square 导出一个构造函数：
```js
const square = require('./square.js');
const mySquare = square(2);
console.log("正方形的面积是 %d", mySquare.area());
```
square 模块定义在 square.js 中：
```js
// 赋值给 `exports` 不会修改模块，必须使用 `module.exports`
module.exports = function(width) {
  return {
    area: () => width ** 2
  };
};
```
### `require`函数
`require`函数用于加载模块，返回模块中`module.exports`的值。

该函数有一个参数用于查找模块位置，可以是相对路径(以'./'或'../'开头)，也可以是绝对路径(以'/'开头)，
还可以是以'http://'或'https://'开头的uri地址，用于加载网络模块，出于安全和加载速度考虑，此方式<font color="#ef5952">不建议使用</font>。

当没有以这些开头时，将会视为内置模块，从内置模块目录依次查找，由于历史原因，在脚本主文件中仍然会先尝试解析成相对路径解析，若解析成功则会忽略内置模块直接加载，<font color="#ef5952">强烈不建议使用此方式加载相对路径的模块，该方式在模块中不可用并且被弃用，在未来版本可能会被移除。</font>

和nodejs类似，当传入的是一个目录，则会尝试加载该目录下的index.js文件，若存在package.json文件则会先解析该文件中的main字段，若main字段指向一个有效的模块将直接加载该模块。
### 模块变量
这些变量只存在于模块中，并非全局变量
* `module` 储存当前模块一些信息的对象，其中最重要的是`module.exports`表示该模块导出的对象

* `exports` 相当于预先运行了`var exports = module.exports`
* `__dirname` 当前模块的目录名
* `__filename` 当前模块的文件名。 这是当前模块文件的已解析符号链接的绝对路径。

