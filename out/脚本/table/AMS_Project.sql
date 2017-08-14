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
execute immediate 'comment on table AMS_Project is ''项目表''';
execute immediate 'comment on column AMS_Project.pId is ''项目表ID''';
execute immediate 'comment on column AMS_Project.ypId is ''年计划ID:外表：AMS_YearPlan''';
execute immediate 'comment on column AMS_Project.project is ''项目名称''';
execute immediate 'comment on column AMS_Project.projectType is ''项目类型''';
execute immediate 'comment on column AMS_Project.sId is ''项目阶段:根据项目阶段确定项目进度''';
execute immediate 'comment on column AMS_Project.startDate is ''开始时间''';
execute immediate 'comment on column AMS_Project.endDate is ''结束时间''';
execute immediate 'comment on column AMS_Project.state is ''状态:1,未执行；2，进行中；3，已结束；4，废弃''';
execute immediate 'comment on column AMS_Project.principle is ''负责人''';
execute immediate 'comment on column AMS_Project.createTime is ''创建时间''';

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
