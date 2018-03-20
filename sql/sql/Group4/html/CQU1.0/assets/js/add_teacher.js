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
           
 	$("#add_teacher_btn").click(function(){
 		var teaid_string = $("#teaid").val(); 
 		var teaid = Number(teaid_string);
 		var teaname = $("#teaname").val();
 		var collegeid_string = $("#college_list option:selected").val();
 		var collegeid = Number(collegeid_string);
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/InsertTeacher?cookie="+cookie+"&teaid="+teaid+"&teaname="+teaname+"&collegeid="+collegeid,
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