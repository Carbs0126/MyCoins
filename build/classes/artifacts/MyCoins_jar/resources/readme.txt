
注意事项：
    1.工程导入时，选择gradle.build
    2.生成的jar在build/classes/artifacts/下
    3.将resources文件夹拷贝到jar文件的同级目录下

问题1：
    执行jar时如果显示没有找到清单
解决1：
    META-INF/MANIFEST.MF 中最后一行添加：Main-Class: ui.MainFrame

问题2：
    Error:(233, 20) java: -source 1.5 中不支持 switch 中存在字符串
    (请使用 -source 7 或更高版本以允许 switch 中存在字符串)

解决2:
    Modules --->Sources ---> Language level 改为 7.0就ok了。