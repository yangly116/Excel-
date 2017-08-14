declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_all_tables
   where table_name = upper('AMS_Project');
   
    if cnt = 0 then
     execute immediate 'create table AMS_Project
(
	  pId                    INTEGER NOT NULL ,
	  ypId                    INTEGER  ,
	  project                    NVARCHAR2(128)  ,
	  projectType                    NVARCHAR2(4)  ,
	  sId                    NVARCHAR2(128)  ,
	  startDate                    DATE  ,
	  endDate                    DATE  ,
	  state                    NVARCHAR2(2)  ,
	  principle                    NVARCHAR2(16)  ,
	  createTime                    DATE  default sysdate
)';
execute immediate 'comment on table AMS_Project is ''��Ŀ��''';
execute immediate 'comment on column AMS_Project.pId is ''��Ŀ��ID''';
execute immediate 'comment on column AMS_Project.ypId is ''��ƻ�ID:���AMS_YearPlan''';
execute immediate 'comment on column AMS_Project.project is ''��Ŀ����''';
execute immediate 'comment on column AMS_Project.projectType is ''��Ŀ����''';
execute immediate 'comment on column AMS_Project.sId is ''��Ŀ�׶�:������Ŀ�׶�ȷ����Ŀ����''';
execute immediate 'comment on column AMS_Project.startDate is ''��ʼʱ��''';
execute immediate 'comment on column AMS_Project.endDate is ''����ʱ��''';
execute immediate 'comment on column AMS_Project.state is ''״̬:1,δִ�У�2�������У�3���ѽ�����4������''';
execute immediate 'comment on column AMS_Project.principle is ''������''';
execute immediate 'comment on column AMS_Project.createTime is ''����ʱ��''';

  end if;             
end;
/
declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_constraints a
   where a.constraint_name = upper('PK_AMS_Project');
        
    if cnt = 0 then
        execute immediate 'alter table AMS_Project add constraint PK_AMS_Project primary key (pId)';
  end if;             
end;
/

declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_sequences a
   where a.sequence_name = upper('SEQ_AMS_Project');
   
    if cnt = 0 then
     execute immediate 'create sequence SEQ_AMS_Project
        minvalue 1
        maxvalue 999999999999999999999999999
        start with 1
        increment by 1
        cache 20';
  end if;             
end;
/
