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
execute immediate 'comment on table AMS_review is ''�İ��''';
execute immediate 'comment on column AMS_review.rId is ''�İ�ID:''';
execute immediate 'comment on column AMS_review.bizId is ''ҵ��ID:''';
execute immediate 'comment on column AMS_review.userId is ''�İ���ID:''';
execute immediate 'comment on column AMS_review.stageCode is ''�׶α���:''';
execute immediate 'comment on column AMS_review.bizName is ''ҵ������:''';
execute immediate 'comment on column AMS_review.content is ''�İ�����:''';
execute immediate 'comment on column AMS_review.readTime is ''�Ķ�ʱ��:''';
execute immediate 'comment on column AMS_review.readState is ''�Ķ�״̬:1,׼���У�2��δ�Ķ���3�����Ķ���''';
execute immediate 'comment on column AMS_review.memo is ''��ע:''';
execute immediate 'comment on column AMS_review.createTime is ''����ʱ��:''';

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
