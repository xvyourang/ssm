<trim>标签
<trim prefix="WHERE" prefixoverride="AND |OR">
prefix：前缀　　　　　　
prefixoverride：去掉第一个and或者是or

<trim prefix="set" suffixoverride="," suffix=" where id = #{id} ">
suffix：后缀
suffixoverride：去掉最后一个逗号（也可以是其他的标记，就像是上面前缀中的and一样）




trim 元素可以给自己包含的内容加上前缀（prefix）或加上后缀（suffix），也可以把包含内容的首部（prefixOverrides）或尾部（suffixOverrides）某些内容移除。

distinct  返回唯一结果


useGeneratedKeys="true" keyProperty="id"  插入后返回主键id

1.物理分页
物理分页依赖的是某一物理实体，这个物理实体就是数据库，比如MySQL数据库提供了limit关键字，程序员只需要编写带有limit关键字的SQL语句，数据库返回的就是分页结果。

2.逻辑分页
逻辑分页依赖的是程序员编写的代码。数据库返回的不是分页结果，而是全部数据，然后再由程序员通过代码获取分页数据，常用的操作是一次性从数据库中查询出全部数据并存储到List集合中，因为List集合有序，再根据索引获取指定范围的数据。



可以不使用表对应的bean对象而使用LinkedMap来存放数据，这样做的优点是查询时不用担心数据库表结构改变，但是在修改、更新、删除时会比较麻烦。