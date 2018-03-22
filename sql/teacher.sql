create or replace procedure PdeleteCourse( tclassid int  )is
 NuserNumber number;
--老师删除课程
--未测试
             begin
               update  curriculum set inowscournumber=inowscournumber-1
                     where curriculum.scourseid=(select course.scourseid from course where course.iteachclassid=tclassid );
                  delete from leavemessage where   leavemessage.iteachclassid=tclassid;   
									   delete from classtimetable where  classtimetable.iteachclassid=tclassid;              
                  delete from selectivecoursesandmark where selectivecoursesandmark.iteachclassid=tclassid;
                  delete from course where course.iteachclassid=tclassid;


            commit;
            end PdeleteCourse;
                                                  
	create or replace procedure PaddTeacher(sName varchar2,  teacherID int , collegeid int,title varchar2 )is
 NuserNumber number;
--创建老师
--未测试
          begin
             select usersnumber into  NuserNumber from scott.consttable
             where scott.consttable.usid=1;
              update scott.consttable set scott.consttable.usersnumber=scott.consttable.usersnumber+1
              where scott.consttable.usid=1;
              insert into scott.person(sname,
                                     iid,
                                     cgender,
                                     iage,
                                     spassword
                                     )
                                     values(sName，NuserNumber,'未录',0,123);
                insert into scott.teacher(iteacheid,
                                          icollegeid,
                                          iid,
                                          stitle,
                                          iworkyears
                                          )
                values(teacherID,collegeid,NuserNumber,title,10);
                  NPcollegeTeacherPlus(teacherID);
            commit;
            end PaddTeacher;
