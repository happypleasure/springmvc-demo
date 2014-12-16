 $(function(){
	 $('#submit').click(function(){
		var formAccountId = $("#formAccountId").val();
		var password = $("#password").val();
		if(!formAccountId || formAccountId==''){
			$('#formAccountId_error').html('用户账号不能为空!');
		}	
		if(!password || password==''){
			$('#password_error').html("密码不能为空!");
		}
		$.ajax({
		    url : 'publisher/login',
			data:{
				formAccountId:formAccountId,
				password:password
			},
			type:'post',
			dataType:'json',
			success:function(result){
				var dataP = eval(result);
				if(dataP.accountId&&dataP.email&&dataP.userName&&dataP.password){
					/**
					 * 登录成功跳转到列表页面
					 */
					window.location.href = 'list.html';
				}else{
					$('#login_error').html("用户名或密码错误");
				}
			}
		});
	 });
	 
	 $("#formAccountId").focus(function(){
		 $('#password_error').html("");
		 $('#formAccountId_error').html('');
		 $('#login_error').html("");
	 });
	 
	 $("#password").focus(function(){
		 $('#password_error').html("");
		 $('#formAccountId_error').html('');
		 $('#login_error').html("");
	 });
 });