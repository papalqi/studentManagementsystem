$(function () {
 	var cookie = localStorage.cook;
 	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/CollegeNameList?cookie="+cookie,
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
                    		var n = i+1;
                    		var content = "<option value="+val.collegeid+">"+val.collegename+"</option>";
						    $("#college_list").append(content);
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
 	$("#college_list").change(function(){
 		var options=$("#college_list option:selected");
 		var college_selected = options.val();
 		$("#major_list").empty();
 		$("#major_list").append("<option value='0'>请选择专业</option>");
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/MajorNameList?cookie="+cookie+"&collegeid="+college_selected,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("添加成功");
                    	$.each(data.info, function(i,val){   
                    		var content = "<option value="+val.majorid+">"+val.majorname+"</option>";
						    $("#major_list").append(content);
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
	
	$("#major_list").change(function(){
 		var options=$("#major_list option:selected");
 		var major_selected = options.val();
 		$("#class_list").empty();
 		$("#class_list").append("<option value='0'>请选择班级</option>");
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/ADClassNameList?cookie="+cookie+"&majorid="+major_selected,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("添加成功");
                    	$.each(data.info, function(i,val){   
                    		
                    		var content = "<option value="+val.adclassid+">"+val.adclassname+"</option>";
						    $("#class_list").append(content);
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
	
 	$("#add_student_btn").click(function(){
 		var stuid_string = $("#stuid").val(); 
 		var stuid = Number(stuid_string);
 		var stuname = $("#stuname").val();
 		var classid_string = $("#class_list option:selected").val();
 		var classid = Number(classid_string);
 		
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/InsertStudent?cookie="+cookie+"&stuid="+stuid+"&stuname="+stuname+"&classid="+classid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	alert("添加成功");
                    	
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
           
   });