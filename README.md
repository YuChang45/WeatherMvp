# WeatherMvp
      本项目是使用MVP+OkHttp+Retrofit+RxAndroid+Json写的一个简单Demo天气应用。主要是展示我对MVP模式的理解以及融合了响应式编程的MVP怎么应用到项目中。由于本人能力有限，所写的东西一定有不足之处，欢迎大家跟我交流。
        关于MVP模式网上解释太多太多，我就不赘述了，这个模式基本原则如下图，说白了，就把握两点，第一View和Model不能直接交互，第二将逻辑代码转移到Presenter层，让Presenter层持有View和Model层的引用。因为MVP只是一种设计模式，而非固定的代码模板，所以网上涌现出了许多不同理解的MVP项目，我这也是其中一个，谁好谁劣，我能力有限不做评说，大家取其精华去去糟粕即可。

       ![image](https://github.com/YuChang45/WeatherMvp/blob/master/app/src/main/res/raw/mvp.png)

       下图是我的工程的结构，

      ![image](https://github.com/YuChang45/WeatherMvp/blob/master/app/src/main/res/raw/gc01.png)

      ![image](https://github.com/YuChang45/WeatherMvp/blob/master/app/src/main/res/raw/gc02.png)
![image](https://github.com/YuChang45/WeatherMvp/blob/master/app/src/main/res/raw/gc02.png?raw=true)
      上图是我对工程结构的简图描述，View层和Presenter层互相持有引用，他们之间配合完成整个逻辑，Presenter层通过接口持有model层的引用，
      这样Presenter层并不需要知道数据是从哪里来的，可以起到一个解耦的作用。





