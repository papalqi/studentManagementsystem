$(function() {
				var user = JSON.parse(localStorage.getItem("userInfo"));
				var n = localStorage.student_amount;
				var page_number = n/8+1;
				$("#pagination1").pagination({
					currentPage: 1,
					totalPage: page_number,
					callback: function(current) {
						var student_begin = (current-1)*8;
						$(".am-table tbody").empty();
						for (var i=0;i<8;i++) {
							var content = "<tr><td><input type='checkbox'></td><td><a class='stuid_td'>"+user[student_begin+i].stuid+"</a></td><td>"+user[student_begin+i].stuname+"</td><td>"+user[student_begin+i].stucollege+"</td><td class='am-hide-sm-only'>"+user[student_begin+i].stumajor+"</td><td class='am-hide-sm-only'>"+user[student_begin+i].stuclass+"</td><td><div class='am-btn-toolbar'><div class='m-btn-group am-btn-group-xs'><button class='am-btn am-btn-default am-btn-xs am-text-secondary change_butt'><span class='am-icon-pencil-square-o'></span> 编辑</button><button class='am-btn am-btn-default am-btn-xs  view_butt'><span class='am-icon-copy'></span> 查看</button><button class='am-btn am-btn-default am-btn-xs am-text-danger  delete_butt'><span class='am-icon-trash-o'></span> 删除</button></div></div></td></tr>";
							
							$(".am-table tbody").append(content);
						}
						
					}
				});
});