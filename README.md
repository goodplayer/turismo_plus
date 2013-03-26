turismo_plus
======================================================================
     -- it was once designed - just modify / add my own code to turismo. :)
        Now it is totally a new software.
[![Build Status](https://travis-ci.org/goodplayer/turismo_plus.png?branch=master)](https://travis-ci.org/goodplayer/turismo_plus)

Home Page of [turismo](https://github.com/ghosthack/turismo)

Contact me : [http://blog.moetang.net](http://blog.moetang.net)

最新版0.0.5-r版,大幅修改内核结构,以适应新功能:
1 1、Servlet3.0异步
1 2、后端并行处理
1 3、BigPipe模型

功能越来越多,结构越来越复杂,慢慢的脱离了sinatra/turismo的风格,从0.0.5-r版到turismo_plus代号的最后一版0.0.9-r,api将不断变化和优化

How to use, add below to your pom.xml:

```xml
<dependency>
    <groupId>net.moetang</groupId>
    <artifactId>turismo_plus</artifactId>
    <version>0.0.5-r</version>
</dependency>
```

各个老API的TOP版本:
```xml
<dependency>
    <groupId>net.moetang</groupId>
    <artifactId>turismo_plus</artifactId>
    <version>0.0.4-r</version>
</dependency>
```

* [使用说明](https://github.com/goodplayer/turismo_plus/wiki/%E4%BD%BF%E7%94%A8%E8%AF%B4%E6%98%8E)
* [如何使用模板渲染引擎](https://github.com/goodplayer/turismo_plus/wiki/%E5%A6%82%E4%BD%95%E4%BD%BF%E7%94%A8%E6%A8%A1%E6%9D%BF%E6%B8%B2%E6%9F%93%E5%BC%95%E6%93%8E)
