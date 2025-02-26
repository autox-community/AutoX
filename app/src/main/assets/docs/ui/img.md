# 图片控件: img

图片控件用于显示来自网络、本地或者内嵌数据的图片，并可以指定图片以圆角矩形、圆形等显示。但是不能用于显示gif动态图。

这里只介绍他的主要方法和属性，如果要查看他的所有方法和属性，阅读[ImageView](http://www.zhdoc.net/android/reference/android/widget/ImageView.html)。

## src

使用一个Uri指定图片的来源。可以是图片的地址(http://....)，本地路径(file://....)或者base64数据("data:image/png;base64,...")。

如果使用图片地址或本地路径，Auto.js会自动使用适当的缓存来储存这些图片，减少下次加载的时间。

例如，显示百度的logo:
```
"ui";
ui.layout(
    <frame>
        <img src="https://www.baidu.com/img/bd_logo1.png"/>
    </frame>
);
```

再例如，显示文件/sdcard/1.png的图片为 `<img src="file:///sdcard/1.png"/>`。
再例如，使base64显示一张钱包小图片为：
```
"ui";
ui.layout(
    <frame>
        <img w="40" h="40" src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADwAAAA8CAYAAAA6/NlyAAAACXBIWXMAAA7EAAAOxAGVKw4bAAAEu0lEQVRoge3bW4iVVRQH8N+ZnDKxvJUGCSWUlXYle/ChiKAkIiu7UXQjonwNIopM8cHoAhkRGQXdfIiE0Ep8KalQoptRTiFFZiRlOo6TPuSk4zk97G9w5vidc77LPjNi84f1MN+391rrf9a+rL32N4xiFMcUjouo5zyciYPYH0FnBadiNiZiD2oR9JbGRdgiOFPDIXRhCWYU0Dcj6duV6BrQuyWxNaLowBcOO1Uv+7EKc4WINUIlabMq6dNI35eJzRHDWOzS2MEB6cd6XI/OQf07k2frkzat9HQnNkcUG7R2dECq2I53EtmePMvaf+MwcWqKu+RzuqhUcfcwcWqKTvmiXFQ2GDodRhQz0aN9ZHsSG0cVrkGf+GT7MG8YeeTCHeKS7sOdMR1stjcWxY2YH0nXh1gdSdf/E+2I8KVYigkl9ewVUsxNpT1qMzaKN4ejJxrtyEt7IuraE1EX2jOkp+JBnFxSzz68KuTqoyiK2BHuxDO4NpK+j/GoOAWF6BiH98Q/SHyCycPIIxMm4FPZCPTj30SynIFr+A7ThotMK4wXopA1Ym9gSiKv5Oj3bdKnFMpuS514E1fm6NMnbF098s3NS4QS0Ik5+hyBsoSXYkGO9jvxy6C/t+IPIYJZcBWW57AXFfMNrSo2kqqw2l4hvSzcIRTw1sm24FVxb5s4NcR0/JXBuUNYJttI6sDjsi1kvTgrGpsMjq3O4FQNa+SbNhWsyKj7I4wpzSYDbpFtKB/EOSn9ZwpRfx5Xp7yfhN0Z9FdxXxxKjTEe2zI4U8NnKf3PNrT2VcWTKe1eyGjjT+Eapm14IqMjNTyd0n9JSrsDwhmaEN2H8GMOO8viUjyMSfJVJh9O0bGoQdt1eFm2oVwve7UpC1ssX568KEXH6fghp54s8lRkrk7CjpxOrGqg6wQ8IKSKWXPpVtIt8ly+v4ATf2t+yqlgDl5SbCjXy8JIXFXweQEHqngxo43JeEw54l+JVLKaJeypRZzoFxavrIWG6cKPW2SO9+PCMkQHsLiA8fpIv5/DmUn4qaCtpWWIEiLzdUHj9XJA2H5uFRbBZriuoI1NSpatpio+nJtFvFvYd2c1sDsGvxfQ3a/knrwgMtm0qD8rPSprCuq8uRmhVqvanBbvm+EQfsNKIcnvTmnTiUdwQcq73oJ2L2v2stXx6vyCRr8RDuk/C8OMUK24J6VtBaekPG81zxuh0TTJhC7FhtUOHF+n61whGalvu8uRWVJFvgPEYOkqQzhLVSPPXLoYa4Xh3Stcls1NaTdb8Xx7ZxnCvSUIfy/kzWno0Pyzx3dL2C0695Hto7NGUhXy5Lzp3kLZKiqNpNTl2+YShgdIvyXbVck44TB/oKTNzWUIv13S+IDsFmpY84QvZAcwTbh4e04o18SwtbIM4dsiOTFYVgzSv7wN+m9vRqjV/PrA0JuCox1bhYNKQ7Qi3CcU1fpiedRG9AkLXhRfbxCnKlET0s21ifwaSWcPbopBdDDOwGtClTD2vCsq+/C68K8HmVDk7DhFyIsvFzKnGThN+689+oU9dptwQb5B+LB8dx4lMb7xqAhkJwo/xljhFFSfSdUc3mPrcbwj15P+pP0/QiR7hYSkGsHnUYziWMF/mXV4JVcZ8G0AAAAASUVORK5CYII="/>
    </frame>
);
```

## tint

图片着色，其值是一个颜色名称或RGB颜色值。使用该属性会将图片中的非透明区域都涂上同一颜色。可以用于改变图片的颜色。

例如，对于上面的base64的图片: `<img w="40" h="40" tint="red" src="data:image/png;base64,..."/>`，则钱包图标颜色会变成红色。

## scaleType

控制图片根据图片控件的宽高放缩时的模式。可选的值为：

* `center`	在控件中居中显示图像, 但不执行缩放。
* `centerCrop`	保持图像的长宽比缩放图片, 使图像的尺寸 (宽度和高度) 等于或大于控件的相应尺寸 (不包括内边距padding)并且使图像在控件中居中显示。
* `centerInside`	保持图像的长宽比缩放图片, 使图像的尺寸 (宽度和高度) 小于视图的相应尺寸 (不包括内边距padding)并且图像在控件中居中显示。
* `fitCenter`	保持图像的长宽比缩放图片, 使图片的宽**或**高和控件的宽高相同并使图片在控件中居中显示
* `fitEnd`	保持图像的长宽比缩放图片, 使图片的宽**或**高和控件的宽高相同并使图片在控件中靠右下角显示
* `fitStart`	保持图像的长宽比缩放图片, 使图片的宽**或**高和控件的宽高相同并使图片在控件靠左上角显示
* `fitXY`	使图片和宽高和控件的宽高完全匹配，但图片的长宽比可能不能保持一致
* `matrix`	绘制时使用图像矩阵进行缩放。需要在代码中使用`setImageMatrix(Matrix)`函数才能生效。

默认的scaleType为`fitCenter`；除此之外最常用的是`fitXY`， 他能使图片放缩到控件一样的大小，但图片可能会变形。

## radius

图片控件的半径。如果设置为控件宽高的一半并且控件的宽高相同则图片将剪切为圆形显示；否则图片为圆角矩形显示，半径即为四个圆角的半径，也可以通过`radiusTopLeft`, `radiusTopRight`, `radiusBottomLeft`, `radiusBottomRight`等属性分别设置四个圆角的半径。

例如，圆角矩形的Auto.js图标：`<img w="100" h="100" radius="20" bg="white" src="http://www.autojs.org/assets/uploads/profile/3-profileavatar.png" />`

有关该属性的单位，参见[尺寸的单位: Dimension](#ui_尺寸的单位_Dimension)。

## radiusTopLeft

图片控件的左上角圆角的半径。有关该属性的单位，参见[尺寸的单位: Dimension](#ui_尺寸的单位_Dimension)。

## radiusTopRight

图片控件的右上角圆角的半径。有关该属性的单位，参见[尺寸的单位: Dimension](#ui_尺寸的单位_Dimension)。

## radiusBottomLeft

图片控件的左下角圆角的半径。有关该属性的单位，参见[尺寸的单位: Dimension](#ui_尺寸的单位_Dimension)。

## radiusBottomRight

图片控件的右下角圆角的半径。有关该属性的单位，参见[尺寸的单位: Dimension](#ui_尺寸的单位_Dimension)。

## borderWidth

图片控件的边框宽度。用于在图片外面显示一个边框，边框会随着图片控件的外形(圆角等)改变而相应变化。
例如, 圆角矩形带灰色边框的Auto.js图标：`<img w="100" h="100" radius="20" borderWidth="5" borderColor="gray" bg="white" src="http://www.autojs.org/assets/uploads/profile/3-profileavatar.png" />`

## borderColor

图片控件的边框颜色。

## circle

指定该图片控件的图片是否剪切为圆形显示。如果为`true`，则图片控件会使其宽高保持一致(如果宽高不一致，则保持高度等于宽度)并使圆形的半径为宽度的一半。

例如，圆形的Auto.js图标：`<img w="100" h="100" circle="true" bg="white" src="http://www.autojs.org/assets/uploads/profile/3-profileavatar.png" />`