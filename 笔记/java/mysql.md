
��ҳ��ѯ��� �����ٵ������pagehelper
���ݶ�����ʹ�ü̳�BaseCondition(��ҳ��ͷ�ҳ��)��ʵ��




//����mysql
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");



�������ݿ⣺
��һ������������//JAVA���ݿ�
Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
�ڶ��������˺����룺
Properties props = new Properties(); 
props.put("user", "admin");
props.put("password", "123");
������ ���Ӵ������ݿ⣺
Connectionconn = DriverManager.getConnection("jdbc:derby:SQLname;create=true",props);

���ύ����
conn.setAutoCommit(false);
 ������
 Statement s = conn.createStatement(); //��ȡʵ�� 
 s.execute("create table user_info (user_id int,user_name varchar(50))");
 conn.commit();//�ύ����
  ����������ݣ�
  Statement s = conn.createStatement();
  boolean mark = s.execute("insert into user_info values(1, '����')");
  conn.commit();
   ��ѯ���ݣ�
 ResultSet rs = s.executeQuery("SELECT user_id,user_name  FROM user_info");//���������
 while (rs.next()) {      
 System.out.println(rs.getInt(1));   
 System.out.println(rs.getString(2));       
 }
  �޸����ݣ�
  Statement s = conn.createStatement();
  boolean mark = s.execute("update  user_info set user_name= '����' where user_id=1");
  conn.commit();
  
  ɾ�����ݣ�Statement s = conn.createStatement();
 boolean mark = s.execute("delete from user_info  where user_id=1");
 conn.commit();
 
/**********************************************************/
��дΪ��ʽ		[]��ʾ��ʡ��
 
 
��ѯ���
 
SELECT row_1,row_n, FROM table_0,table_n��WHERE factor_0,factor_n

���������

CREATE TABLE table (row_1 type,row_n type) 

type check(
char(n)  		�Զ��nλ
varchar(n)		���Զ��nλ
smallint		��+-��32768
int/integer		��+-��2147483648
decimal(p[,q])	����p��10��������С��λq
float			
binary[(n)]		nΪλ2����
var binary[(n)] 
image			2����
datetime		��-��-��
time 			ʱ:��:��
)


������
CREATE TABLE SS
(��Ŀ1 char��n�� PRIMARY KEY��������//����ĩβ(����1������2),
 ��Ŀ2 varchar��30��NOT NULL��������Ĭ��0��SMALLINT DEFAUT(0)��
 ��Ŀ3 SSEX  char��2��CHECK(SSEX IN (���С�����Ů��),
 FOREIGN KEY(C#) PEFERENCES C(C#));


�����

����
SP_RENAME SS,SSS;
������
ALTER TABLE S ADD A CHAR(n);
ɾ����
ALTER TABLE S DROP COLUMN A CASCADE(ȫɾ)/RESTRICT��

�޸���
ALTER TABLE  SS ALTER COLUMN A VARCHAR(N);
���� ɾ����
DROP TABLE S CASCADE(ȫɾ)/RESTRICT��

���ݲ���
INTSERT INTO SC
(S#,C#,GRADE)
VALUES('���ݡ��������ݡ��������ݡ�)��

�޸�
UPDATE S
SET SNAME='������', ��
WHERE S#='200401003';��������


ɾ��
DELETE FROM S
WHERE S#='3236';(������û�о���ȫɾ)

��ѯ
SELECT A��*��ȫ����
FROM S
WHEAE ����
ORDER BY A ASC(��)/DESC������ //��A����

BETWEEN����֮��


GROUP BY A //��A����
HAVING(����)����������


�������

SELECT S.S#,SNAME
FROM S,SC
WHERE S.S#=SC.S#ADN C#='C40101';


��
UNION(ALL)

��
INTERSECT
��
EXCEPT






�����
SELRCT ԭ�� AS ����
FORM C��

select distinct  ����Ψһ���

 