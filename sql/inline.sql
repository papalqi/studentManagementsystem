create or replace function NFFindIDwithstuID(studentID int )
 --测试成功
        return int
        is   outTem int;
   /*内联函数，通过学生学号，返回查找的学生ID，*/
          begin
                           select student.Iid
                             into outTem
                             from student
                            where student.istudentid=studentID;
            --commit;
            return outTem;
            end ;
	create or replace function NFFindIDwithteaID(teacherid int )
 --测试成功
        return int
        is   outTem int;
   /*内联函数，通过老师学号，返回查找的老师ID，*/
          begin
                           select teacher.iid
                             into outTem
                             from teacher
                            where teacher.iteacheid=teacherid;
            --commit;
            return outTem;
            end ;       
						                           
							create or replace function NFFindIDwithadmID(teacherid int )
 --测试成功
        return int
        is   outTem int;
   /*内联函数，通过管理员号，返回查找的管理员ID，*/
          begin
                           select administrators.iid
                             into outTem
                             from administrators
                            where administrators.iadminid=teacherid;
            --commit;
            return outTem;
            end ;       
						

