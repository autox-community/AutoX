# OCR文档
>稳定性: 
>
> 实验性的函数、模块或特性，
> 在未来的更新中可能会更改或移除。应该谨慎使用这些函数或模块，或者仅用作临时或试验用途。
# Paddle OCR
**5.6.1 新增**
 基于百度飞桨的 OCR
## paddle.ocr(img, path)
- `img` {Image} 图片
- `path` {String} 自定义模型路径,必须是绝对路径
- `return` {Array}

使用自定义模型进行文字识别
```
// files.path() 将相对路径转为绝对路径
let myModelPath = files.path("./models");
let result = paddle.ocr(img, myModelPath)
```
## paddle.ocr(img[, cpuThreadNum=4, useSlim=true])
- ` img ` {Image} 图片
- ` cpuThreadNum ` {Number} 识别使用的 CPU 核心数量
- ` useSlim ` {Boolean} 加载的模型,可选值:
  - `true` ocr_v2_for_cpu(slim) :快速模型,默认
  - `false` ocr_v2_for_cpu : 精准模型
- `return` {Array}  

高精度识别，返回值包含坐标，置信度
```js
let res = paddle.ocr(img);
toastLog(JSON.stringify(res))
```
返回值示例
```json
[{
	"bounds": {
		"bottom": 535,
		"left": 348,
		"right": 631,
		"top": 384
	},
	"confidence": 0.9808736,
	"inferenceTime": 188.0,
	"preprocessTime": 53.0,
	"text": "约定",
	"words": "约定"
}]
```
## paddle.ocrText(img[, cpuThreadNum=4, useSlim=true])
- ` img ` {Image} 图片
- ` cpuThreadNum ` {Number} 识别使用的 CPU 核心数量
- ` useSlim ` {Boolean} 加载的模型,可选值:
  - `true` ocr_v2_for_cpu(slim) :快速模型,默认
  - `false` ocr_v2_for_cpu : 精准模型
- `return` {Array} 字符串数组

只返回文本识别信息
```js
let res = paddle.ocrText(img);
toastLog("识别信息: " + JSON.stringify(res))
//["约定","最终相遇"]
```
## paddle.release()
 释放 native 内存，非必要，供万一出现内存泄露时使用
# Tessract OCR
**6.2.9 新增**
前往 github 下载完整例子：[TessractOCR](https://github.com/wilinz/autoxjs-tessocr)
# Google ML kIT OCR
**6.3.4 新增**
## gmlkit.ocr(img,Language)
- `img` {Image} 图片
- `Language` {String} 识别语言，可选值为：
   - `la` 拉丁
   - `zh` 中文
   - `sa` 梵文
   - `ja` 日语
   - `ko` 韩语
   - [更多语言](https://developers.google.cn/ml-kit/vision/text-recognition/v2/languages)
- `retrun` {Object} Json
```JS
//识别中文
let result = gmlkit.ocr(img, "zh");
log(result.text)
```
