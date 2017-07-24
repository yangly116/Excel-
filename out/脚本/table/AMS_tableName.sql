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
	  tId                    INTEGER NOT NULL ,
	  payerId                    NVARCHAR2(18) NOT NULL unique,
	  tState                    NVARCHAR2(32)  default ''1'',
	  PAY_DATE                    DATE  unique,
	  ABSTRACT                    NVARCHAR2(60)  ,
	  TR_TYPE                    NVARCHAR2(2)  default ''T'',
	  AMOUNT                    NUMBER(15,2)  ,
	  CURRENCY                    NVARCHAR2(3)  ,
	  RECE_ACC_NO                    NVARCHAR2(32)  ,
	  RECE_ACC_NAME                    NVARCHAR2(60)  ,
	  REG_NO                    NVARCHAR2(4)  ,
	  RECE_OPBANK_NO                    NVARCHAR2(60)  ,
	  RECE_OPBANK_NAME                    NVARCHAR2(60)  ,
	  createTime                    DATE  default sysdate
)';
execute immediate 'comment on table AMS_tableName is ''���Ա�''';
execute immediate 'comment on column AMS_tableName.tId is ''����:����Id''';
execute immediate 'comment on column AMS_tableName.payerId is ''���ID:�ʽ�ϵͳ�����λ���''';
execute immediate 'comment on column AMS_tableName.tState is ''״̬:1��������2������''';
execute immediate 'comment on column AMS_tableName.PAY_DATE is ''��������:���������գ���ʽ��YYYY-MM-DD''';
execute immediate 'comment on column AMS_tableName.ABSTRACT is ''ժҪ''';
execute immediate 'comment on column AMS_tableName.TR_TYPE is ''���㷽ʽ:����ת��T''';
execute immediate 'comment on column AMS_tableName.AMOUNT is ''������:���''';
execute immediate 'comment on column AMS_tableName.CURRENCY is ''����:�����CNY''';
execute immediate 'comment on column AMS_tableName.RECE_ACC_NO is ''�տ�˺�''';
execute immediate 'comment on column AMS_tableName.RECE_ACC_NAME is ''�տ����:�տ�''';
execute immediate 'comment on column AMS_tableName.REG_NO is ''�տ��׼��������:�ʽ�ϵͳ�ڱ�׼��ַ����''';
execute immediate 'comment on column AMS_tableName.RECE_OPBANK_NO is ''�տ������:�����д������ƣ����ʽ�ϵͳ������һ�£�''';
execute immediate 'comment on column AMS_tableName.RECE_OPBANK_NAME is ''�տ����������:���忪����֧��''';
execute immediate 'comment on column AMS_tableName.createTime is ''����ʱ��''';

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
