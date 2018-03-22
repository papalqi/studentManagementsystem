create or replace procedure PinitSys(s int )is
 sdas int;
begin
          delete from selectivecoursesandmark;
					delete from classtimetable;
					delete from leavemessage; 
					delete from course;
					delete from curriculum;
					update student  a set a.idorm=0, a.djidian=0;
				   PaddCurriculum(cID             => 1,
                      sName           => 'c++程序设计',
                      courseNumber    => 0,
                      maxcoursenumber => 3,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1);  
						   PaddCurriculum(cID             => 2,
                      sName           => '数据库设计',
                      courseNumber    => 0,
                      maxcoursenumber => 3,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1); 
							 PaddCurriculum(cID             => 3,
                      sName           => 'java程序设计',
                      courseNumber    => 0,
                      maxcoursenumber => 3,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1); 
								PaddCurriculum(cID             => 4,
                      sName           => '算法基础',
                      courseNumber    => 0,
                      maxcoursenumber => 3,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1); 			
							PaddCurriculum(cID             => 5,
                      sName           => '数据结构',
                      courseNumber    => 0,
                      maxcoursenumber => 3,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1);				
            commit;                         
						   paddcourse(teacherID    =>201522 ,
                    maxNumber    => 50,
                    CurriculumID => 1,
                    adclassID    => sdas) ;
								 paddcourse(teacherID    =>201986 ,
                    maxNumber    => 50,
                    CurriculumID => 1,
                    adclassID    => sdas) ;			
							  paddcourse(teacherID    =>201756 ,
                    maxNumber    => 50,
                    CurriculumID => 1,
                    adclassID    => sdas) ;			
						 	 paddcourse(teacherID    =>214444 ,
                    maxNumber    => 50,
                    CurriculumID => 1,
                    adclassID    => sdas) ;
										for i in 1..20  loop
											insert into selectivecoursesandmark(iteachclassid,
                                               istudentid)
											 values(sdas,(select student.istudentid from student where rownum=1 and student  not in student.istudentid) )  ;
											  
										end loop;
												 
							
            end PinitSys;

