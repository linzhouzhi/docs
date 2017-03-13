# js smarty
> js 实现的模版处理引擎，用于标签处理，包含了翻页局部刷新等常用的功能。  

## what
smarty 是什么？一般指的是 php smarty 他是由php实现的模版引擎，目的是为了将php代码的处理和html数据的展示分开，这样前端人员在没有任何php基础的情况下就可以开发了。
js smarty 又是什么，它是模仿php smarty，利用 js 实现的模版引擎，这套引擎相对于 php smarty 试用场景更广，比如分片加载、分页、局部刷新等。   
> php smarty 地址：http://www.smarty.net/   
js smarty 地址：https://github.com/linzhouzhi/sparrow/tree/master/public/js

## how
js smarty 主要分为三大部分
### 一、标签处理
> 输出一个变量的处理，if,foreach,include    

```
{{foreach from=$admin_list item=rs}}  //循环
	{{include file="jstpl/demo_list_row.tpl"}} // 引入另一个模版
{{/foreach}}
```
### 二、修正器、函数
> 修正器可以将数据进一步处理加工，函数跟意义上的函数一样。其中这两种都包含了内置修正器和函数，当然也可以自定义。    

```
// 修正器的定义
smarty.register_modifier( 'admin_id', function( val ) {
    return val*100;
} );
// 函数的定义
smarty.register_function( 'sum', function( params ){
    return params['val1'] + params['val2'] + params['val3'];
});
// 模版中使用
{{$rs.id|admin_id}} //修正器处理
{{sum val1=1 val2=3 val3=4}} //函数处理
```
### 三、模版加载
#### 函数定义
```
/**
 * 加载一个模板, 并执行回调
 */
html : function( tpl, data, container, callback )
/**
 * 从服务器加载数据(json)组装成模板
 */
get : function( url, tpl, container, callback, is_loading )
/**
 * 提交数据, 再将服务器返回的数据(json)组装成模板
 */
post : function( url, data, tpl, container, callback, is_loading )
/**
 * 打开一个窗口
 */
open : function( tpl, data, window_option, callback )
/**
 * 服务器获取数据, 再组装成html, 打开一个窗口
 */
fopen : function( url, tpl, is_loading, window_option, callback )
popen : function( url, data, tpl, is_loading, window_option, callback )
```
#### 使用例子
```
* jstpl/demo_list_search 模版
* data 模版所需数据
* admin_list_div 模版所在容器
smarty.html( 'jstpl/demo_list_search', data, 'admin_list_div', function(){
    // 回调函数
} );
```
## why
### 优点
* 可以轻松实现异步加载
* 提供常用的标签库，方便前端人员进行设计
* 常用的函数都已经封装好，像分页、日期格式等，不需要后端人员继续处理。
* js smarty 的适用场景会更广,php smarty 只能用于php, jsp 只能用于java。
* js smarty 小巧仅 2 千行代码。

### 缺点
* 具备维护或开发 js smarty 的人比较少,使用前需要培训
* 欢迎补充
