create or replace procedure PinitSys(s int )is
 sdas int;
begin
          delete from selectivecoursesandmark;
					delete from classtimetable;
					delete from leavemessage; 
					delete from course;
					delete from curriculum;
					update student  a set a.idorm=0, a.djidian=0;    
					update adclass  set adclass.ichengjib=0;
				   PaddCurriculum(cID             => 'CST001',
                      sName           => 'c++�������',
                      courseNumber    => 3,
                      maxcoursenumber => 0,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1);  
						   PaddCurriculum(cID             => 'CST002',
                      sName           => '���ݿ����',
                      courseNumber    => 3,
                      maxcoursenumber => 0,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1); 
							 PaddCurriculum(cID             => 'CST003',
                      sName           => 'java�������',
                      courseNumber    => 3,
                      maxcoursenumber => 0,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1); 
								PaddCurriculum(cID             => 'CST004',
                      sName           => '�㷨����',
                      courseNumber    => 3,
                      maxcoursenumber => 0,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1); 			
							PaddCurriculum(cID             => 'CST005',
                      sName           => '���ݽṹ',
                      courseNumber    => 3,
                      maxcoursenumber => 0,
                      credit          => 3,
                      hours           => 36,
                      majorid         => 1);				
            commit;
            end PinitSys;  
						
						
						create or replace procedure PinitSys1(s int )is
 sdas int;
begin
               paddcourse(teacherID    =>201522 ,
                    maxNumber    => 50,
                    CurriculumID =>'CST001',
                    adclassID    => sdas) ;
								 paddcourse(teacherID    =>201986 ,
                    maxNumber    => 50,
                    CurriculumID => 'CST001',
                    adclassID    => sdas) ;			
							  paddcourse(teacherID    =>201756 ,
                    maxNumber    => 50,
                    CurriculumID => 'CST001',
                    adclassID    => sdas) ;			
						 
						     PselectCourse(studentID => 20130048, courseID => SDAS)  ;
														 PselectCourse(studentID => 20130016, courseID => SDAS)  ;
														  PselectCourse(studentID => 20130021, courseID => SDAS)  ;
															 PselectCourse(studentID => 20130027, courseID => SDAS)  ;
															  PselectCourse(studentID => 20125373, courseID => SDAS)  ;
																 PselectCourse(studentID => 20125355, courseID => SDAS)  ; 
																 PselectCourse(studentID => 20125395, courseID => SDAS)  ; 
																 PselectCourse(studentID => 20130284, courseID => SDAS)  ;
																  PselectCourse(studentID => 20130272, courseID => SDAS)  ; 
																	PselectCourse(studentID => 20130307, courseID => SDAS)  ;
																	 PselectCourse(studentID => 20130318, courseID => SDAS)  ;
																	  PselectCourse(studentID => 20132039, courseID => SDAS)  ;
																  PselectCourse(studentID => 20130348, courseID => SDAS)  ;

			
           
            commit;
            end PinitSys1;
                                   
						                	   
