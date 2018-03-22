create or replace procedure PaddRoom(classroomID int,academicbuildingid int ,sname varchar2)   is
--修改教室
--人数默认是50
 --未测试
       begin
                   insert into scott.classroom(iclassroomid,
                                               iacademicbuildingid,
                                               sclassroomname,
                                               iroomnumber)
                                               values(classroomID,academicbuildingid,sname,50);
          commit;
end PaddRoom;


 create or replace procedure PaddAllRoom(uu int  )   is
--修改教室
--人数默认是50
 --未测试     
        numbers int ;
       begin    
				 numbers:=495;
                  for i in 1..4 loop                        
							
									for j in 1..10 loop             
										
									     PaddRoom(classroomID =>numbers , academicbuildingid =>uu , sname =>to_char(i*100+j) )   ;
									                numbers:=numbers+1;
										end loop ;      
									
										end loop  ;
          commit;
end PaddAllRoom;

  create or replace procedure PupdateBuildNumber(uu int  )   is
--修改教室
--人数默认是50
 --未测试     
        numbers int ;
       begin    
				        update academicbuilding set     academicbuilding.roomnumber=(select count（*）from classroom where 
								classroom.iacademicbuildingid= uu)    
								where          academicbuilding.iacademicbuildingid=uu;
          commit;
end PupdateBuildNumber;




create or replace procedure PdeleteRoom(classroomID int )   is
--删除教室
--人数默认是50
 --未测试
       begin
               delete from scott.classroom where classroom.iclassroomid=classroomID;
          commit;
end PdeleteRoom;
