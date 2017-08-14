declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_all_tables
   where table_name = upper('AMS_review');
   
    if cnt = 0 then
     execute immediate 'create table AMS_review
(
	  rId                    INTEGER NOT NULL ,
	  bizId                    INTEGER  ,
	  userId                    INTEGER  ,
	  stageCode                    NVARCHAR2(32)  ,
	  bizName                    NVARCHAR2(512)  ,
	  content                    NVARCHAR2(1024)  ,
	  readTime                    DATE  ,
	  readState                    NVARCHAR2(4)  ,
	  memo                    NVARCHAR2(512)  ,
	  createTime                    DATE  default sysdate
)';
execute immediate 'comment on table AMS_review is ''阅办表''';
execute immediate 'comment on column AMS_review.rId is ''阅办ID:''';
execute immediate 'comment on column AMS_review.bizId is ''业务ID:''';
execute immediate 'comment on column AMS_review.userId is ''阅办人ID:''';
execute immediate 'comment on column AMS_review.stageCode is ''阶段编码:''';
execute immediate 'comment on column AMS_review.bizName is ''业务名称:''';
execute immediate 'comment on column AMS_review.content is ''阅办内容:''';
execute immediate 'comment on column AMS_review.readTime is ''阅读时间:''';
execute immediate 'comment on column AMS_review.readState is ''阅读状态:1,准备中；2，未阅读；3，已阅读；''';
execute immediate 'comment on column AMS_review.memo is ''备注:''';
execute immediate 'comment on column AMS_review.createTime is ''创建时间:''';

  end if;             
end;
/
declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_constraints a
   where a.constraint_name = upper('PK_AMS_review');
        
    if cnt = 0 then
        execute immediate 'alter table AMS_review add constraint PK_AMS_review primary key (rId)';
  end if;             
end;
/

declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_sequences a
   where a.sequence_name = upper('SEQ_AMS_review');
   
    if cnt = 0 then
     execute immediate 'create sequence SEQ_AMS_review
        minvalue 1
        maxvalue 999999999999999999999999999
        start with 1
        increment by 1
        cache 20';
  end if;             
end;
/
