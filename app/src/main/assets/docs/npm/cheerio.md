# cheerio
v6.3.9新增
> <font color="#FF34FF17">稳定性: 稳定</font>

这是一个用于解析和生成html/xml的库，使用方法请参阅[官方网站](https://github.com/cheeriojs/cheerio)，该模块不会自动加载，如需使用
```
const cheerio = require('cheerio');
```

这里简单介绍一下在autojs中的用途。  

当你有一个变量，想让它的内容嵌入到ui界面中时你可能会这么做
```js
let text = "变量文本"
ui.layout(
    <vertical>
            <text text="{{text}}" textColor="#000000" textSize="18sp" maxLines="1" />
            <text text="{{text}}" textColor="#000000" textSize="18sp" maxLines="1" />
    </vertical>)
//或是
ui.layout(`
    <vertical>
            <text text="${text}" textColor="#000000" textSize="18sp" maxLines="1" />
            <text text="${text}" textColor="#000000" textSize="18sp" maxLines="1" />
    </vertical>`)
//或是
ui.layout(
    <vertical>
            <text id="text1" textColor="#000000" textSize="18sp" maxLines="1" />
            <text id="text2" textColor="#000000" textSize="18sp" maxLines="1" />
    </vertical>)
ui.text1.setText(text)
ui.text2.setText(text)
```
这3种方法无论哪种都有些缺陷，第一种变量不是全局的则会报错，第二种字符串不能包含特殊字符，否则解析xml时报错，第三种调用安卓方法有一定的性能问题，且不够灵活。  

使用`cheerio`则可以像这样处理:
```js
const cheerio = require('cheerio');
let text = "变量文本"
let $ = cheerio.load( `<vertical>
            <text class="text" textColor="#000000" textSize="18sp" maxLines="1" />
            <text class="text" textColor="#000000" textSize="18sp" maxLines="1" />
    </vertical>`,{
        xmlMode:true
    })
$('.text').text(text);
let xml = $.xml()
log(xml)
ui.layout(xml)
```
高级的用法还可以将xml组件化，列表生成等
