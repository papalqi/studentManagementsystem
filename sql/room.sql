create or replace procedure PaddRoom(classroomID int,academicbuildingid int ,sname varchar2)   is
--�޸Ľ���
--����Ĭ����50
 --δ����
       begin
                   insert into scott.classroom(iclassroomid,
                                               iacademicbuildingid,
                                               sclassroomname,
                                               iroomnumber)
                                               values(classroomID,academicbuildingid,sname,50);
          commit;
end PaddRoom;


 create or replace procedure PaddAllRoom(uu int  )   is
--�޸Ľ���
--����Ĭ����50
 --δ����     
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
--�޸Ľ���
--����Ĭ����50
 --δ����     
        numbers int ;
       begin    
				        update academicbuilding set     academicbuilding.roomnumber=(select count��*��from classroom where 
								classroom.iacademicbuildingid= uu)    
								where          academicbuilding.iacademicbuildingid=uu;
          commit;
end PupdateBuildNumber;




create or replace procedure PdeleteRoom(classroomID int )   is
--ɾ������
--����Ĭ����50
 --δ����
       begin
               delete from scott.classroom where classroom.iclassroomid=classroomID;
          commit;
end PdeleteRoom;
