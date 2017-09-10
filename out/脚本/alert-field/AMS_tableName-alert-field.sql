declare
  cnt integer;
begin
  select count(0)
      into cnt  
      from user_tab_columns a
   	  where a.Table_name = upper('AMS_tableName')
	  and a.COLUMN_NAME in(
	  upper('tState'),
	  upper('AMOUNT'),
	  upper('PAY_DATE'),
	  upper('payState'),
	  upper('msgId'),
	  upper('startDate'),
	  upper('bMoney'),
	  upper('msFlag')
);     
    if cnt = 8 then
       execute immediate 'ALTER TABLE AMS_tableName modify tState varchar2(128)  ';
       execute immediate 'comment on column AMS_tableName.tState is  ''״̬2:�޸�ע��''';
       execute immediate 'ALTER TABLE AMS_tableName modify AMOUNT NUMBER(15,8)  ';
       execute immediate 'comment on column AMS_tableName.AMOUNT is  ''������:�޸ľ��ȷ�Χ''';
       execute immediate 'ALTER TABLE AMS_tableName modify PAY_DATE DATE default sysdate ';
       execute immediate 'comment on column AMS_tableName.PAY_DATE is  ''��������:�޸�Ĭ��ֵ''';
       execute immediate 'ALTER TABLE AMS_tableName modify payState NVARCHAR2(20) default ''1'' ';
       execute immediate 'comment on column AMS_tableName.payState is  ''֧��״̬:�޸����ͳ���''';
       execute immediate 'ALTER TABLE AMS_tableName modify msgId varchar2(128)  ';
       execute immediate 'comment on column AMS_tableName.msgId is  ''����Id:�޸��ֶ�����''';
        select count(0) into cnt 
        from user_tab_columns 
        where table_name = upper('AMS_tableName')
        and column_name = upper('startDate')
        and NULLABLE = UPPER('Y');
       if cnt = 1 then 
       execute immediate 'ALTER TABLE AMS_tableName modify startDate DATE  NOT NULL';
       execute immediate 'comment on column AMS_tableName.startDate is  ''��ʼʱ��:�޸��ֶ�Ϊ�ǿ�''';
         end if;
        select count(0) into cnt 
        from user_tab_columns 
        where table_name = upper('AMS_tableName')
        and column_name = upper('bMoney')
        and NULLABLE = UPPER('N');
       if cnt = 1 then 
       execute immediate 'ALTER TABLE AMS_tableName modify bMoney NUMBER(15,2)  NULL';
       execute immediate 'comment on column AMS_tableName.bMoney is  ''�������:�޸��ֶ�Ϊ�ɿ�''';
         end if;
        select count(0) into cnt 
        from user_tab_columns 
        where table_name = upper('AMS_tableName')
        and column_name = upper('msFlag')
        and NULLABLE = UPPER('Y');
       if cnt = 1 then 
       execute immediate 'ALTER TABLE AMS_tableName modify msFlag NUMBER unique NOT NULL';
       execute immediate 'comment on column AMS_tableName.msFlag is  ''���ı��:�����ֶ�Լ��''';
         end if;

  end if;
end;
/
