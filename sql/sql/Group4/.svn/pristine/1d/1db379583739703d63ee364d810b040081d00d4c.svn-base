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
 	
 	$("#add_course_btn").click(function(){
 		var couid = $("#couid").val(); 
 		var couno_string = $("#couno").val(); 
 		var couno = Number(couno_string);
 		var icredit_string = $("#icredit").val(); 
 		var icredit = Number(icredit_string);
 		var ihours_string = $("#ihours").val(); 
 		var ihours = Number(ihours_string);
 		var couname = $("#couname").val();
 		var majorid_string = $("#major_list option:selected").val();
 		var majorid = Number(majorid_string);
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/InsertCourse?cookie="+cookie+"&couid="+couid+"&couname="+couname+"&majorid="+majorid+"&couno="+couno+"&icredit="+icredit+"&ihours="+ihours,
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
                    	console.log("http://192.168.1.187:8080/studentgrade/InsertCourse?cookie="+cookie+"&couid="+couid+"&couname="+couname+"&majorid="+majorid+"&couno="+couno+"&icredit="+icredit+"&ihours="+ihours);
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