create or replace procedure PleaveMessage(iisd varchar2,  teachclassID int ,message  varchar2,udate date)is
 NuserNumber number;
--����
--δ����
          begin       
						
					 select usersnumber into  NuserNumber from scott.consttable where consttable.usid=4 ;
       update scott.consttable set scott.consttable.usersnumber=scott.consttable.usersnumber+1
       where consttable.usid=4;
               insert INTO LEAVEMESSAGE(IID,
                                        ITEACHCLASSID,
                                        SCOMMENT,
                                        TCOMMENTTIME,
                                        MID)
                                        VALUES(iisd,teachclassID,message,udate,NuserNumber);
            commit;
            end PleaveMessage;       
						
create or replace procedure PdeleteMessage(umid int )is
 NuserNumber number;
--����
--δ����
          begin
					delete from       LEAVEMESSAGE where    LEAVEMESSAGE.MID=umid;
            commit;
            end PdeleteMessage;
