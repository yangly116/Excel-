--增加字段
declare
  cnt integer;
begin
	select count(0)
      into cnt	
	  from user_tab_columns a
	  where a.Table_name = upper('tableName2')
	  and a.COLUMN_NAME in(
	  upper('RECE_CNAPS'),
	  upper('URGENT_FLAG'),
	  upper('PERSONALFLAG'),
	  upper('CREATOR'),
	  upper('CREATE_TIME'),
	  upper('TEXTVALUE1'),
	  upper('TEXTVALUE2'),
	  upper('TEXTVALUE3'),
	  upper('TEXTVALUE4'),
	  upper('TEXTVALUE5'),
	  upper('NUMBERVALUE1'),
	  upper('NUMBERVALUE3'),
	  upper('DATEVALUE1'),
	  upper('DATEVALUE2'),
	  upper('DATEVALUE3')
);	   
    if cnt = 0 then
	   execute immediate 'ALTER TABLE tableName2 ADD RECE_CNAPS NVARCHAR2(12) ';
       execute immediate 'comment on column tableName2.RECE_CNAPS is  ''收款方开户行CNAPS号:大额联行号''';
	   execute immediate 'ALTER TABLE tableName2 ADD URGENT_FLAG NVARCHAR2(2) NOT NULL';
       execute immediate 'comment on column tableName2.URGENT_FLAG is  ''加急标识:加急Y,不加急N''';
	   execute immediate 'ALTER TABLE tableName2 ADD PERSONALFLAG NVARCHAR2(2) ';
       execute immediate 'comment on column tableName2.PERSONALFLAG is  ''对私标记:默认对公；对公‘1’、对私‘2’''';
	   execute immediate 'ALTER TABLE tableName2 ADD CREATOR NVARCHAR2(20) ';
       execute immediate 'comment on column tableName2.CREATOR is  ''录入人:录入人姓名''';
	   execute immediate 'ALTER TABLE tableName2 ADD CREATE_TIME DATE ';
       execute immediate 'comment on column tableName2.CREATE_TIME is  ''录入时间:YYYY-MM-DD''';
	   execute immediate 'ALTER TABLE tableName2 ADD TEXTVALUE1 NVARCHAR2(255) ';
       execute immediate 'comment on column tableName2.TEXTVALUE1 is  ''文本备用字段1''';
	   execute immediate 'ALTER TABLE tableName2 ADD TEXTVALUE2 NVARCHAR2(255) ';
       execute immediate 'comment on column tableName2.TEXTVALUE2 is  ''文本备用字段2''';
	   execute immediate 'ALTER TABLE tableName2 ADD TEXTVALUE3 NVARCHAR2(255) ';
       execute immediate 'comment on column tableName2.TEXTVALUE3 is  ''文本备用字段3''';
	   execute immediate 'ALTER TABLE tableName2 ADD TEXTVALUE4 NVARCHAR2(255) ';
       execute immediate 'comment on column tableName2.TEXTVALUE4 is  ''文本备用字段4''';
	   execute immediate 'ALTER TABLE tableName2 ADD TEXTVALUE5 NVARCHAR2(255) ';
       execute immediate 'comment on column tableName2.TEXTVALUE5 is  ''文本备用字段5''';
	   execute immediate 'ALTER TABLE tableName2 ADD NUMBERVALUE1 NUMBER ';
       execute immediate 'comment on column tableName2.NUMBERVALUE1 is  ''数字备用字段1''';
	   execute immediate 'ALTER TABLE tableName2 ADD NUMBERVALUE3 NUMBER(15,2) ';
       execute immediate 'comment on column tableName2.NUMBERVALUE3 is  ''数字备用字段3''';
	   execute immediate 'ALTER TABLE tableName2 ADD DATEVALUE1 DATE ';
       execute immediate 'comment on column tableName2.DATEVALUE1 is  ''时间备用字段1''';
	   execute immediate 'ALTER TABLE tableName2 ADD DATEVALUE2 DATE ';
       execute immediate 'comment on column tableName2.DATEVALUE2 is  ''时间备用字段2''';
	   execute immediate 'ALTER TABLE tableName2 ADD DATEVALUE3 DATE ';
       execute immediate 'comment on column tableName2.DATEVALUE3 is  ''时间备用字段3''';

	end if;
end;
/
