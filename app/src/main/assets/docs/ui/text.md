# 文本控件: text

文本控件用于显示文本，可以控制文本的字体大小，字体颜色，字体等。

> 继承于[**视图(View)**](#视图-view)，可使用View的所有属性

以下介绍该控件的主要属性和方法，如果要查看他的所有属性和方法，请阅读[TextView](http://www.zhdoc.net/android/reference/android/widget/TextView.html)。

## text 

设置文本的内容。例如`text="一段文本"`。

## textColor

设置字体的颜色，可以是RGB格式的颜色(例如#ff00ff)，或者颜色名称(例如red, green等)，具体参见[颜色](#ui_颜色)。

示例, 红色字体：
```jsx
<text text="红色字体" textColor="red"/>
```

## textSize

设置字体的大小，单位一般是sp。按照Material Design的规范，正文字体大小为14sp，标题字体大小为18sp，次标题为16sp。

示例，超大字体: 
```jsx
<text text="超大字体" textSize="40sp"/>
```

## textStyle

设置字体的样式，比如斜体、粗体等。可选的值为：
* bold  加粗字体
* italic  斜体	
* normal  正常字体

可以用或("|")把他们组合起来，比如粗斜体为"bold|italic"。

例如，粗体：
```jsx
<text textStyle="bold" textSize="18sp" text="这是粗体"/>
```

## lines

设置文本控件的行数。即使文本内容没有达到设置的行数，控件也会留出相应的宽度来显示空白行；如果文本内容超出了设置的行数，则超出的部分不会显示。

另外在xml中是不能设置多行文本的，要在代码中设置。例如:

```
"ui";
ui.layout(
    <vertical>
        <text id="myText" line="3">
    </vertical>
)
//通过\n换行
ui.myText.setText("第一行\n第二行\n第三行\n第四行");
```

## maxLines

设置文本控件的最大行数。

## minLines

设置文本控件的最小行数。

## typeface

设置字体。可选的值为：
* `normal` 正常字体
* `sans` 衬线字体
* `serif` 非衬线字体
* `monospace` 等宽字体

示例，等宽字体: `<text text="等宽字体" typeface="monospace"/>`

## ellipsize

设置文本的省略号位置。文本的省略号会在文本内容超出文本控件时显示。可选的值为：
* `end`   在文本末尾显示省略号
* `marquee`   跑马灯效果，文本将滚动显示
* `middle`	在文本中间显示省略号
* `none`	不显示省略号
* `start`	在文本开头显示省略号

## ems

当设置该属性后,TextView显示的字符长度（单位是em）,超出的部分将不显示，或者根据ellipsize属性的设置显示省略号。

例如，限制文本最长为5em: `<text ems="5" ellipsize="end" text="很长很长很长很长很长很长很长的文本"/>

## autoLink

控制是否自动找到url和电子邮件地址等链接，并转换为可点击的链接。默认值为“none”。

设置该值可以让文本中的链接、电话等变成可点击状态。

可选的值为以下的值以其通过或("|")的组合：

* `all`    匹配所有连接、邮件、地址、电话
* `email`    匹配电子邮件地址
* `map`    匹配地图地址
* `none`    不匹配 (默认)
* `phone`    匹配电话号码
* `web`    匹配URL地址

示例：`<text autoLink="web|phone" text="百度: http://www.baidu.com 电信电话: 10000"/>`
