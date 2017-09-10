--�����ֶ�
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
       execute immediate 'comment on column AMS_tableName.BK1 is  ''�����ֶ�1:''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK2 nvarchar2(128)  ';
       execute immediate 'comment on column AMS_tableName.BK2 is  ''�����ֶ�2''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK3 varchar2(128) default ''1'' ';
       execute immediate 'comment on column AMS_tableName.BK3 is  ''�����ֶ�3:1��������2������''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK4 NUMBER unique NOT NULL';
       execute immediate 'comment on column AMS_tableName.BK4 is  ''�����ֶ�4:�ֶ�ΨһԼ��''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK5 NUMBER(15,6)  ';
       execute immediate 'comment on column AMS_tableName.BK5 is  ''�����ֶ�5''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK6 DATE  ';
       execute immediate 'comment on column AMS_tableName.BK6 is  ''�����ֶ�6''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK7 clob  ';
       execute immediate 'comment on column AMS_tableName.BK7 is  ''�����ֶ�7''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK8 date default sysdate ';
       execute immediate 'comment on column AMS_tableName.BK8 is  ''�����ֶ�8:Ĭ��ϵͳʱ��''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK9 INTEGER  ';
       execute immediate 'comment on column AMS_tableName.BK9 is  ''�����ֶ�9:I����INTEGER''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK10 NVARCHAR2(18) default ''1'' ';
       execute immediate 'comment on column AMS_tableName.BK10 is  ''�����ֶ�10:C����nvarchar2����''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK11 NUMBER  ';
       execute immediate 'comment on column AMS_tableName.BK11 is  ''�����ֶ�11:N����NUMBER����''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK12 NUMBER(15,2) unique ';
       execute immediate 'comment on column AMS_tableName.BK12 is  ''�����ֶ�12:��ʾ����15λ��С��2λ''';
	   execute immediate 'ALTER TABLE AMS_tableName ADD BK13 DATE  ';
       execute immediate 'comment on column AMS_tableName.BK13 is  ''�����ֶ�13:D����DATE����''';

	end if;
end;
/
