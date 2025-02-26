# Lodash
v6.5.2新增
> <font color="#FF34FF17">稳定性: 稳定</font>

Lodash 是一个一致性、模块化、高性能的 JavaScript 实用工具库，使用方法请参阅[中文文档](https://www.lodashjs.com/)，该模块不会自动加载，请使用以下方法导入
```js
// Load the full build.
var _ = require('lodash');
// Load the core build.
var _ = require('lodash/core');
// Load the FP build for immutable auto-curried iteratee-first data-last methods.
var fp = require('lodash/fp');
 
// Load method categories.
var array = require('lodash/array');
var object = require('lodash/fp/object');
 
// Cherry-pick methods for smaller browserify/rollup/webpack bundles.
var at = require('lodash/at');
var curryN = require('lodash/fp/curryN');
```
