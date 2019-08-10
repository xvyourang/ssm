

一.先安装jdk

1.下载解压后配置环境变量

编辑环境变量文件

vim /etc/profile

增加环境变量

```
export JAVA_HOME=/home/xyr/Public/jdk8
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$CLASSPATH
export JAVA_PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin
export PATH=$PATH:${JAVA_PATH}
```

## 重启机器或执行命令 ：source /etc/profile

sudo shutdown -r now

查看jdk是否安装成功

java -version

二. 安装zookeeper

下载压缩包解压

 进入创建data文件夹

在conf文件夹下创建配置文件zoo.cfg

dataDir 为刚刚创建的data文件夹的位置

```
tickTime = 2000
dataDir = /home/xyr/Public/zookeeper/data
clientPort = 2181
initLimit = 5
syncLimit = 2
```

### 步骤2.4：启动ZooKeeper服务器

进入zk安装目录的bin目录，使用命令启动

./zkServer.sh start

### 步骤2.5：启动CLI

./zkCli.sh



关闭防火墙

service iptables stop

### 停止ZooKeeper服务器

连接服务器并执行所有操作后，可以使用以下命令停止zookeeper服务器。

```
$ bin/zkServer.sh stop
```