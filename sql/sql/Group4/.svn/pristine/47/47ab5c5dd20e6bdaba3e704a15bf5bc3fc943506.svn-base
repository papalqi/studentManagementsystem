$(function () {
	var cookie = localStorage.cook;
	var teaid= localStorage.account;
	console.log(teaid);

	$.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectTeacherCourse?cookie="+cookie+"&teaid="+teaid,
                type:"get",
                xhrFields: {  
                        withCredentials: true  
                    }, 
                crossDomain: true,
                async:false,
                dataType:"json",
                success:function(data){
                    if(data.status == 200){
                    	console.log("显示成功！");
                    	$.each(data.info, function(i,val){   
                    		console.log("1");
                    		var content = "<tr><td><a>"+val.scourseid+"</a></td><td>"+val.scoursename+"</td><td>"+val.scollegename+"</td><td>"+val.smajorname+"</td><td class='am-hide-sm-only'>"+val.icredit+"</td><td class='am-hide-sm-only'>"+val.ihours+"</td><td class='couid_td'>"+val.iteachclassid +"</td><td class='am-hide-sm-only'>"+val.istudentnumber+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-danger  delete_butt'><span class='am-icon-trash-o'></span> 进入班级</button></div></div></td></tr>";
						    $(".am-table tbody").append(content);
						  }); 
	                }else if(data.status == 400){
	                	console.log("显示失败");
	                }
                },
                error: function (err) {
                    console.log("超时!");
                }
            });
            
            
             $(".delete_butt").click(function(){
	 			localStorage.couclassid = $(this).parents("tr").find(".couid_td").text();
	 			
	            document.mycourse_form.action="teacher_grade_input.html";
	            $(".am-form").submit();
        });
            
});