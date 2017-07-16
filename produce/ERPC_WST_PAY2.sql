declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_all_tables
   where table_name = upper('ERPC_WST_PAY2');
   
    if cnt = 0 then
     execute immediate 'create table ERPC_WST_PAY2
(
ERP_INS_ID2                    NVARCHAR2(32),
  PAYER_ID2                    NVARCHAR2(18),
  PAYER_NO                    NVARCHAR2(32),
  PAY_DATE                    DATE,
  ABSTRACT                    NVARCHAR2(60),
  TR_TYPE                    NVARCHAR2(2),
  AMOUNT                    NUMBER(15,2),
  CURRENCY                    NVARCHAR2(3),
  RECE_ACC_NO                    NVARCHAR2(32),
  RECE_ACC_NAME                    NVARCHAR2(60),
  REG_NO                    NVARCHAR2(4),
  RECE_OPBANK_NO                    NVARCHAR2(60),
  RECE_OPBANK_NAME                    NVARCHAR2(60),
  RECE_CNAPS                    NVARCHAR2(12),
  URGENT_FLAG                    NVARCHAR2(2),
  PERSONALFLAG                    NVARCHAR2(2),
  CREATOR                    NVARCHAR2(20),
  CREATE_TIME                    DATE,
  TEXTVALUE1                    NVARCHAR2(255),
  TEXTVALUE2                    NVARCHAR2(255),
  TEXTVALUE3                    NVARCHAR2(255),
  TEXTVALUE4                    NVARCHAR2(255),
  TEXTVALUE5                    NVARCHAR2(255),
  NUMBERVALUE1                    NUMBER,
  NUMBERVALUE3                    NUMBER(15,2),
  DATEVALUE1                    DATE,
  DATEVALUE2                    DATE,
  DATEVALUE3                    DATE
)';
execute immediate 'comment on table ERPC_WST_PAY2 is ''付款信息表2''';
execute immediate 'comment on column ${tableName}.ERP_INS_ID2 is ''ERP端付款唯一标识:所有的付款业务类型统一标识''';
execute immediate 'comment on column ${tableName}.PAYER_ID2 is ''付款方ID:资金系统付款方单位编号''';
execute immediate 'comment on column ${tableName}.PAYER_NO is ''付款方内部账号:资金系统内的内部结算账号''';
execute immediate 'comment on column ${tableName}.PAY_DATE is ''付款日期:期望付款日，格式：YYYY-MM-DD''';
execute immediate 'comment on column ${tableName}.ABSTRACT is ''摘要''';
execute immediate 'comment on column ${tableName}.TR_TYPE is ''结算方式:网银转账T''';
execute immediate 'comment on column ${tableName}.AMOUNT is ''付款金额:金额''';
execute immediate 'comment on column ${tableName}.CURRENCY is ''币种:人民币CNY''';
execute immediate 'comment on column ${tableName}.RECE_ACC_NO is ''收款方账号''';
execute immediate 'comment on column ${tableName}.RECE_ACC_NAME is ''收款方户名''';
execute immediate 'comment on column ${tableName}.REG_NO is ''收款方标准地名编码:资金系统内标准地址编码''';
execute immediate 'comment on column ${tableName}.RECE_OPBANK_NO is ''收款方开户行:开户行大行名称（和资金系统内名称一致）''';
execute immediate 'comment on column ${tableName}.RECE_OPBANK_NAME is ''收款方开户行名称:具体开户的支行''';
execute immediate 'comment on column ${tableName}.RECE_CNAPS is ''收款方开户行CNAPS号:大额联行号''';
execute immediate 'comment on column ${tableName}.URGENT_FLAG is ''加急标识:加急Y,不加急N''';
execute immediate 'comment on column ${tableName}.PERSONALFLAG is ''对私标记:默认对公；对公‘1’、对私‘2’''';
execute immediate 'comment on column ${tableName}.CREATOR is ''录入人:录入人姓名''';
execute immediate 'comment on column ${tableName}.CREATE_TIME is ''录入时间:YYYY-MM-DD''';
execute immediate 'comment on column ${tableName}.TEXTVALUE1 is ''文本备用字段1''';
execute immediate 'comment on column ${tableName}.TEXTVALUE2 is ''文本备用字段2''';
execute immediate 'comment on column ${tableName}.TEXTVALUE3 is ''文本备用字段3''';
execute immediate 'comment on column ${tableName}.TEXTVALUE4 is ''文本备用字段4''';
execute immediate 'comment on column ${tableName}.TEXTVALUE5 is ''文本备用字段5''';
execute immediate 'comment on column ${tableName}.NUMBERVALUE1 is ''数字备用字段1''';
execute immediate 'comment on column ${tableName}.NUMBERVALUE3 is ''数字备用字段3''';
execute immediate 'comment on column ${tableName}.DATEVALUE1 is ''时间备用字段1''';
execute immediate 'comment on column ${tableName}.DATEVALUE2 is ''时间备用字段2''';
execute immediate 'comment on column ${tableName}.DATEVALUE3 is ''时间备用字段3''';

  end if;             
end;
/
declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_constraints a
   where a.constraint_name = upper('PK_ERPC_WST_PAY2');
        
    if cnt = 0 then
        execute immediate 'alter table ERPC_WST_PAY2 add constraint PK_ERPC_WST_PAY2 primary key (ERP_INS_ID2)';
  end if;             
end;
/

declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_sequences a
   where a.sequence_name = upper('SEQ_ERPC_WST_PAY2');
   
    if cnt = 0 then
     execute immediate 'create sequence SEQ_ERPC_WST_PAY2
        minvalue 1
        maxvalue 999999999999999999999999999
        start with 1
        increment by 1
        cache 20';
  end if;             
end;
/
