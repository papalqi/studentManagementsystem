create or replace procedure PaddYHFK(messages varchar2, iids int,times date,stitle varchar2 )is
             NuserNumber number;

          begin
            --������ر�ID�����޸ļ�һ
       select usersnumber into  NuserNumber from scott.consttable where consttable.usid=5 ;
       update scott.consttable set scott.consttable.usersnumber=scott.consttable.usersnumber+1
       where consttable.usid=5;
                    insert into scott.YHFK(yid,
                                           iid,
                                           times,
                                           message,
                                           stitle)
																					 values(NuserNumber,iids,times,messages,stitle);

            commit;
            end PaddYHFK;