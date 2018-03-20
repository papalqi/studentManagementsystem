$(function () {
//		console.log(view_stuname);
		var cookie = localStorage.cook;
		$("#teaname").val(localStorage.teaname);
		$("#teaid").val(localStorage.teaid);
		$("#teatitle").val(localStorage.teatitle);
		$("#teaphoneno").val(localStorage.teaphoneno);
		$("#teamail").val(localStorage.teamail);
		
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
		
		$("#change_teacher_btn").click(function(){
 		var teaid_string = $("#teaid").val(); 
 		var teaid = Number(teaid_string);
 		var teaname = $("#teaname").val();
 		var collegeid_string = $("#college_list option:selected").val();
 		var collegeid = Number(collegeid_string);
 		var teatitle = $("#teatitle").val();
 		$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/UpdataTeacher?cookie="+cookie+"&teaid="+teaid+"&teaname="+teaname+"&collegeid="+collegeid+"&teatitle="+teatitle,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	alert("修改成功");
                    	
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