  /*�޸İ༶����*/
 create or replace procedure PstudentNumberPuls(classID int )is
          begin
            --�޸������������+1
            update  scott.adclass
               set scott.adclass.inumber =scott.adclass.inumber +1
             where classID=adclass.iclassid;
             --�޸�רҵ�ĵ�����+1
             update  scott.major
               set scott.major.istudentnumber =scott.major.istudentnumber +1
             where scott.major.imajorid=(select imajorid from scott.adclass where   classID= scott.adclass.iclassid);
            --�޸�ѧԺ����
               update  scott.college
               set scott.college.istudentnumber= scott.college.istudentnumber +1
               where scott.college.icollegeid=(select scott.major.icollegeid from scott.major where
               major.imajorid=(select imajorid from scott.adclass where   classID= scott.adclass.iclassid));
           
            commit;
            end PstudentNumberPuls;
            

             
