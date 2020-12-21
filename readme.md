#自动流水编码生成（java版）

1、调用代码：
AutoCodeBuilder builder=new AutoCodeBuilder("SendCarFormNo");
String code = builder.Build();
System.out.println(code);

2、表sys_autocode配置情况：
字段名：AutoCodeId      Expression                  Description
记录值：'SendCarFormNo' 'PD{dt:yyyyMMdd}{seq:3}'    '派车单号'

3、出输情况（如果当前日期为：2016-11-28）：
第一次运行：PD20161128001
第二次运行：PD20161128002
