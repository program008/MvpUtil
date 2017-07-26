#实现登录注册使用MVP模式
#
[鸿洋_](http://blog.csdn.net/lmj623565791/article/details/46596109)

## MVC 向MVP的转变
![mvc](http://img.blog.csdn.net/20150622212835554)
###转变为：
![mvp](http://img.blog.csdn.net/20150622212856011)
#
##MVP和MVC区别
**MVP和MVC对比**
#
相信大家对MVC都是比较熟悉了：M-Model-模型、V-View-视图、C-Controller-控制器，MVP作为MVC的演化版本，那么类似的MVP所对应的意义：M-Model-模型、V-View-视图、P-Presenter-表示器。 从MVC和MVP两者结合来看，Controlller/Presenter在MVC/MVP中都起着逻辑控制处理的角色，起着控制各业务流程的作用。而 MVP与MVC最不同的一点是M与V是不直接关联的也是就Model与View不存在直接关系，这两者之间间隔着的是Presenter层，其负责调控 View与Model之间的间接交互。在 Android中很重要的一点就是对UI的操作基本上需要异步进行也就是在MainThread中才能操作UI，所以对View与Model的切断分离是 合理的。此外Presenter与View、Model的交互使用接口定义交互操作可以进一步达到松耦合也可以通过接口更加方便地进行单元测试。所以也就有了这张图片（MVP和MVC的对比）
#
![](http://img.blog.csdn.net/20150622212856011)

##DEMO截图     
<img width="540" height="960" src="https://github.com/program008/MvpUtil/blob/master/screenshots/device-2017-07-26-182505.png?raw=true"/>

<img width="540" height="960" src="https://github.com/program008/MvpUtil/blob/master/screenshots/device-2017-07-26-182545.png?raw=true"/><img width="540" height="960" src="https://github.com/program008/MvpUtil/blob/master/screenshots/device-2017-07-26-182612.png?raw=true"/>



