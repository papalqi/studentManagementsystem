create or replace procedure PDeleteStudent(studentID int)   is
        temid int;
        temCid int;
        temColegeID int;
 --ɾ��ѧ����Ϣ
--���Գɹ�
                begin
                                      select iclassid  into temCid from student
                                    where student.istudentid=studentID;
                                 NPstudentNumberSub(temCid);

                                 temid:=NFFindIDwithstuID(studentID);
																 delete from leavemessage where  leavemessage.iid=temid;
                                delete from scott.student where   student.istudentid=studentID;
                                delete from scott.person where   person.iid=temid;
                  commit;
end PDeleteStudent;
