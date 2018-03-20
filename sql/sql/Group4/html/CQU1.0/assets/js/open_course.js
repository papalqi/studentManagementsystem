$(function () {
	var cookie = localStorage.cook;
	var icredit = localStorage.icredit;
	

		$("#scoursename").val(localStorage.scoursename );
		$("#scourseid").val(localStorage.scourseid );
		$("#smajorname").val(localStorage.smajorname );
		$("#scollegename").val(localStorage.scollegename );
		$("#icredit").val(localStorage.icredit);
		$("#iscournumber").val(localStorage.iscournumber);
		$("#ihours").val(localStorage.ihours);
		$("#inowscournumber").val(localStorage.inowscournumber);
	
 	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectCampus?cookie="+cookie,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("成功");
                    	$.each(data.info, function(i,val){   
                    		var content = "<option value="+val.icampusid+">"+val.scampusname+"</option>";
						    $("#campus_list").append(content);
						  }); 
                    }else if(data.status == 400){
                    	console.log("错误");
                    }else if(data.status == 408){
                    	console.log("超时");
                    }
                },
                error: function (err) {
                    console.log("情况异常!");
                }
           });
           
    
//  $("#classroom_list").change(function(){
//  	var classroomid_string = $("#classroom_list option:selected").val();
//		var classroomid = Number(classroomid_string);
//		timelist[0].classroomid = classroomid;
//  });
//  $("#weeknumber_list").change(function(){
//  	var weeknumber_string  = $("#weeknumber_list option:selected").val();
//		var weeknumber = Number(weeknumber_string);
//		timelist[0].weeknumber = weeknumber;
//  });
//	$("#daynumber_list").change(function(){
//		var week_string = $("#daynumber_list option:selected").val();
//		var week = Number(week_string);
//		timelist[0].week = week;
//	});
//	$("#section_list").change(function(){
//		var section_string = $("#section_list option:selected").val();
//		var section = Number(section_string);
//		timelist[0].ssection = section;
//	});
	
//	for (var i=1;i<7;i++) {
//		var timeinfo = {};
//		timeinfo.classroomid = classroomid;
//		timeinfo.weeknumber = weeknumber+i;
//		timeinfo.week = week;
//		timeinfo.ssection = section;
//		timelist.push(timeinfo);
//	}
//	if(icredit==2){		
//		
//		timelist[0] = {
//		"classroomid": classroomid, //教室id
//          "weeknumber": weeknumber, //第几周
//          "week": week, //星期几
//          "ssection": section //第几节
//
//	};
//	for (var i=1;i<13;i++) {
//		var timeinfo = {};
//		timeinfo.classroomid = classroomid;
//		timeinfo.weeknumber = weeknumber+i;
//		timeinfo.week = week;
//		timeinfo.ssection = section;
//		timelist.push(timeinfo);
//	}
//	}
//	if(icredit==3||icredit==4){
//		$("#course_section").after("<div class='am-form-group'><label for='user-weibo' class='am-u-sm-3 am-form-label'>上课时间-星期几</label><div class='am-u-sm-9'><select id='daynumber_list2' ><option value='1'>周一</option><option value='2'>周二</option><option value='3'>周三</option><option value='4'>周四</option><option value='5'>周五</option><option value='6'>周六</option><option value='7'>周末</option></select></div></div><div id='course_section2' class='am-form-group'><label for='user-weibo' class='am-u-sm-3 am-form-label'>上课时间-第几节</label><div class='am-u-sm-9'><select id='section_list2' ><option value='1'>第一节</option><option value='2'>第二节</option><option value='3'>第三节</option><option value='4'>第四节</option><option value='5'>第五节</option><option value='6'>第六节</option></select></div></div>");
//		$("#daynumber_list2").change(function(){
//		var week2_string = $("#daynumber_list2 option:selected").val();
//		var week2 = Number(week2_string);
//	});
//		$("#section_list2").change(function(){
//		var section2_string = $("#section_list2 option:selected").val();
//		var section2 = Number(section2_string);
//	});
//		timelist[1] = {
//		"classroomid": classroomid, //教室id
//          "weeknumber": weeknumber, //第几周
//          "week": week2, //星期几
//          "ssection": section2 //第几节
//
//	};
//		if(icredit==3){
//			for(var i=1;i<10;i++){
//				var timeinfo = {};
//				timeinfo.classroomid = classroomid;
//				timeinfo.weeknumber = weeknumber+i;
//				timeinfo.week = week;
//				timeinfo.ssection = section;
//				timelist.push(timeinfo);
//				var timeinfo2 = {};
//				timeinfo.classroomid = classroomid;
//				timeinfo.weeknumber = weeknumber+i;
//				timeinfo.week = week2;
//				timeinfo.ssection = section2;
//				timelist.push(timeinfo);
//			}
//		}
//		if(icredit==4){
//			for(var i=1;i<13;i++){
//				var timeinfo ={};
//				timeinfo.classroomid = Number(classroomid);
//				timeinfo.weeknumber = weeknumber+i;
//				timeinfo.week = week;
//				timeinfo.ssection = section;
//				timelist.push(timeinfo);
//				var timeinfo2 = {};
//				timeinfo.classroomid = classroomid;
//				timeinfo.weeknumber = weeknumber+i;
//				timeinfo.week = week2;
//				timeinfo.ssection = section2;
//				timelist.push(timeinfo);
//			}
//		}
//	}
	
       
       
 	$("#campus_list").change(function(){
 		var options=$("#campus_list option:selected");
 		var campus_selected = options.val();
 		$("#teaching_building").empty();
 		$("#teaching_building").append("<option value='0'>请选择教学楼</option>");
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectAcademicbuilding?cookie="+cookie+"&icampusid="+campus_selected,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("教学楼成功");
                    	$.each(data.info, function(i,val){   
                    		var content = "<option value="+val.iacademicbuildingid+">"+val.nacademicbuildingname+"</option>";
						    $("#teaching_building_list").append(content);
						    console.log(val.iacademicbuildingid);
						  });
                    }else if(data.status == 400){
                    	console.log("错误");
                    }else if(data.status == 408){
                    	console.log("超时");
                    }
                },
                error: function (err) {
                    console.log("情况异常!");
                }
           });
 	});
 	
		$("#teaching_building_list").change(function(){
 		var options=$("#teaching_building_list option:selected");
 		var teaching_building_selected = options.val();
 		console.log(teaching_building_selected);
 		$("#class_list").empty();
 		$("#class_list").append("<option value='0'>请选择教室</option>");
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectClassroom?cookie="+cookie+"&iacademicbuildingid="+teaching_building_selected,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("成功");
                    	$.each(data.info, function(i,val){   
                    		var content = "<option value="+val.iclassroomid+">"+val.iclassroomid+"教室人数"+val.iroomnumber+"</option>";
						    $("#classroom_list").append(content);
						  });
                    }else if(data.status == 400){
                    	console.log("错误");
                    }else if(data.status == 408){
                    	console.log("超时");
                    }
                },
                error: function (err) {
                    console.log("情况异常!");
                }
           });
 	});
 	
 	
 	var timelist=[];
 	var n=0;
 	switch(Number(icredit)){
 		case 1:{
 			n=6;
 			$("#course_section").after("<div class='am-form-group'><label for='user-weibo' class='am-u-sm-3 am-form-label'>上课时间-星期几</label><div class='am-u-sm-9'><select id='daynumber_list2' ><option value='1'>周一</option><option value='2'>周二</option><option value='3'>周三</option><option value='4'>周四</option><option value='5'>周五</option><option value='6'>周六</option><option value='7'>周末</option></select></div></div><div id='course_section2' class='am-form-group'><label for='user-weibo' class='am-u-sm-3 am-form-label'>上课时间-第几节</label><div class='am-u-sm-9'><select id='section_list2' ><option value='1'>第一节</option><option value='2'>第二节</option><option value='3'>第三节</option><option value='4'>第四节</option><option value='5'>第五节</option><option value='6'>第六节</option></select></div></div>");
 			break;
 		}
 		case 2:{
 			n=12;
 			break;
 		}
 		case 3:{
 			n=9;
 			break
 		}
 		case 4:{
 			n=12;
 			$("#course_section").after("<div class='am-form-group'><label for='user-weibo' class='am-u-sm-3 am-form-label'>上课时间-星期几</label><div class='am-u-sm-9'><select id='daynumber_list2' ><option value='1'>周一</option><option value='2'>周二</option><option value='3'>周三</option><option value='4'>周四</option><option value='5'>周五</option><option value='6'>周六</option><option value='7'>周末</option></select></div></div><div id='course_section2' class='am-form-group'><label for='user-weibo' class='am-u-sm-3 am-form-label'>上课时间-第几节</label><div class='am-u-sm-9'><select id='section_list2' ><option value='1'>第一节</option><option value='2'>第二节</option><option value='3'>第三节</option><option value='4'>第四节</option><option value='5'>第五节</option><option value='6'>第六节</option></select></div></div>");
 			break;
 		}
 	}
 	$("#open_course_btn").click(function(){
 		
 		var classroomid_string = $("#classroom_list option:selected").val();
		var classroomid = Number(classroomid_string);
		var weeknumber_string  = $("#weeknumber_list option:selected").val();
		var weeknumber = Number(weeknumber_string);
		var week_string = $("#daynumber_list option:selected").val();
		var week = Number(week_string);
		var section_string = $("#section_list option:selected").val();
		var section = Number(section_string);
		
 		for (var i=0;i<=n;i++) {
			var timeinfo = {};
			timeinfo.classroomid = classroomid;
			timeinfo.weeknumber = weeknumber+i;
			timeinfo.week = week;
			timeinfo.ssection = section;
			timelist.push(timeinfo);
		}
 		if(icredit == 3 ||icredit == 4){
 			var week2_string = $("#daynumber_list2 option:selected").val();
			var week2 = Number(week2_string);
			var section2_string = $("#section_list2 option:selected").val();
			var section2 = Number(section2_string);
 			for (var i=0;i<=n;i++) {
			var timeinfo = {};
			timeinfo.classroomid = classroomid;
			timeinfo.weeknumber = weeknumber+i;
			timeinfo.week = week2;
			timeinfo.ssection = section2;
			timelist.push(timeinfo);
			}
 		}
 		var curriculumID = $("#scourseid").val(); 
 		var maxNumber= $("#maxNumber").val(); 
 		var teacherID  = localStorage.account;
 		var data = {};
   		data.timelist = timelist;
   		data.cookie = cookie;
   		data.curriculumID = curriculumID;
   		data.teacherID = teacherID;
   		data.maxNumber = maxNumber;
   		console.log(data.timelist[1].ssection);
   		var data_string = JSON.stringify(data);
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/InsertTeacherCourse",
                type:"post",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:true,
                data:data_string,
                dataType:"json",
                  contentType: "application/json",
                success:function(data){
                    if(data.status == 200){
                    	alert("开设成功");
                    }else if(data.status == 400){
                    	console.log("错误");
                    	console.log("http://192.168.1.187:8080/studentgrade/ChangeCourse?cookie="+cookie+"&couid="+couid+"&couname="+couname+"&couno="+couno+"&majorid="+majorid+"&icredit="+icredit+"&ihours="+ihours);
                    }else if(data.status == 408){
                    	console.log("超时");
                    }
                },
                error: function (err) {
                    console.log("情况异常!");
                    
                }
           });
 	});
 	
});