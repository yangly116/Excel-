declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_all_tables
   where table_name = upper('AMS_stage');
   
    if cnt = 0 then
     execute immediate 'create table AMS_stage
(
	  sId                    INTEGER NOT NULL ,
	  stageName                    NVARCHAR2(128)  ,
	  state                    NVARCHAR2(128)  ,
	  createTime                    DATE  default sysdate
)';
execute immediate 'comment on table AMS_stage is ''��Ŀ�׶α�''';
execute immediate 'comment on column AMS_stage.sId is ''�׶�ID''';
execute immediate 'comment on column AMS_stage.stageName is ''�׶�����''';
execute immediate 'comment on column AMS_stage.state is ''�׶�״̬:1,δִ�У�2�������У�3���ѽ�����4������''';
execute immediate 'comment on column AMS_stage.createTime is ''����ʱ��''';

  end if;             
end;
/
declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_constraints a
   where a.constraint_name = upper('PK_AMS_stage');
        
    if cnt = 0 then
        execute immediate 'alter table AMS_stage add constraint PK_AMS_stage primary key (sId)';
  end if;             
end;
/

declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_sequences a
   where a.sequence_name = upper('SEQ_AMS_stage');
   
    if cnt = 0 then
     execute immediate 'create sequence SEQ_AMS_stage
        minvalue 1
        maxvalue 999999999999999999999999999
        start with 1
        increment by 1
        cache 20';
  end if;             
end;
/
