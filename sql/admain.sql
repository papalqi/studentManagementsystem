create or replace procedure PcreateAdmin(sName varchar2,  adminID int  )is
 NuserNumber number;
--创建管理员
--已测试
          begin
             select usersnumber into  NuserNumber from scott.consttable 
						 where        consttable.usid=1;
              update scott.consttable set scott.consttable.usersnumber=scott.consttable.usersnumber+1
               where usersnumber is not null;
              insert into scott.person(sname,
                                     iid,
                                     cgender,
                                     iage,
                                     spassword
                                     )
                                     values(sName，NuserNumber,'未录入',0,123);
                insert into scott.administrators(iadminid, iid, iworkyears)
                values(adminID,NuserNumber,0);

            commit;
            end PcreateAdmin;
