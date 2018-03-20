$(function () {
	var cookie = localStorage.cook;
	var stuid = localStorage.account;
	var week = 1;

            $.ajax({
                url:"http://192.168.1.187:8080/studentgrade/SelectStudentGrade?cookie="+cookie+"&stuid="+stuid,
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
						$.each(data.info, function(i,val){ 
                    		var content = "<tr><td><a class='teaid_td'>"+val.iteachclassid+"</a></td><td>"+val.scoursename+"</td><td class='am-hide-sm-only'>"+val.iscore1+"</td><td class='am-hide-sm-only'>"+val.iscore2+"</td><td class='am-hide-sm-only'>"+val.iallscore+"</td></tr>";
						    $(".am-table tbody").append(content);
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