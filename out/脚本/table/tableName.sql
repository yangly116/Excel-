declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_all_tables
   where table_name = upper('tableName');
   
    if cnt = 0 then
     execute immediate 'create table tableName
(
	  tId                    INTEGER NOT NULL,
	  payerId                    NVARCHAR2(18) ,
	  payerNO                    NVARCHAR2(32) ,
	  PAY_DATE                    DATE ,
	  ABSTRACT                    NVARCHAR2(60) ,
	  TR_TYPE                    NVARCHAR2(2) ,
	  AMOUNT                    NUMBER(15,2) ,
	  CURRENCY                    NVARCHAR2(3) ,
	  RECE_ACC_NO                    NVARCHAR2(32) ,
	  RECE_ACC_NAME                    NVARCHAR2(60) ,
	  REG_NO                    NVARCHAR2(4) ,
	  RECE_OPBANK_NO                    NVARCHAR2(60) ,
	  RECE_OPBANK_NAME                    NVARCHAR2(60) 
)';
execute immediate 'comment on table tableName is ''表名注释''';
execute immediate 'comment on column tableName.tId is ''主键:唯一主键Id''';
execute immediate 'comment on column tableName.payerId is ''付款方ID:资金系统付款方单位编号''';
execute immediate 'comment on column tableName.payerNO is ''付款方内部账号:资金系统内的内部结算账号''';
execute immediate 'comment on column tableName.PAY_DATE is ''付款日期:期望付款日，格式：YYYY-MM-DD''';
execute immediate 'comment on column tableName.ABSTRACT is ''摘要''';
execute immediate 'comment on column tableName.TR_TYPE is ''结算方式:网银转账T''';
execute immediate 'comment on column tableName.AMOUNT is ''付款金额:金额''';
execute immediate 'comment on column tableName.CURRENCY is ''币种:人民币CNY''';
execute immediate 'comment on column tableName.RECE_ACC_NO is ''收款方账号''';
execute immediate 'comment on column tableName.RECE_ACC_NAME is ''收款方户名''';
execute immediate 'comment on column tableName.REG_NO is ''收款方标准地名编码:资金系统内标准地址编码''';
execute immediate 'comment on column tableName.RECE_OPBANK_NO is ''收款方开户行:开户行大行名称（和资金系统内名称一致）''';
execute immediate 'comment on column tableName.RECE_OPBANK_NAME is ''收款方开户行名称:具体开户的支行''';

  end if;             
end;
/
declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_constraints a
   where a.constraint_name = upper('PK_tableName');
        
    if cnt = 0 then
        execute immediate 'alter table tableName add constraint PK_tableName primary key (tId)';
  end if;             
end;
/

declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_sequences a
   where a.sequence_name = upper('SEQ_tableName');
   
    if cnt = 0 then
     execute immediate 'create sequence SEQ_tableName
        minvalue 1
        maxvalue 999999999999999999999999999
        start with 1
        increment by 1
        cache 20';
  end if;             
end;
/
