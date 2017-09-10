--增加字段
declare
  cnt integer;
begin
	select count(0)
      into cnt	
	  from user_tab_columns a
	  where a.Table_name = upper('AMS_tableName')
	  and a.COLUMN_NAME in(
	  upper('BK1'),
	  upper('BK2'),
	  upper('BK3'),
	  upper('BK4'),
	  upper('BK5'),
	  upper('BK6'),
	  upper('BK7'),
	  upper('BK8'),
	  upper('BK9'),
	  upper('BK10'),
	  upper('BK11'),
	  upper('BK12'),
	  upper('BK13')
);	   
    if cnt = 0 then
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK1 INTEGER  ';
       execute immediate 'comment on column AMS_tableName.BK1 is  ''备用字段1:''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK2 nvarchar2(128)  ';
       execute immediate 'comment on column AMS_tableName.BK2 is  ''备用字段2''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK3 varchar2(128) default ''1'' ';
       execute immediate 'comment on column AMS_tableName.BK3 is  ''备用字段3:1，正常；2，废弃''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK4 NUMBER unique NOT NULL';
       execute immediate 'comment on column AMS_tableName.BK4 is  ''备用字段4:字段唯一约束''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK5 NUMBER(15,6)  ';
       execute immediate 'comment on column AMS_tableName.BK5 is  ''备用字段5''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK6 DATE  ';
       execute immediate 'comment on column AMS_tableName.BK6 is  ''备用字段6''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK7 clob  ';
       execute immediate 'comment on column AMS_tableName.BK7 is  ''备用字段7''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK8 date default sysdate ';
       execute immediate 'comment on column AMS_tableName.BK8 is  ''备用字段8:默认系统时间''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK9 INTEGER  ';
       execute immediate 'comment on column AMS_tableName.BK9 is  ''备用字段9:I代表INTEGER''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK10 NVARCHAR2(18) default ''1'' ';
       execute immediate 'comment on column AMS_tableName.BK10 is  ''备用字段10:C代表nvarchar2类型''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK11 NUMBER  ';
       execute immediate 'comment on column AMS_tableName.BK11 is  ''备用字段11:N代表NUMBER类型''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK12 NUMBER(15,2) unique ';
       execute immediate 'comment on column AMS_tableName.BK12 is  ''备用字段12:表示整数15位，小数2位''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK13 DATE  ';
       execute immediate 'comment on column AMS_tableName.BK13 is  ''备用字段13:D代表DATE类型''';

	end if;
end;
/
