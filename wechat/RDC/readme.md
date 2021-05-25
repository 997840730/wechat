# JavaWeb微信

## 1.实现功能

### 聊天功能

能与好友单对单聊天，也可加入群聊进行聊天。

### 朋友圈

可以发布照片到朋友圈（实际上可以选择上传文件，但个人不建议如此使用），好友可观看。

### 好友功能

可以搜索好友或群聊进行好友或群聊申请，当对方同意过后方可进行聊天与查看朋友圈。同时可以选择将好友拉黑，同时将不再接收到拉黑的好友消息。

### 个人信息功能

可以用邮箱创建个人账号，同时除去用户账号与申请邮箱不可更改之外，其余信息可以进行更改。

## 2.设计思路

![image-20210525183341665](G:\yanfazhongxing\RDC\wechat\image\image-20210525183341665.png)

### web

![image-20210525183506670](G:\yanfazhongxing\RDC\wechat\image\image-20210525183506670.png)

![image-20210525183518421](G:\yanfazhongxing\RDC\wechat\image\image-20210525183518421.png)

监听器负责在项目开始时将需要的类生成，并放在ConcurrentHashMap中。

在页面操作时，将信息送到weChatServlet，实现分发，送到对应的provider层中，进行处理。

webServicet与HttpSessionConfigurator负责websocket的搭建，所有消息经过该类。

筛选器负责将不经过登录页面操作跳转会首页面，同时，负责将敏感词汇拦截下来。

provider层将对应的操作分发。

### Service

![image-20210525184220519](G:\yanfazhongxing\RDC\wechat\image\image-20210525184220519.png)

该层负责进行业务逻辑的操作

### dao

![image-20210525185046910](G:\yanfazhongxing\RDC\wechat\image\image-20210525185046910.png)

对数据库进行增删改查的操作

### po

![image-20210525185126415](G:\yanfazhongxing\RDC\wechat\image\image-20210525185126415.png)

对应数据库库的表单

### Exception与factory

![image-20210525185233251](G:\yanfazhongxing\RDC\wechat\image\image-20210525185233251.png)

负责异常处理与类的代理申请。

### util

![image-20210525185340373](G:\yanfazhongxing\RDC\wechat\image\image-20210525185340373.png)

工具类，负责辅助完成项目操作。