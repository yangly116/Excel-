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
execute immediate 'comment on table AMS_tableName is ''�½�����''';
execute immediate 'comment on column AMS_tableName.tId is ''����:''';
execute immediate 'comment on column AMS_tableName.RECE_ACC_NAME is ''�տ����''';
execute immediate 'comment on column AMS_tableName.tState is ''״̬:1��������2������''';
execute immediate 'comment on column AMS_tableName.payerId is ''���ID:�ֶ�ΨһԼ��''';
execute immediate 'comment on column AMS_tableName.AMOUNT is ''������''';
execute immediate 'comment on column AMS_tableName.PAY_DATE is ''��������''';
execute immediate 'comment on column AMS_tableName.message is ''������Ϣ''';
execute immediate 'comment on column AMS_tableName.createTime is ''����ʱ��:Ĭ��ϵͳʱ��''';
execute immediate 'comment on column AMS_tableName.msgId is ''����Id:I����INTEGER''';
execute immediate 'comment on column AMS_tableName.payState is ''֧��״̬:C����nvarchar2����''';
execute immediate 'comment on column AMS_tableName.msFlag is ''���ı��:N����NUMBER����''';
execute immediate 'comment on column AMS_tableName.bMoney is ''�������:��ʾ����15λ��С��2λ''';
execute immediate 'comment on column AMS_tableName.startDate is ''��ʼʱ��:D����DATE����''';

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
