create or replace procedure PaddCampus(iCampusID int, sCampusName VARCHAR2) is
/*添加校区（校区ID【number】，校区名【vchar2】） 
测试成功*/
begin
                  insert into SCOTT.CAMPUS (ICAMPUSID,SCAMPUSNAME)
                  values (iCampusID,sCampusName);
                  commit;
end PaddCampus;

create or replace procedure PupdateCampus(CampusID int, CampusName VARCHAR2) is
--修改校区（仅能修改校区名字）
--测试成功
begin
                  update scott.CAMPUS set scott.CAMPUS.scampusname= CampusName
                  where  scott.campus.icampusid =CampusID ;
                  commit;
end PupdateCampus;


create or replace procedure PdeleteCampusWithName(CampusName VARCHAR2) is
--用名字删除校区
--测试成功
begin
           delete from scott.campus where CAMPUS.scampusname= CampusName;
             commit;
end PdeleteCampusWithName; 

create or replace procedure PdeleteCampusWithID(CampusID number) is
--用ID删除校区
--测试成功
begin
           delete from scott.campus where CAMPUS.iCampusID= CampusID;
             commit;
end PdeleteCampusWithID; 
-----------------------------------------------------------------------------------------------

create or replace procedure PaddCscollege( collegeID int, collegeName VARCHAR2 ,campusID int)   is  
/* 添加一个新的学院，学院名称，学院编号，校区编号 默认为1，学院地址 默认为 重庆市沙坪坝区沙坪坝正街174号
院长编号 默认为无，学生数量 默认为0，老师数量 默认为0
测试完成*/
                begin
                  insert into SCOTT.COLLEGE (SCOLLEGENAME, ICOLLEGEID, ICAMPUSID, 
                  SCOLLEGEADSSRESS, ISTUDENTNUMBER, ITEACHERNUMBER)
                values(collegeName,collegeID,campusID,'沙坪坝正街174号',0,0); 
                  commit;              
end PaddCscollege;


create or replace procedure PupdateCollegeName( collegeID int, collegeName VARCHAR2 )   is  
--修改学院名称
--测试完成
                begin
                  update scott.college set college.scollegename=collegeName
                  where college.icollegeid=collegeID;
                 commit;              
end PupdateCollegeName;


create or replace procedure PupdateCollegeCampusAndAddress( collegeID int, address VARCHAR2,campusID int )   is 
--修改学院地址和校区ID
--测试成功 
                begin
                  update scott.college set college.scollegeadssress=address , college.icampusid=campusID
                  where college.icollegeid=collegeID;
                 commit;              
end PupdateCollegeCampusAndAddress;


create or replace procedure PdeleteCollege( collegeID int )   is  
--删除学院信息
--测试成功
                begin
                             delete from scott.college where college.icollegeid= collegeID;
                             commit;
                 commit;              
end PdeleteCollege;
-------------------------------------------------------------------------------


create or replace procedure PaddMajor( MajorName varchar2 ,majorID integer,collegeID integer )   is  
/*添加新的专业，除了名字和ID其他的都是默认为0*/
--测试成功
                begin
                  insert into scott.major(smajorname,
                                          imajorid,
                                          icollegeid,
                                          iclassnumber,
                                          iteachernumber,
                                          istudentnumber)              
                  values(  MajorName，majorID,collegeID， 0,0,0);                
                  commit;              
end PaddMajor;


create or replace procedure PupdateMajorWithName( majorID integer,MajorName varchar2 )   is  
--修改专业名用ID
--未测试
                begin
                  update scott.major set major.smajorname=MajorName where imajorid=majorID;
                  commit;              
end PupdateMajorWithName;

create or replace procedure PupdateMajorWithName( oldMajorName varchar2,newMajorName varchar2 )   is  
--修改专业名，用专业名
--未测试
                begin
                  update scott.major set major.smajorname=newMajorName where major.smajorname =oldMajorName;
                  commit;              
end PupdateMajorWithName;

create or replace procedure PdeleteMajorWithName( MajorName varchar2 )   is  
--删除专业用名字
--未测试
                begin
                  delete from scott.major where major.smajorname= MajorName;
                             commit;
                  commit;              
end PdeleteMajorWithName;

create or replace procedure PdeleteMajorWithID( majorID integer )   is  
--删除专业用ID
--未测试
                begin
                  delete from scott.major where major.imajorid= majorID;
                             commit;
                  commit;              
end PdeleteMajorWithID;




-------------------------------------------------------------------------------------------------

create or replace procedure PaddADCLASS(classID int , majorID int ,className varchar2)is
/*添加行政班默认人数为0*/
--已测试
          begin
            --添加教学班
            insert into scott.adclass(
                                      inumber,
                                      sclassname,
                                      iclassid,
                                      imajorid)
            values(0，className，classID，majorID);
            --更新专业班级数
            update  scott.major
               set scott.major.iclassnumber=scott.major.iclassnumber+1
             where majorID=major.imajorid;
            commit;
            end PaddADCLASS;

 create or replace procedure PdeleteMajorWithID( classID integer )   is  
 --删除班级
--未测试 
                begin
                  delete from scott.adclass where adclass.iclassid= classID;
                             commit;
                  commit;              
end PdeleteMajorWithID;          

    ---------------------------------------------------------------------------------
 create or replace procedure PaddStudent(sName varchar2,
                                        gender varchar2, age int,studentID int,classID int )is
             NuserNumber number;
/*添加学生，IID自动生成，eamil手机为空可以没有，地址为未录入，密码默认为123456
年级默认大一
寝室号默认10000*/     
          begin
            --提出公关表ID，并修改加一
       select usersnumber into  NuserNumber from scott.consttable ;
       update scott.consttable set scott.consttable.usersnumber=scott.consttable.usersnumber+1
       where usersnumber is not null;
      
       dbms_output.put_line(NuserNumber);
       --添加一个人
                insert into scott.person(sname,
                                     iid,
                                     cgender,
                                     iage,
                                     spassword
                                     )
                                     values(sName，NuserNumber,gender,age,123);
         --添加一个学生
            insert into scott.student(istudentid,
                                      iclassid,
                                      iid,
                                      igrade,
                                      idorm)
                                       values(studentID,classID,NuserNumber,1,10000);
                                       NPstudentNumberPuls(classID);
             
            commit;
            end PaddStudent;
            
   
 create or replace procedure PupdateStudentAddress(studentID int, placeoforigin1 varchar2 ,
                                                      placeoforigin2 varchar2,placeoforigin3 varchar2 ,placeaddress varchar2)   is  
 --修改学生地址信息
--测试成功
                begin
                            update scott.person set 
                            
                                                          person.splaceoforigin1=placeoforigin1,
                                                         person.splaceoforigin2=placeoforigin2,
                                                          person.splaceoforigin3=placeoforigin3,
                                                          person.splaceaddress=placeaddress
                                             where NFFindIDwithstuID(studentID)=person.iid;                                                   
                           
                  commit;              
end PupdateStudentAddress;          
   
 create or replace procedure PupdateStudentsPassword(studentID int,NewPassword int)   is  
 --修改学生密码
--未测试
                begin
                            update scott.person set
                                                               person.spassword=NewPassword
                                             where NFFindIDwithstuID(studentID)=person.iid;                                                   
                           
                  commit;              
end PupdateStudentsPassword;     

 create or replace procedure PupdateDeleteStudent(studentID int,NewPassword int)   is  
 --删除学生信息
--未测试
                begin
                                delete from scott.student where   student.istudentid=studentID;
                  commit;              
end PupdateDeleteStudent;     
     



