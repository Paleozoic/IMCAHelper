# Develop
https://www.felixroske.de/page/programmierung/index.html
https://github.com/roskenet/springboot-javafx-support


# 全局热键
jintellitype

# 模拟键鼠
Robot类，但会被游戏平台拦截

# 驱动级别键鼠模拟
WinIO x64设计到签名  暂时不实现

# package
https://github.com/javafx-maven-plugin/javafx-maven-plugin
```shell
# 打包得到可执行jar
# ps: 一下命令如果无法生成，可先用ide run一下，将文件编译，再执行mvn jfx:build-jar
mvn jfx:jar
# 打包得到可执行exe
mvn jfx:build-native
```
打包本地程序，按提示操作:
```$xslt
[WARNING] Class with name com.maxplus1.IMCAHelper.IMCAHelperStart was not found inside provided jar files!! JavaFX-application might not be working !!
[INFO] Skipping 'EXE 安装程序' because of configuration error '找不到 InnoSetup 编译器 (iscc.exe)。'
Advice to fix:   从 http://www.jrsoftware.org 下载 InnoSetup 5 或更高版本, 然后将其添加到 PATH。
[INFO] Skipping 'MSI 安装程序' because of configuration error '找不到 WiX 工具 (light.exe, candle.exe)。'
Advice to fix:   从 http://wix.sf.net 下载 WiX 3.0 或更高版本, 然后将其添加到 PATH。
[INFO] Skipping 'WebStart JNLP Bundler' because of configuration error 'No OutFile Specificed'
Advice to fix: Please specify the name of the JNLP Outut file in 'jnlp.outfile'
```


# 管理员运行
## Jar的管理员运行
通过管理员运行，可以发现信号不被屏蔽。
由于没有打包成exe，所以需要安装jre。并且是Java8以上版本。
管理员运行的方式：
新建bat文件，里面写上
```shell
# java -jar  程序全路径
# 例如
@echo off
E:
cd E:\Code\MyProject\IMCAHelper\target\jfx\app
start javaw -jar IMCAHelper.jar
exit
```
然后新建快捷方式，右键管理员运行。

## exe的管理员运行
exe打包失败了……略过。