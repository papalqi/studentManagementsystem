create or replace view vleaveMessage as
select distinct
        leavemessage.mid,
       leavemessage.iid,
			 leavemessage.iteachclassid,
			 leavemessage.scomment,
			 leavemessage.tcommenttime,
			 person.sname   ,
			 person.spageaddress
       from      leavemessage left join person  on  leavemessage.iid =    person.iid;
			 
