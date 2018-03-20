$(function () {
	var cookie = localStorage.cook;
	var stuid = localStorage.account;
	var week = 2;
	
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectOneStudent?cookie="+cookie+"&stuid="+stuid,
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
						$(".diinfo1").html("姓名："+data.stuname+"<br> 学院："+data.stucollege+"<br> 专业："+data.stumajor+"<br> 班级："+data.stuclass+"班<br>");
                    	$(".diinfo2").html("学号： "+data.stuid+"<br> 联系电话："+data.stuphoneno+"<br> 邮箱："+data.stumail+"<br>");
	                }else if(data.status == 400){
	                	console.log("失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
	
	
	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectStudentCourse?cookie="+cookie+"&stuid="+stuid+"&week="+week,
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
						$.each(data.info, function(i,val){ 
                    		var isection = val.isection;
                    		var iweek = val.iweek;
                    		var iweek_number = Number(iweek)+1;
                    		switch (Number(isection)){
							  		case 1:{
							  			console.log("1");
							  			$("#first_section td").eq(iweek_number).text("["+val.scoursename+"]"+"/"+val.sname+"/"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#first_section td").eq(iweek_number).css("background-color","#FFCC99");
							  			break;
							  		};
							 		case 2:{
							 			console.log("2");
							 			$("#second_section td").eq(iweek_number).text("["+val.scoursename+"]"+"/"+val.sname+"/"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#second_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
							  		case 3:{
							  			console.log("3");
							 			$("#third_section td").eq(iweek_number).text("["+val.scoursename+"]"+"/"+val.sname+"/"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#third_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
							        case 4:{
							        	console.log("4");
							 			$("#forth_section td").eq(iweek_number).text("["+val.scoursename+"]"+"/"+val.sname+"/"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#forth_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
							  		case 5:{
							  			console.log("5");
							 			$("#fifth_section td").eq(iweek_number).text("["+val.scoursename+"]"+"/"+val.sname+"/"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#fifth_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
							  		case 6:{
							  			console.log("6");
							 			$("#sixth_section td").eq(iweek_number).text("["+val.scoursename+"]"+"/"+val.sname+"/"+val.nacademicbuildingname+val.sclassroomname);
							  			$("#sixth_section td").eq(iweek_number).css("background-color","FFCC99");
							  			break;
							 		}
								}
                   });
	                }else if(data.status == 400){
	                	console.log("失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
	
	
     });