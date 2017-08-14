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
execute immediate 'comment on table AMS_FileInfo is ''文件信息表''';
execute immediate 'comment on column AMS_FileInfo.fileId is ''文件ID''';
execute immediate 'comment on column AMS_FileInfo.bizId is ''业务ID''';
execute immediate 'comment on column AMS_FileInfo.stageCode is ''阶段编码''';
execute immediate 'comment on column AMS_FileInfo.stageName is ''阶段名称''';
execute immediate 'comment on column AMS_FileInfo.fileType is ''文件类型''';
execute immediate 'comment on column AMS_FileInfo.fileName is ''文件名''';
execute immediate 'comment on column AMS_FileInfo.filePath is ''文件路径''';
execute immediate 'comment on column AMS_FileInfo.uploadTime is ''文件上传时间''';
execute immediate 'comment on column AMS_FileInfo.alterTime is ''文件修改时间''';
execute immediate 'comment on column AMS_FileInfo.fileState is ''文件状态:1,正常；2，废弃''';

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
