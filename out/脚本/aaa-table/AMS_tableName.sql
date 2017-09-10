declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_all_tables
   where table_name = upper('AMS_tableName');
   
    if cnt = 0 then
     execute immediate 'create table AMS_tableName
(
	  tId                    INTEGER  ,
	  RECE_ACC_NAME                    nvarchar2(128)  ,
	  tState                    varchar2(128) default ''1'' ,
	  payerId                    NUMBER unique ,
	  AMOUNT                    NUMBER(15,6)  ,
	  PAY_DATE                    DATE  NOT NULL,
	  message                    clob  ,
	  createTime                    date default sysdate ,
	  msgId                    INTEGER  ,
	  payState                    NVARCHAR2(18) default ''1'' unique NOT NULL,
	  msFlag                    NUMBER(13)  ,
	  bMoney                    NUMBER(15,2) unique NOT NULL,
	  startDate                    DATE  
)';
execute immediate 'comment on table AMS_tableName is ''新建表范例''';
execute immediate 'comment on column AMS_tableName.tId is ''主键:''';
execute immediate 'comment on column AMS_tableName.RECE_ACC_NAME is ''收款方户名''';
execute immediate 'comment on column AMS_tableName.tState is ''状态:1，正常；2，废弃''';
execute immediate 'comment on column AMS_tableName.payerId is ''付款方ID:字段唯一约束''';
execute immediate 'comment on column AMS_tableName.AMOUNT is ''付款金额''';
execute immediate 'comment on column AMS_tableName.PAY_DATE is ''付款日期''';
execute immediate 'comment on column AMS_tableName.message is ''报文信息''';
execute immediate 'comment on column AMS_tableName.createTime is ''创建时间:默认系统时间''';
execute immediate 'comment on column AMS_tableName.msgId is ''报文Id:I代表INTEGER''';
execute immediate 'comment on column AMS_tableName.payState is ''支付状态:C代表nvarchar2类型''';
execute immediate 'comment on column AMS_tableName.msFlag is ''报文标号:N代表NUMBER类型''';
execute immediate 'comment on column AMS_tableName.bMoney is ''额外费用:表示整数15位，小数2位''';
execute immediate 'comment on column AMS_tableName.startDate is ''开始时间:D代表DATE类型''';

  end if;             
end;
/
declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_constraints a
   where a.constraint_name = upper('PK_AMS_tableName');
        
    if cnt = 0 then
        execute immediate 'alter table AMS_tableName add constraint PK_AMS_tableName primary key (tId,payerId)';
  end if;             
end;
/

declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_sequences a
   where a.sequence_name = upper('SEQ_AMS_tableName');
   
    if cnt = 0 then
     execute immediate 'create sequence SEQ_AMS_tableName
        minvalue 1
        maxvalue 999999999999999999999999999
        start with 1
        increment by 1
        cache 20';
  end if;             
end;
/
