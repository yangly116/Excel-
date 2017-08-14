declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_all_tables
   where table_name = upper('AMS_FileInfo');
   
    if cnt = 0 then
     execute immediate 'create table AMS_FileInfo
(
	  fileId                    INTEGER NOT NULL ,
	  bizId                    INTEGER  ,
	  stageCode                    NVARCHAR2(32)  ,
	  stageName                    NVARCHAR2(64)  ,
	  fileType                    NVARCHAR2(512)  ,
	  fileName                    NVARCHAR2(512)  ,
	  filePath                    NVARCHAR2(512)  default sysdate,
	  uploadTime                    DATE  default sysdate,
	  alterTime                    DATE  ,
	  fileState                    NVARCHAR2(2)  default ''1''
)';
execute immediate 'comment on table AMS_FileInfo is ''�ļ���Ϣ��''';
execute immediate 'comment on column AMS_FileInfo.fileId is ''�ļ�ID''';
execute immediate 'comment on column AMS_FileInfo.bizId is ''ҵ��ID''';
execute immediate 'comment on column AMS_FileInfo.stageCode is ''�׶α���''';
execute immediate 'comment on column AMS_FileInfo.stageName is ''�׶�����''';
execute immediate 'comment on column AMS_FileInfo.fileType is ''�ļ�����''';
execute immediate 'comment on column AMS_FileInfo.fileName is ''�ļ���''';
execute immediate 'comment on column AMS_FileInfo.filePath is ''�ļ�·��''';
execute immediate 'comment on column AMS_FileInfo.uploadTime is ''�ļ��ϴ�ʱ��''';
execute immediate 'comment on column AMS_FileInfo.alterTime is ''�ļ��޸�ʱ��''';
execute immediate 'comment on column AMS_FileInfo.fileState is ''�ļ�״̬:1,������2������''';

  end if;             
end;
/
declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_constraints a
   where a.constraint_name = upper('PK_AMS_FileInfo');
        
    if cnt = 0 then
        execute immediate 'alter table AMS_FileInfo add constraint PK_AMS_FileInfo primary key (fileId)';
  end if;             
end;
/

declare
 cnt integer;
begin
    select count(0)
    into cnt  
    from user_sequences a
   where a.sequence_name = upper('SEQ_AMS_FileInfo');
   
    if cnt = 0 then
     execute immediate 'create sequence SEQ_AMS_FileInfo
        minvalue 1
        maxvalue 999999999999999999999999999
        start with 1
        increment by 1
        cache 20';
  end if;             
end;
/
