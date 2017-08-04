##DBDoc##
数据库文档生成器。数据库目前支持MySQL，文档为html。

##生成可执行jar包##
本项目为maven工程，可通过以下命令生成可执行jar包：

```Bash
mvn compile assembly:single
```

该命令将会在 `target/` 目录下生成jar包。

##如何执行##
以java -jar形式传递参数执行, 通过`java -jar dbdoc.jar --help` 打印帮助信息。
以下是示例:
```Bash
java -jar dbdoc.jar \
-t MYSQL \
-s 127.0.0.1:3306 \
-n test \
-u root \
-p 123456 \
-d HTML
```

##License##
[MIT](LICENSE)