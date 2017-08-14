declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_all_tables
   where table_name = upper('AMS_YearPlan');
   
    if cnt = 0 then
     execute immediate 'create table AMS_YearPlan
(
	  ypId                    INTEGER NOT NULL ,
	  year                    NVARCHAR2(8)  ,
	  title                    NVARCHAR2(128)  ,
	  content                    NVARCHAR2(1024)  ,
	  startDate                    DATE  ,
	  endDate                    DATE  ,
	  state                    NVARCHAR2(2)  ,
	  principle                    NVARCHAR2(16)  ,
	  createTime                    DATE  default sysdate
)';
execute immediate 'comment on table AMS_YearPlan is ''��ƻ���''';
execute immediate 'comment on column AMS_YearPlan.ypId is ''��ƻ�ID''';
execute immediate 'comment on column AMS_YearPlan.year is ''���''';
execute immediate 'comment on column AMS_YearPlan.title is ''����''';
execute immediate 'comment on column AMS_YearPlan.content is ''����''';
execute immediate 'comment on column AMS_YearPlan.startDate is ''��ʼʱ��''';
execute immediate 'comment on column AMS_YearPlan.endDate is ''����ʱ��''';
execute immediate 'comment on column AMS_YearPlan.state is ''״̬:1,δִ�У�2�������У�3������ͨ����4������''';
execute immediate 'comment on column AMS_YearPlan.principle is ''������''';
execute immediate 'comment on column AMS_YearPlan.createTime is ''����ʱ��''';

  end if;             
end;
/
declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_constraints a
   where a.constraint_name = upper('PK_AMS_YearPlan');
        
    if cnt = 0 then
        execute immediate 'alter table AMS_YearPlan add constraint PK_AMS_YearPlan primary key (ypId)';
  end if;             
end;
/

declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_sequences a
   where a.sequence_name = upper('SEQ_AMS_YearPlan');
   
    if cnt = 0 then
     execute immediate 'create sequence SEQ_AMS_YearPlan
        minvalue 1
        maxvalue 999999999999999999999999999
        start with 1
        increment by 1
        cache 20';
  end if;             
end;
/
