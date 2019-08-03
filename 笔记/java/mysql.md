
分页查询设计 数据少的情况用pagehelper
数据多的情况使用继承BaseCondition(含页码和分页数)来实现




//连接mysql
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");



创建数据库：
第一步加载驱动：//JAVA数据库
Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
第二部设置账号密码：
Properties props = new Properties(); 
props.put("user", "admin");
props.put("password", "123");
第三部 连接创建数据库：
Connectionconn = DriverManager.getConnection("jdbc:derby:SQLname;create=true",props);

不提交事务
conn.setAutoCommit(false);
 创建表：
 Statement s = conn.createStatement(); //获取实例 
 s.execute("create table user_info (user_id int,user_name varchar(50))");
 conn.commit();//提交事务
  往表添加数据：
  Statement s = conn.createStatement();
  boolean mark = s.execute("insert into user_info values(1, '张三')");
  conn.commit();
   查询数据：
 ResultSet rs = s.executeQuery("SELECT user_id,user_name  FROM user_info");//遍历结果集
 while (rs.next()) {      
 System.out.println(rs.getInt(1));   
 System.out.println(rs.getString(2));       
 }
  修改数据：
  Statement s = conn.createStatement();
  boolean mark = s.execute("update  user_info set user_name= '张三' where user_id=1");
  conn.commit();
  
  删除数据：Statement s = conn.createStatement();
 boolean mark = s.execute("delete from user_info  where user_id=1");
 conn.commit();
 
/**********************************************************/
大写为格式		[]表示可省略
 
 
查询语句
 
SELECT row_1,row_n, FROM table_0,table_n　WHERE factor_0,factor_n

创建表语句

CREATE TABLE table (row_1 type,row_n type) 

type check(
char(n)  		自动填到n位
varchar(n)		不自动填到n位
smallint		（+-）32768
int/integer		（+-）2147483648
decimal(p[,q])	长度p的10进制数，小数位q
float			
binary[(n)]		n为位2进制
var binary[(n)] 
image			2进制
datetime		年-月-日
time 			时:分:秒
)


创建表
CREATE TABLE SS
(项目1 char（n） PRIMARY KEY（主键）//可在末尾(主键1，主键2),
 项目2 varchar（30）NOT NULL，（设置默认0：SMALLINT DEFAUT(0)）
 项目3 SSEX  char（2）CHECK(SSEX IN (‘男’，‘女’),
 FOREIGN KEY(C#) PEFERENCES C(C#));


表操作

改名
SP_RENAME SS,SSS;
增加列
ALTER TABLE S ADD A CHAR(n);
删除列
ALTER TABLE S DROP COLUMN A CASCADE(全删)/RESTRICT；

修改列
ALTER TABLE  SS ALTER COLUMN A VARCHAR(N);
撤销 删除表
DROP TABLE S CASCADE(全删)/RESTRICT；

数据插入
INTSERT INTO SC
(S#,C#,GRADE)
VALUES('数据’，‘数据’，‘数据’)；

修改
UPDATE S
SET SNAME='新数据', 等
WHERE S#='200401003';（条件）


删除
DELETE FROM S
WHERE S#='3236';(条件，没有就是全删)

查询
SELECT A（*是全部）
FROM S
WHEAE 条件
ORDER BY A ASC(升)/DESC（降） //按A排序

BETWEEN介于之间


GROUP BY A //按A分组
HAVING(条件)；分组条件


多表连接

SELECT S.S#,SNAME
FROM S,SC
WHERE S.S#=SC.S#ADN C#='C40101';


并
UNION(ALL)

交
INTERSECT
差
EXCEPT






起别名
SELRCT 原名 AS 新名
FORM C；

select distinct  返回唯一结果

 