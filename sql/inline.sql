create or replace function NFFindIDwithstuID(studentID int )
 --���Գɹ�
        return int
        is   outTem int;
   /*����������ͨ��ѧ��ѧ�ţ����ز��ҵ�ѧ��ID��*/
          begin
                           select student.Iid
                             into outTem
                             from student
                            where student.istudentid=studentID;
            --commit;
            return outTem;
            end ;
	create or replace function NFFindIDwithteaID(teacherid int )
 --���Գɹ�
        return int
        is   outTem int;
   /*����������ͨ����ʦѧ�ţ����ز��ҵ���ʦID��*/
          begin
                           select teacher.iid
                             into outTem
                             from teacher
                            where teacher.iteacheid=teacherid;
            --commit;
            return outTem;
            end ;       
						                           
							create or replace function NFFindIDwithadmID(teacherid int )
 --���Գɹ�
        return int
        is   outTem int;
   /*����������ͨ������Ա�ţ����ز��ҵĹ���ԱID��*/
          begin
                           select administrators.iid
                             into outTem
                             from administrators
                            where administrators.iadminid=teacherid;
            --commit;
            return outTem;
            end ;       
						
