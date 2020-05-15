
# 华为心脏健康APP 机型检测绕过 (Xposed)
## 需求
1. Xposed Framework >= 82
2. Android API >= 16

## 实现过程
1. 利用[JadX](https://github.com/skylot/jadx)解包华为心脏健康APP(com.plagh.heartstudy)
2. 利用启动Splash屏幕的错误提示，定位代码中的字符串资源 `src\main\res\values\strings.xml`
```
<string name="use_notice_title">机型适配</string>
```

3. 利用 `use_notice_title` 定位到类`com.plagh.heartstudy.view.activity.SplashActivity.f`
4. 查找`f` method的引用，找到`com.plagh.heartstudy.view.activity.SplashActivity.d`
5. `e`判断系统的`ro.build.hw_emui_api_level`是否满足要求， `b`检测机型是否为"HUAWEI"或"HONOR"。
然而判断逻辑要求两者皆不满足才进入机型检测失败页面，所以本次只需注入其中之一。我选择注入`b`函数。
6. 接下来内容转到Xposed Framework开发源码，见类`com.github.wuyuanyi135.huaweibypass.Hook`

望厂商构建自己生态的时候保持风度。


![Annotation 2020-05-15 221307.png](img/Annotation%202020-05-15%20221307.png)

