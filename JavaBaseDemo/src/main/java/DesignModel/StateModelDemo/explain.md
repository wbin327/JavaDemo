## 状态模式Demo

### 现有如下场景
金融借贷平台项目：借贷平台的订单，有审核-发布-抢单等状态，随着操作不同，会改变订单的状态，
项目中这个模块实现就会使用到状态模式

### 问题分析：
这类代码，在添加一种状态时，我们需要手动添加if/else，在添加一种功能时，
要对所有的状态进行判断。因此代码会变得越来越臃肿，并且一旦没有处理某个状态
，便会发生及其严重的BUG,难以维护

