create or replace view vGLYSHCJ as
select distinct
       curriculum.scourseid,
       curriculum.scoursename,
			 course.iteachclassid,
       teacher.iteacheid,
       person.sname ,
			  course.istudentnumber,
				course.istudentmaxnumber,
				course.bscorestate,
				course.scouesestate  ,    
				major.smajorname,
				college.scollegename
   from( ((((course left join curriculum on curriculum.scourseid=course.scourseid)  left join selectiveCoursesAndMark
   on course.iteachclassid=selectiveCoursesAndMark.Iteachclassid ) left join teacher on course.iteacheid=teacher.iteacheid)
    left join person on teacher.iid=person.iid) left join major on major.imajorid=curriculum.imajorid)
          left join college on college.icollegeid= major.icollegeid
;
create or replace view vteachercourse as
select
    --  selectivecoursesandmark.scouesestate
      curriculum.scourseid,
       curriculum.scoursename,
       curriculum.imajorid,
       major.smajorname,
       major.icollegeid,
      college.scollegename,
         teacher.iteacheid,
      person.sname,
      course.iteachclassid,
      course.istudentnumber,
      course.istudentmaxnumber,
       curriculum.icredit,
       curriculum.ihours,
        classtimetable.iclassroomid,

        classroom.sclassroomname,

         -- classtimetable.istateweek,
         academicbuilding.iacademicbuildingid,
         academicbuilding.nacademicbuildingname,
       campus.icampusid,
       campus.scampusname,
           classtimetable.iweeknumber,
             classtimetable.iweek,
            classtimetable.isection
   from(( ((((((((course left join curriculum on curriculum.scourseid=course.scourseid)
    left join selectiveCoursesAndMark on course.iteachclassid=selectiveCoursesAndMark.Iteachclassid )
    left join teacher on course.iteacheid=teacher.iteacheid)
    left join person on teacher.iid=person.iid)
    left join major on major.imajorid=curriculum.imajorid)
          left join college on college.icollegeid= major.icollegeid)
            left join classtimetable on classtimetable.iteachclassid=course.iteachclassid)
            left join selectivecoursesandmark u on u.iteachclassid=classtimetable.iteachclassid)
            left join classroom on classroom.iclassroomid=classtimetable.iclassroomid)
            left join academicbuilding on academicbuilding.iacademicbuildingid=classroom.iacademicbuildingid)
            left join campus on campus.icampusid=academicbuilding.icampusid           ;
						
						
						
						
						
create or replace view vteacherTcourse as
select
    --  selectivecoursesandmark.scouesestate
      curriculum.scourseid,
       curriculum.scoursename,
         teacher.iteacheid,
      person.sname,
      course.iteachclassid
   from(( ((((((((course left join curriculum on curriculum.scourseid=course.scourseid)
    left join selectiveCoursesAndMark on course.iteachclassid=selectiveCoursesAndMark.Iteachclassid )
    left join teacher on course.iteacheid=teacher.iteacheid)
    left join person on teacher.iid=person.iid)
    left join major on major.imajorid=curriculum.imajorid)
          left join college on college.icollegeid= major.icollegeid)
            left join classtimetable on classtimetable.iteachclassid=course.iteachclassid)
            left join selectivecoursesandmark u on u.iteachclassid=classtimetable.iteachclassid)
            left join classroom on classroom.iclassroomid=classtimetable.iclassroomid)
            left join academicbuilding on academicbuilding.iacademicbuildingid=classroom.iacademicbuildingid)
            left join campus on campus.icampusid=academicbuilding.icampusid
;
; create or replace view vglyshcj as
select distinct
       curriculum.scourseid,
       curriculum.scoursename,
			 course.iteachclassid,
       teacher.iteacheid,
       person.sname ,
			  course.istudentnumber,
				course.istudentmaxnumber,
				course.bscorestate,
				course.scouesestate
   from( ((((course left join curriculum on curriculum.scourseid=course.scourseid)  left join selectiveCoursesAndMark
   on course.iteachclassid=selectiveCoursesAndMark.Iteachclassid ) left join teacher on course.iteacheid=teacher.iteacheid)
    left join person on teacher.iid=person.iid) left join major on major.imajorid=curriculum.imajorid)
          left join college on college.icollegeid= major.icollegeid;
                                                                             
					
					
				create or replace view vxueshengkpaiming as        
				--
select    distinct    student.istudentid,
	                   person.sname		 , 
										 student.idorm,
										 student.igrade,  
										 student.djidian,
                      selectivecoursesandmark.iteachclassid,
											curriculum.scoursename,
											curriculum.scourseid ,
											curriculum.icredit,   
											 selectivecoursesandmark.iscore1,
											 selectivecoursesandmark.iscore2,
											selectivecoursesandmark.iallscore,
											course.bscorestate,  
											
											course.scouesestate  ,  
											adclass.iclassid,
											 adclass.sclassname,
											 major.smajorname,
											 college.scollegename
                      from
                     (((((((( (scott.student   right join scott.selectivecoursesandmark
                       on selectivecoursesandmark.istudentid=student.istudentid)
                       left join course on course.iteachclassid=selectivecoursesandmark.iteachclassid)
                       left join scott.curriculum on scott.curriculum.scourseid=scott.course.scourseid)
                       left join scott.classtimetable on scott.classtimetable.iteachclassid=scott.course.iteachclassid)
                       left join scott.classroom on scott.classroom.iclassroomid = scott.classtimetable.iclassroomid)
											 left join teacher on teacher.iteacheid= course.iteacheid)
											 left join person on         student.iid=person.iid)
											 left join adclass on adclass.iclassid=student.iclassid)
											 left join major on     major.imajorid=adclass.imajorid)  
											 left join college on college.icollegeid=major.icollegeid;

				create or replace view vyhfk as
				select   YHFK.YID,YHFK.IID,YHFK.TIMES,YHFK.MESSAGE    ,person.sname
				from        YHFK left join person on    person.iid=  YHFK.iid;          
				
				
				
				
				create or replace view vJIDIANPAIMING as        
				--
select    distinct    student.istudentid,
	                   person.sname		 , 
										 student.idorm,
										-- student.igrade,  
										 student.djidian,

											adclass.iclassid,
											 adclass.sclassname,
										     adclass.ichengjib
                      from
                     (((((((( (scott.student   right join scott.selectivecoursesandmark
                       on selectivecoursesandmark.istudentid=student.istudentid)
                       left join course on course.iteachclassid=selectivecoursesandmark.iteachclassid)
                       left join scott.curriculum on scott.curriculum.scourseid=scott.course.scourseid)
                       left join scott.classtimetable on scott.classtimetable.iteachclassid=scott.course.iteachclassid)
                       left join scott.classroom on scott.classroom.iclassroomid = scott.classtimetable.iclassroomid)
											 left join teacher on teacher.iteacheid= course.iteacheid)
											 left join person on         student.iid=person.iid)
											 left join adclass on adclass.iclassid=student.iclassid)
											 left join major on     major.imajorid=adclass.imajorid)  
											 left join college on college.icollegeid=major.icollegeid;
											 
 create or replace view vadclassPaiMing as
select
    --  selectivecoursesandmark.scouesestate
     adclass.iclassid,
		 adclass.sclassname,
		 adclass.ichengjib,
		  major.smajorname,
			college.scollegename
 from (adclass left join major on major.imajorid=adclass.imajorid)  
 left join   college on college.icollegeid=major.icollegeid;
                                                                                                
 
 
select b.owner,b.object_name,l.session_id,l.locked_mode from v$locked_object l, dba_objects b where b.object_id=l.object_id


create or replace view vyhfk as
select   YHFK.YID,YHFK.IID,YHFK.TIMES,YHFK.MESSAGE,yhfk.stitle    ,person.sname
				from        YHFK left join person on    person.iid=  YHFK.iid;
