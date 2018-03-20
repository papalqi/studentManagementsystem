$(function () {
	var cookie = localStorage.cook;
	var teaid = localStorage.account;
	var week = 1;

            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectOneTeacher?cookie="+cookie+"&teaid="+teaid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("成功！");
//                  	console.log(data.stuname);
						$(".diinfo1").html("姓名："+data.teaname+"<br> 学院："+data.teacollege+"<br> 职称："+data.teatitle+"<br> 工号："+data.teaid+"<br>");
                    	$(".diinfo2").html("联系电话： "+data.teaphoneno+"<br> 邮箱："+data.teamail+"<br> 办公地址：主教-1801<br>");
	                }else if(data.status == 400){
	                	console.log("失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectTeacherTableCourse?cookie="+cookie+"&teaid="+teaid+"&week="+week,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("课表成功！");
                    	$.each(data.info, function(i,val){ 
                    		var isection = val.isection;
                    		var iweek = val.iweek;
                    		var iweek_number = Number(iweek)+1;
                    		switch (Number(isection)){
							  		case 1:{
							  			console.log("1");
							  			$("#first_section td").eq(iweek_number).text("["+val.scoursename+"]"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#first_section td").eq(iweek_number).css("background-color","#FFCC99");
							  			break;
							  		};
							 		case 2:{
							 			console.log("2");
							 			$("#second_section td").eq(iweek_number).text("["+val.scoursename+"]"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#second_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
							  		case 3:{
							  			console.log("3");
							 			$("#third_section td").eq(iweek_number).text("["+val.scoursename+"]"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#third_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
							        case 4:{
							        	console.log("4");
							 			$("#forth_section td").eq(iweek_number).text("["+val.scoursename+"]"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#forth_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
							  		case 5:{
							  			console.log("5");
							 			$("#fifth_section td").eq(iweek_number).text("["+val.scoursename+"]"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#fifth_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
							  		case 6:{
							  			console.log("6");
							 			$("#sixth_section td").eq(iweek_number).text("["+val.scoursename+"]"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#sixth_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
								}	
						});
	                }else if(data.status == 400){
	                	console.log("显示失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
     });