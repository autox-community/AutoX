# 垂直布局: vertical

垂直布局是一种比较简单的布局，会把在它里面的控件按照垂直方向依次摆放，如下图所示：

垂直布局:

 —————

| 控件1 |

| 控件2 |

| 控件3 |

| ............ |

 ——————

## layout_weight

垂直布局中的控件可以通过`layout_weight`属性来控制控件高度占垂直布局高度的比例。如果为一个控件指定`layout_weight`, 则这个控件的高度=垂直布局剩余高度 * layout_weight / weightSum；如果不指定weightSum, 则weightSum为所有子控件的layout_weight之和。所谓"剩余高度"，指的是垂直布局中减去没有指定layout_weight的控件的剩余高度。
例如:
```
"ui";
ui.layout(
    <vertical h="100dp">
        <text layout_weight="1" text="控件1" bg="#ff0000"/>
        <text layout_weight="1" text="控件2" bg="#00ff00"/>
        <text layout_weight="1" text="控件3" bg="#0000ff"/>
    </vertical>
);
```

在这个布局中，三个控件的layout_weight都是1，也就是他们的高度都会占垂直布局高度的1/3，都是33.3dp.
再例如：
```
"ui";
ui.layout(
    <vertical h="100dp">
        <text layout_weight="1" text="控件1" bg="#ff0000"/>
        <text layout_weight="2" text="控件2" bg="#00ff00"/>
        <text layout_weight="1" text="控件3" bg="#0000ff"/>
    </vertical>
);
```

在这个布局中，第一个控件高度为1/4, 第二个控件为2/4, 第三个控件为1/4.
再例如：
```
"ui";
ui.layout(
    <vertical h="100dp" weightSum="5">
        <text layout_weight="1" text="控件1" bg="#ff0000"/>
        <text layout_weight="2" text="控件2" bg="#00ff00"/>
        <text layout_weight="1" text="控件3" bg="#0000ff"/>
    </vertical>
);
```
在这个布局中，因为指定了weightSum为5, 因此第一个控件高度为1/5, 第二个控件为2/5, 第三个控件为1/5.
再例如：
```
"ui";
ui.layout(
    <vertical h="100dp">
        <text h="40dp" text="控件1" bg="#ff0000"/>
        <text layout_weight="2" text="控件2" bg="#00ff00"/>
        <text layout_weight="1" text="控件3" bg="#0000ff"/>
    </vertical>
);
```
在这个布局中，第一个控件并没有指定layout_weight, 而是指定高度为40dp, 因此不加入比例计算，此时布局剩余高度为60dp。第二个控件高度为剩余高度的2/3，也就是40dp，第三个控件高度为剩余高度的1/3，也就是20dp。

垂直布局的layout_weight属性还可以用于控制他的子控件高度占满剩余空间，例如：
```
"ui";
ui.layout(
    <vertical h="100dp">
        <text h="40dp" text="控件1" bg="#ff0000"/>
        <text h="40dp" text="控件2" bg="#00ff00"/>
        <text layout_weight="1" text="控件3" bg="#0000ff"/>
    </vertical>
);
```
在这个布局中，第三个控件的高度会占满除去控件1和控件2的剩余空间。
