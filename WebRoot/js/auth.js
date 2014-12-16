$(function(){
	 var Request = GetRequest();
	 var redirect_uri = Request['redirect_uri'];
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
			    url : 'publisher/auth',
				data:{
					formAccountId:formAccountId,
					password:password
				},
				type:'post',
				dataType:'json',
				success:function(result){
					if(result&&result > 0){
						/**
						 * 授权成功之后返回注册中心页面
						 */
						alert('授权成功');
						window.location.href = redirect_uri;
					}else if(result&&result == -1){
						$('#login_error').html("账号密码错误");
					}else{
						$('#login_error').html("或者授权失败");
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
});


function GetRequest() {
   var url = location.search; //获取url中"?"符后的字串
   var theRequest = new Object();
   if (url.indexOf("?") != -1) {
      var str = url.substr(1);
      strs = str.split("&");
      for(var i = 0; i < strs.length; i ++) {
         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
      }
   }
   return theRequest;
}