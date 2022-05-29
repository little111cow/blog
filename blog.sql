/*
SQLyog Community v13.1.7 (64 bit)
MySQL - 5.7.24 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `blog`;

/*Table structure for table `t_blog` */

DROP TABLE IF EXISTS `t_blog`;

CREATE TABLE `t_blog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `first_picture` varchar(1000) DEFAULT NULL,
  `flag` varchar(255) DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `appreciation` tinyint(1) DEFAULT '0',
  `share_statement` tinyint(1) DEFAULT '0',
  `commentabled` tinyint(1) DEFAULT '0',
  `published` tinyint(1) NOT NULL DEFAULT '0',
  `recommend` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `description` text,
  `tag_ids` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `t_blog` */

insert  into `t_blog`(`id`,`title`,`content`,`first_picture`,`flag`,`views`,`appreciation`,`share_statement`,`commentabled`,`published`,`recommend`,`create_time`,`update_time`,`type_id`,`user_id`,`description`,`tag_ids`) values 
(2,'使用System.out.format()格式化输出','#### JDK5.0允许java像C语言那样直接用printf()方法来格式化输出\r\n####  System.out.format()功能与printf()一样，可以使用%d,%f等参数。\r\n使用System.out.format()完成**左对齐，补0，千位分隔符，小数点位数，本地化表达**\r\n```java\r\npublic class TestNumber {\r\n   \r\n    public static void main(String[] args) {\r\n        int year = 2020;\r\n        //左对齐，补0，千位分隔符，小数点位数，本地化表达\r\n        \r\n      //直接打印数字\r\n        System.out.println(year);\r\n          \r\n        //直接打印数字\r\n        System.out.format(\"%d%n\",year);\r\n        //总长度是8,默认右对齐\r\n        System.out.format(\"%8d%n\",year);\r\n        //总长度是8,左对齐\r\n        System.out.format(\"%-8d%n\",year);\r\n        //总长度是8,不够补0\r\n        System.out.format(\"%08d%n\",year);\r\n        //千位分隔符\r\n        System.out.format(\"%,8d%n\",year*10000);\r\n  \r\n        //保留5位小数\r\n        System.out.format(\"%.5f%n\",Math.PI);\r\n          \r\n        //不同国家的千位分隔符\r\n        System.out.format(Locale.FRANCE,\"%,.2f%n\",Math.PI*10000);\r\n        System.out.format(Locale.US,\"%,.2f%n\",Math.PI*10000);\r\n        System.out.format(Locale.UK,\"%,.2f%n\",Math.PI*10000);\r\n          \r\n    }\r\n}\r\n```\r\n输出结果：\r\n\r\n```java\r\n2020\r\n2020\r\n    2020\r\n2020    \r\n00002020\r\n20,200,000\r\n3.14159\r\n31?415,93\r\n31,415.93\r\n31,415.93\r\n```\r\n','http://n.sinaimg.cn/sinacn20111/600/w1920h1080/20190331/0f41-huxwryw5226043.jpg','原创',11,1,1,1,1,1,'2019-03-12 13:42:14','2019-03-13 12:00:08',2,1,'JDK5.0允许java像C语言那样直接用printf()方法来格式化输出\r\nSystem.out.format()功能与printf()一样，可以使用%d,%f等参数。\r\n\r\n使用System.out.format()完成左对齐，补0，千位分隔符，小数点位数，本地化表达','1'),
(3,'Springboot中PageHelper 分页查询使用方法','### 一：导入依赖\r\n\r\n```java\r\n<dependency>\r\n	<groupId>com.github.pagehelper</groupId>\r\n	<artifactId>pagehelper-spring-boot-starter</artifactId>\r\n	<version>1.2.13</version>\r\n</dependency>\r\n```\r\n### 二：配置yml文件中PageHelper的属性\r\n\r\n```java\r\npagehelper:                #分页插件\r\n  helper-dialect: mysql\r\n  reasonable: true\r\n  support-methods-arguments: true\r\n  params:\r\n```\r\n### 三：在controller类中使用，\r\n##### 1.在查询方法上调用PageHelper.startPage()方法，设置分页的页数和每页信息数，\r\n##### 2.将查询出来的结果集用PageInfo的构造函数初始化为一个分页结果对象\r\n##### 3.将分页结果对象存入model，返回前端页面\r\n```java\r\n@GetMapping(\"/types\")\r\npublic String types(@RequestParam(required = false,defaultValue = \"1\",value = \"pagenum\")int pagenum, Model model){\r\n\r\n    PageHelper.startPage(pagenum, 5);  //pagenum：页数，pagesize:每页的信息数\r\n    \r\n    List<Type> allType = typeService.getAllType(); //调用业务层查询方法\r\n    \r\n    PageInfo<Type> pageInfo = new PageInfo<>(allType); //得到分页结果对象\r\n    \r\n    model.addAttribute(\"pageInfo\", pageInfo);  //携带分页结果信息\r\n    \r\n    return \"admin/types\";  //回到前端展示页面\r\n}\r\n```\r\n### 四：前端展示分页（thymeleaf+semantic-ui）,这里ui用自己的就行，比如bootstrap或layui，主要是thymeleaf的使用。\r\n\r\n```java\r\n<table  class=\"ui compact celled teal table\">\r\n  <thead>\r\n  <tr>\r\n    <th></th>\r\n    <th>名称</th>\r\n    <th>操作</th>\r\n  </tr>\r\n  </thead>\r\n  <tbody>\r\n  <tr th:each=\"type, iterStat : ${pageInfo.list}\">\r\n    <td th:text=\"${iterStat.count}\">1</td>\r\n    <td th:text=\"${type.name}\">摸鱼方法</td>\r\n    <td>\r\n      <a href=\"#\" th:href=\"@{/admin/types/{id}/input(id=${type.id})}\" class=\"ui mini teal basic button\">编辑</a>\r\n      <a href=\"#\" th:href=\"@{/admin/types/{id}/delete(id=${type.id})}\" class=\"ui mini red basic button\">删除</a>\r\n    </td>\r\n  </tr>\r\n  </tbody>\r\n  <tfoot>\r\n  <tr>\r\n    <th colspan=\"7\">\r\n      <div class=\"ui mini pagination menu\"  >\r\n        <div class=\"item\"><a th:href=\"@{/admin/types}\">首页</a></div>\r\n        <div class=\"item\"><a th:href=\"@{/admin/types(pagenum=${pageInfo.hasPreviousPage}?${pageInfo.prePage}:1)}\">上一页</a></div>\r\n        <div class=\"item\"><a th:href=\"@{/admin/types(pagenum=${pageInfo.hasNextPage}?${pageInfo.nextPage}:${pageInfo.pages})}\">下一页</a></div>\r\n        <div class=\"item\"><a th:href=\"@{/admin/types(pagenum=${pageInfo.pages})}\">尾页</a></div>\r\n      </div>\r\n      <a href=\"#\" th:href=\"@{/admin/types/input}\" class=\"ui mini right floated teal basic button\">新增</a>\r\n    </th>\r\n  </tr>\r\n  </tfoot>\r\n</table>\r\n\r\n<div class=\"ui segment m-inline-block\">\r\n  <p >当前第<span th:text=\"${pageInfo.pageNum}\"></span>页，总<span th:text=\"${pageInfo.pages}\"></span>页，共<span th:text=\"${pageInfo.total}\"></span>条记录</p>\r\n</div>\r\n```\r\n### 五：效果展示（pagesize设置为5的效果）\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20200310105006168.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQyODA0NzM2,size_16,color_FFFFFF,t_70)\r\n','http://p.qpic.cn/dnfbbspic/0/dnfbbs_dnfbbs_dnf_gamebbs_qq_com_forum_202002_04_082156ifotspmtuzcffycn.jpg/0','原创',10,1,0,1,1,1,'2019-03-14 17:05:58','2022-04-26 16:05:06',1,1,'1.在查询方法上调用PageHelper.startPage()方法，设置分页的页数和每页信息数，\r\n2.将查询出来的结果集用PageInfo的构造函数初始化为一个分页结果对象\r\n3.将分页结果对象存入model，返回前端页面','1,3'),
(4,'thymeleaf语法及使用','## 模板引擎\r\n\r\n简介：模板引擎(这里特指用于Web开发的模板引擎)是为了使用户界面与业务数据(内容)分离而产生的,它可以生成特定格式的文档，用于网站的模板引擎就会生成一个标准的HTML文档。\r\n模板引擎的思想：\r\n![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9pbWFnZXMuY25ibG9ncy5jb20vY25ibG9nc19jb20venltMTk5OS8xNjQ1NDE2L29fMjAwMjExMDk1OTQxMTQucG5n?x-oss-process=image/format,png)\r\nThymeleaf就是SpringBoot给我们推荐的一种模板引擎！\r\n\r\n## Thymeleaf模板引擎\r\n\r\n#### 1.使用Thymeleaf之前的步骤\r\n\r\n 1. Thymeleaf 官网：https://www.thymeleaf.org/\r\n 2. springboot项目直接引入依赖：\r\n```java\r\n<dependency>\r\n   <groupId>org.springframework.boot</groupId>\r\n   <artifactId>spring-boot-starter-thymeleaf</artifactId>\r\n</dependency>\r\n```\r\n3.非springboot项目直接引入依赖：\r\n\r\n```java\r\n<dependency>\r\n    <groupId>org.thymeleaf</groupId>\r\n    <artifactId>thymeleaf</artifactId>\r\n    <version>2.1.4</version>\r\n</dependency>\r\n```\r\n  4.在thymeleaf的配置类ThymeleafProperties中我们可以发现：thymeleaf配置的默认前缀为：\"classpath:/templates/\"，默认后缀为：\".html\"，只要把html页面放在这个路径下，\r\n\r\nthymeleaf就可以帮我们自动渲染。\r\n\r\n```java\r\npublic class ThymeleafProperties {\r\n    private static final Charset DEFAULT_ENCODING;\r\n    public static final String DEFAULT_PREFIX = \"classpath:/templates/\";\r\n    public static final String DEFAULT_SUFFIX = \".html\";\r\n    private boolean checkTemplate = true;\r\n    private boolean checkTemplateLocation = true;\r\n    private String prefix = \"classpath:/templates/\";\r\n    private String suffix = \".html\";\r\n    private String mode = \"HTML\";\r\n...\r\n}\r\n```\r\n如图为用idea创建的springboot的项目结构：将html页面放在resources/templates中即可。\r\n![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9pbWFnZXMuY25ibG9ncy5jb20vY25ibG9nc19jb20venltMTk5OS8xNjQ1NDE2L29fMjAwMjExMDk1OTExMTMucG5n?x-oss-process=image/format,png)\r\n#### 2.Thymeleaf语法简单使用（th:text, th:utext, th:each）\r\n编写一个controller实现跳转到一个html页面，通过Model对象携带数据\r\n\r\n```java\r\n@Controller\r\npublic class HelloController {\r\n\r\n    @RequestMapping(\"/success\")\r\n    public String success(Model model){\r\n        //存入数据\r\n        model.addAttribute(\"msg\",\"<h1>Hello</h1>\");\r\n        model.addAttribute(\"users\", Arrays.asList(\"小红\", \"小米\",\"小白\"));\r\n        //classpath:/templates/success.html\r\n        return \"success\";\r\n    }\r\n}\r\n```\r\nsuccess.html\r\n\r\n```java\r\n<!DOCTYPE html>\r\n<html lang=\"en\" xmlns:th=\"http://www.thymeleaf.org\">\r\n<head>\r\n    <meta charset=\"UTF-8\">\r\n    <title>Title</title>\r\n</head>\r\n<body>\r\n    <h1>success</h1>\r\n\r\n    <!--Thymeleaf语法：th:text就是将div中的内容设置为它指定的值-->\r\n\r\n    <div th:text=\"${msg}\">你好</div>\r\n    <!--utext：会解析html，显示相应的效果-->\r\n    <div th:utext=\"${msg}\">你好</div>\r\n    <!--each：遍历-->\r\n    <h3 th:each=\"user:${users}\" th:text=\"${user}\"></h3>\r\n\r\n</body>\r\n</html>\r\n```\r\n通过http://localhost:8080/success路径访问到success.html页面，同时成功显示数据：![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9pbWFnZXMuY25ibG9ncy5jb20vY25ibG9nc19jb20venltMTk5OS8xNjQ1NDE2L29fMjAwMjExMTEyOTE3MTUucG5n?x-oss-process=image/format,png)\r\n#### 3.Thymeleaf基本语法（属性和表达式）\r\n**Thymeleaf标准表达式**\r\n   \r\n\r\n 1. 变量表达式：**${ }**：用于前端获取后端传递的变量值\r\n\r\n   \r\n\r\n 1. 选择表达式：***{ }**：用于绑定一个对象的属性\r\n\r\n   \r\n\r\n 1. URL链接表达式：**@{ }**：用于链接\r\n\r\n    \r\n\r\n 1. 条件表达式：**三目运算符（表达式 ？值（then）：值（else））**\r\n\r\n    \r\n\r\n\r\n    \r\n**Thymeleaf属性标签：**\r\n![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9pbWFnZXMuY25ibG9ncy5jb20vY25ibG9nc19jb20venltMTk5OS8xNjQ1NDE2L29fMjAwMjExMTEzMTAxMTYucG5n?x-oss-process=image/format,png)\r\n\r\n \r\n\r\n','http://n.sinaimg.cn/sinacn20111/600/w1920h1080/20190331/0f41-huxwryw5226043.jpg','原创',22,1,1,1,1,1,'2020-03-14 17:01:31','2022-05-17 18:41:05',4,1,'简介：模板引擎(这里特指用于Web开发的模板引擎)是为了使用户界面与业务数据(内容)分离而产生的,它可以生成特定格式的文档，用于网站的模板引擎就会生成一个标准的HTML文档。Thymeleaf就是SpringBoot给我们推荐的一种模板引擎！','1,3'),
(5,'利用Set集合去重','### Set集合特点:\r\n#### ①     一次只存一个元素,\r\n\r\n#### ②     不能存储重复的元素\r\n\r\n#### ③     存储顺序和取出来的顺序不一定一致不能存储重复的元素\r\n\r\n可以利用②这一特点，完成去重的功能。\r\n#### 一：Set集合去掉List集合中重复元素\r\n\r\n```java\r\npublic static void main(String[] args) {\r\n	\r\n	//利用set集合 去除ArrayList集合中的重复元素\r\n	ArrayList<String> list = new ArrayList<>();\r\n	list.add(\"1\");\r\n    list.add(\"1\");\r\n    list.add(\"2\");\r\n    list.add(\"2\");\r\n    list.add(\"3\");\r\n    list.add(\"3\");\r\n    list.add(\"4\");\r\n    list.add(\"4\");\r\n    System.out.println(\"去重前的List集合：\"+list);\r\n    \r\n	Set<String> set = new HashSet<>();\r\n	set.addAll(list);\r\n	System.out.println(\"Set集合：\"+set);\r\n	\r\n	list.clear();            // 清空原有元素 放入被list去重后的元素\r\n	list.addAll(set);\r\n	System.out.println(\"去重后的List集合：\"+list);\r\n}\r\n```\r\n运行结果：\r\n\r\n```java\r\n去重前的List集合：[1, 1, 2, 2, 3, 3, 4, 4]\r\nSet集合：[1, 2, 3, 4]\r\n去重后的List集合：[1, 2, 3, 4]\r\n```\r\n\r\n#### 二：Set集合去掉字符串中重复子串\r\n\r\n```java\r\npublic static void main(String[] args) {\r\n	String str = \"aaab\";\r\n	System.out.println(\"字符串aaab 有非空子串a, b, aa, ab, aaa, aab, aaab，一共 7 个\");\r\n	\r\n	Set<String> set = new HashSet<String>();\r\n	for (int step = 0; step <= str.length() - 1; step++) {\r\n		//扫描全部子串\r\n		for (int begin = 0, end = 1 + step; end <= str.length(); begin++, end++) {     \r\n			String kid = str.substring(begin, end);   //截取字符串子串\r\n			set.add(kid);			//将子串放入set集合，完成去重\r\n		}\r\n	}\r\n	System.out.println(\"去除重复子串后的全部子串有：\"+set.size()+\"个\");\r\n	System.out.println(\"分别是：\" + set);\r\n}\r\n```\r\n运行结果：\r\n```java\r\n字符串aaab 有非空子串a, b, aa, ab, aaa, aab, aaab，一共 7 个\r\n去除重复子串后的全部子串有：7个\r\n分别是：[aa, aaa, a, ab, b, aab, aaab]\r\n```\r\n\r\n','http://p.qpic.cn/dnfbbspic/0/dnfbbs_dnfbbs_dnf_gamebbs_qq_com_forum_202002_04_082156ifotspmtuzcffycn.jpg/0','原创',5,1,1,1,1,1,'2020-03-13 11:21:46','2020-03-14 16:19:35',1,1,'Set集合特点:\r\n① 一次只存一个元素,\r\n② 不能存储重复的元素\r\n③ 存储顺序和取出来的顺序不一定一致不能存储重复的元素','2'),
(6,'mybatis根据日期查询、查询日期并返回','### 方法：\r\n\r\n#### 1.查询日期，返回日期字符串\r\n```handlebars\r\n//查询所有博客对应的年份，返回一个集合\r\nList<String> findGroupYear();  \r\n```\r\n#### 2.根据日期查询\r\n```handlebars\r\n//根据年份查询博客信息\r\nList<Blog> findByYear(@Param(\"year\") String year);  \r\n```\r\n\r\n\r\n### mybatis映射:\r\n\r\n\r\n```java\r\n<select id=\"findGroupYear\" resultType=\"String\">\r\n    select DATE_FORMAT(b.update_time, \'%Y\') from t_blog b\r\n</select>\r\n\r\n\r\n<select id=\"findByYear\" resultType=\"Blog\">\r\n    select b.title, b.update_time, b.id, b.flag\r\n    from t_blog b\r\n    where DATE_FORMAT(b.update_time, \'%Y\') = #{year}\r\n</select>\r\n```\r\n\r\n## 总结：\r\n**DATE_FORMAT(date,format)：date表示日期，format表示显示的格式。**\r\n**该方法可以把date类型转换为String类型的字符串**\r\n','http://p.qpic.cn/dnfbbspic/0/dnfbbs_dnfbbs_dnf_gamebbs_qq_com_forum_202002_04_082156ifotspmtuzcffycn.jpg/0','原创',7,1,1,1,1,1,'2020-03-21 11:54:41','2020-03-21 15:33:02',2,1,'mybatis根据日期查询、查询日期并返回\r\n方法：\r\n1.查询日期，返回日期字符串','5'),
(11,'安东尼经典语录','我怕你说话，我怕你沉默。 \r\n我怕你来到，我怕你离开。 \r\n我怕抓紧你，我怕放走你。 \r\n我怕你看到我，我怕你无视我。 \r\n我怕你想念我，我怕你忘记我。 \r\n我怕你过于依赖我，我怕你不再需要我。 \r\n我怕你爱我，我怕你不爱我。 \r\n我怕你太爱我，我怕你不够爱我。\r\n\r\n不管昨夜经历了怎样的泣不成声 早晨醒来这个城市依然车水马龙\r\n\r\n不要让那个喜欢你的人 撕心裂肺地为你哭那么一次 因为 你能把他伤害到那个样子的机会 只有一次 那一次以后 你就从不可或缺的人变成可有可无的人了 即使他还爱你 可是 总有一些东西真的改变了\r\n\r\n应该趁着年轻 和喜欢的人一起 制造些比夏天还要温暖的事\r\n\r\n风不懂云的漂泊。天不懂雨的落魄。眼不懂泪的懦弱。所以你不懂我的选择。也可以不懂我的难过。不是每一个人都一定要快乐。不是每一种痛都一定要诉说\r\n\r\n后来，我终于能接受，我们不会再在一起这个实事。我想我唯一能做的就是，继承那些，你拥有的让我着迷的品质，好好地生活下去。\r\n\r\n如果你喜欢的人不喜欢你，那么就算全世界的人都喜欢你，还是会觉得孤独吧。If the person you like doesn\'t like you ,wouldn\'t it still be lonely even if the whole world loves you.\r\n\r\n那些 我们一直惴惴不安 又充满好奇的未来 会在心里隐隐约约地觉得它们是明亮的\r\n\r\n人生，总会有不期而遇的温暖，和生生不息的希望。\r\n\r\n其实我一直相信 等你出现的时候我就知道是你\r\n\r\n我谈过最长的恋爱，就是自恋，我爱自己，没有情敌。\r\n\r\n不知道如何爱你，看着你，是我唯一的方式。I don\'t know how to love you ,looking at you is the only way i know\r\n\r\n我所知道的关于你的事情 就只有天气预报了\r\n\r\n我觉得 我们俩之间就像喝酒 我干杯 你随意\r\n\r\n玫瑰当然爱小王子 \r\n不过当你真的喜欢一个人的时候 就会想很多 会很容易办蠢事 说傻话 \r\n更别说 那个人像小王子那么可爱 \r\n玫瑰很温柔 其实她只是不知所措罢了 \r\n至于小王子 他还太小了 不明白玫瑰的温柔 \r\n他的离开也许并不是坏事\r\n\r\n最喜欢早上，好像什么都可以重新开始，中午的时候就开始觉得忧伤，晚上最难度过。\r\n\r\nI know that one day I will forgot you. \r\nI don\'t have anticipation. \r\nI don\'t feel sad. \r\nI just know, that\'s all.\r\n\r\n长大了 总有那么一两次机会 你会为了喜欢的人 跑那么一跑 因为 如果是对的人的话 走路真的来不及\r\n\r\n很久以后，那些好极了和糟透了的时刻我们都会忘记，唯一真实和难忘的是，我们抬头挺胸走过的人生。\r\n\r\n不论是我的世界车水马龙繁华盛世 还是它们都瞬间消失化为须臾 我都会坚定地走向你 不慌张 不犹豫','http://p.qpic.cn/dnfbbspic/0/dnfbbs_dnfbbs_dnf_gamebbs_qq_com_forum_202002_04_082156ifotspmtuzcffycn.jpg/0','转载',4,1,1,1,1,0,'2020-03-22 19:36:45','2022-05-17 20:06:31',3,1,'不论是我的世界车水马龙繁华盛世\r\n 还是它们都瞬间消失化为须臾 \r\n我都会坚定地走向你 不慌张 不犹豫','4,5'),
(55,'markdown语法解析','# 解决方案，使用commonmark-java插件（地址：[commonmark-java插件地址](https://github.com/commonmark/commonmark-java \"commonmark-java插件地址\")）\r\n# 依赖\r\n```xml\r\n<!--将博客内容解析成html的插件及其扩展-->\r\n		<dependency>\r\n			<groupId>org.commonmark</groupId>\r\n			<artifactId>commonmark</artifactId>\r\n			<version>0.18.1</version>\r\n		</dependency>\r\n\r\n		<dependency>\r\n			<groupId>org.commonmark</groupId>\r\n			<artifactId>commonmark-ext-gfm-tables</artifactId>\r\n			<version>0.18.1</version>\r\n		</dependency>\r\n\r\n		<dependency>\r\n			<groupId>org.commonmark</groupId>\r\n			<artifactId>commonmark-ext-heading-anchor</artifactId>\r\n			<version>0.18.1</version>\r\n		</dependency>\r\n```\r\n\r\n# 封装工具类(https://github.com/commonmark/commonmark-java)\r\n```java\r\n/**\r\n * Created by liujian on 2022/5/2.\r\n * 将markdown语法解析为html页面的工具类，使用commonmark-java插件\r\n */\r\npublic class MarkdownUtils {\r\n\r\n    /**\r\n     * markdown格式转换成HTML格式\r\n     * @param markdown\r\n     * @return\r\n     */\r\n    public static String markdownToHtml(String markdown) {\r\n        Parser parser = Parser.builder().build();\r\n        Node document = parser.parse(markdown);\r\n        HtmlRenderer renderer = HtmlRenderer.builder().build();\r\n        return renderer.render(document);\r\n    }\r\n\r\n    /**\r\n     * 增加扩展[标题锚点，表格生成]\r\n     * Markdown转换成HTML\r\n     * @param markdown\r\n     * @return\r\n     */\r\n    public static String markdownToHtmlExtensions(String markdown) {\r\n        //h标题生成id\r\n        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());\r\n        //转换table的HTML\r\n        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());\r\n        Parser parser = Parser.builder()\r\n                .extensions(tableExtension)\r\n                .build();\r\n        Node document = parser.parse(markdown);\r\n        HtmlRenderer renderer = HtmlRenderer.builder()\r\n                .extensions(headingAnchorExtensions)\r\n                .extensions(tableExtension)\r\n                .attributeProviderFactory(new AttributeProviderFactory() {\r\n                    public AttributeProvider create(AttributeProviderContext context) {\r\n                        return new CustomAttributeProvider();\r\n                    }\r\n                })\r\n                .build();\r\n        return renderer.render(document);\r\n    }\r\n\r\n    /**\r\n     * 处理标签的属性\r\n     */\r\n    static class CustomAttributeProvider implements AttributeProvider {\r\n        @Override\r\n        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {\r\n            //改变a标签的target属性为_blank\r\n            if (node instanceof Link) {\r\n                attributes.put(\"target\", \"_blank\");\r\n            }\r\n            if (node instanceof TableBlock) {\r\n                attributes.put(\"class\", \"ui celled table\");\r\n            }\r\n        }\r\n    }\r\n\r\n\r\n    public static void main(String[] args) {\r\n        String table = \"| hello | hi   | 哈哈哈   |\\n\" +\r\n                \"| ----- | ---- | ----- |\\n\" +\r\n                \"| 斯维尔多  | 士大夫  | f啊    |\\n\" +\r\n                \"| 阿什顿发  | 非固定杆 | 撒阿什顿发 |\\n\" +\r\n                \"\\n\";\r\n        String a = \"[imCoding 爱编程](https://github.com/little111cow)\";\r\n        System.out.println(markdownToHtmlExtensions(a));\r\n    }\r\n}\r\n\r\n```\r\n# 使用说明\r\n输入markdown类型的字符串，调用MarkdownUtils工具类的静态方法markdownToHtmlExtensions或者markdownToHtml解析为HTML语法。','https://pic.quanjing.com/19/x5/QJ9106651790.jpg?x-oss-process=style/show_794s','原创',37,1,1,1,1,1,'2022-05-02 17:33:01','2022-05-02 21:47:10',1,1,'解析markdown语法','1,3,4'),
(56,'博客还存在的问题','## 1.前台首页的搜索清空功能（前端功能）还不能使用(已经解决，为按钮添加onclick事件，通过绑定document.getElementById(‘searchBtn’).value = \'\'事件解决)\r\n\r\n## 2.后台博客管理条件查询时分页还存在问题\r\n\r\n## 3.数据库查询得到的日期有问题，归档的日期不对(已经解决，原因是前端页面的日期格式转化存在问题，thmleaf模板引擎的错误使用)\r\n\r\n## 4.博客评论功能待实现(实现了评论留言和一级回复，不支持多级回复)\r\n\r\n## 5.博客的前端根据tag分类查询时的分页还有问题\r\n\r\n# 总体功能已经实现，还有一些bug需要修复','https://pic.quanjing.com/19/x5/QJ9106651790.jpg?x-oss-process=style/show_794s','原创',28,1,1,1,1,0,'2022-05-10 20:57:03','2022-05-17 20:04:44',5,1,'博客存在的问题','1,5'),
(57,'java','java','https://cdn.pixabay.com/photo/2022/02/13/12/07/seagull-7011013__480.jpg','原创',0,1,1,1,1,0,'2022-05-17 19:55:19','2022-05-17 20:07:24',1,1,'描述','1'),
(58,'啊哈','答复','/files/avatar.jpg','原创',0,1,1,1,1,0,'2022-05-17 19:55:53','2022-05-17 20:07:16',1,1,'enen','1'),
(59,'而被捕','大','https://www.keaidian.com/uploads/allimg/190424/24110307_19.jpg','原创',0,1,1,1,1,0,'2022-05-17 19:56:17','2022-05-17 20:07:09',1,1,'描述','1,3');

/*Table structure for table `t_blog_tags` */

DROP TABLE IF EXISTS `t_blog_tags`;

CREATE TABLE `t_blog_tags` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(20) DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `t_blog_tags` */

insert  into `t_blog_tags`(`id`,`tag_id`,`blog_id`) values 
(1,1,2),
(2,1,3),
(3,3,3),
(4,1,4),
(5,3,4),
(6,2,5),
(7,5,6),
(8,4,11),
(9,5,11);

/*Table structure for table `t_comment` */

DROP TABLE IF EXISTS `t_comment`;

CREATE TABLE `t_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `blog_id` bigint(20) DEFAULT NULL,
  `parent_comment_id` bigint(20) NOT NULL DEFAULT '-1',
  `admincomment` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `t_comment` */

insert  into `t_comment`(`id`,`nickname`,`email`,`content`,`avatar`,`create_time`,`blog_id`,`parent_comment_id`,`admincomment`) values 
(1,'littlecow','whu_jianliu@163.com','博主的评论','/files/avatar.jpg','2022-05-17 19:19:57',2,-1,1),
(2,'小白','663@163.com','很好的博客','https://cdn.pixabay.com/photo/2022/04/20/01/23/wedding-7144049__480.jpg','2022-05-17 19:20:30',2,1,0),
(3,'littlecow','whu_jianliu@163.com','博主评论','/files/avatar.jpg','2022-05-17 19:22:07',4,-1,1),
(4,'小白','6638876@163.com','没错','https://cdn.pixabay.com/photo/2022/05/08/12/51/flower-7182055__340.jpg','2022-05-17 19:23:32',4,3,0),
(5,'小红','66386@163.com','@小白 你说得对','https://scpic.chinaz.net/files/pic/pic9/201910/zzpic20739.jpg','2022-05-17 19:24:18',4,-1,0),
(6,'小白','663@163.com','@小红嘻嘻','https://cdn.pixabay.com/photo/2022/04/20/01/23/wedding-7144049__480.jpg','2022-05-17 19:32:47',4,-1,0);

/*Table structure for table `t_file` */

DROP TABLE IF EXISTS `t_file`;

CREATE TABLE `t_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT '../../static/files',
  `file_name` varchar(255) NOT NULL,
  `file_suffix` varchar(20) NOT NULL,
  `upload_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='保存文件信息';

/*Data for the table `t_file` */

insert  into `t_file`(`id`,`path`,`file_name`,`file_suffix`,`upload_time`) values 
(6,'D:\\blog\\target\\classes\\static\\files','moderncvcolorblue','sty','2022-05-15 18:25:43'),
(8,'D:\\blog\\target\\classes\\static\\files','avatar','jpg','2022-05-15 20:40:58'),
(10,'D:\\blog\\target\\classes\\static\\files','刘健_信息体验工程师_武汉大学&&西南交通大学','pdf','2022-05-15 18:28:06');

/*Table structure for table `t_tag` */

DROP TABLE IF EXISTS `t_tag`;

CREATE TABLE `t_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `t_tag` */

insert  into `t_tag`(`id`,`name`) values 
(1,'后端'),
(2,'springboot'),
(3,'java'),
(4,'javase'),
(5,'杂谈');

/*Table structure for table `t_type` */

DROP TABLE IF EXISTS `t_type`;

CREATE TABLE `t_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `t_type` */

insert  into `t_type`(`id`,`name`) values 
(1,'java'),
(2,'python'),
(3,'c++'),
(4,'c'),
(5,'记事笔记');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) NOT NULL DEFAULT '',
  `email` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `type` int(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `vcode` char(4) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`nickname`,`username`,`password`,`email`,`avatar`,`type`,`create_time`,`update_time`,`vcode`) values 
(1,'littlecow','Admin','1234567','whu_jianliu@163.com','/files/avatar.jpg',1,'2020-03-08 18:25:26','2022-05-15 15:28:16','oiDH');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
