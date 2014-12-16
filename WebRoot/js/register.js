var flag = true;

$(function(){
	$( ".pretime input" ).datepicker({ dateFormat: 'yy-mm-dd' });
	
	/**
	 * 表单验证
	 */
	initFormFunction();
	
	$('#submit').click(function(){
		onSubmitValidate();
		
		if(!flag){
			return flag;
		}
		var jsonData = getFormData();
		$.ajax({
		    url : 'publisher/register',
			data:jsonData,
			type:'post',
			dataType:'json',
			success:function(result){
				if(result && result > 0){
					window.location.href = 'list.html';
				}else{
					$('#login_error').html('注册失败');
				}
			}
		});
	});
});


function getFormData(){
	var jsonData = null;
	var bookNameCn = $('#bookNameCn').val();
	var seriesName = $('#seriesName').val();
	var bookContentTypeId = $('#bookContentTypeId').val();
	var langId = $('#langId').val();
	var editing = $('#editing').val();
	var prepareTimeStr = $('#prepareTime').val();
	var pages = $('#pages').val();
	var wordage = $('#wordage').val();
	
	/**
	 * 印次
	 */
	var year = $('#year').val();
	var month = $('#month').val();
	var print_count = $('#print_count').val();
	var printerCount = year + ' , ' + month + ' , ' + print_count;
	/**
	 * 开本
	 */
	var hight = $('#hight').val();
	var width = $('#width').val();
	var kaiben = $('#kaiben').val();
	var format = hight + ' , ' + width + ' , ' + kaiben;
	/**
	 * 暂定价
	 */
	var _priceNum = $('#priceNum').val();
	var _type = $('#priceType').val();
	var _count = $('#_count').val();
	var priceType = _priceNum + ' , ' + _type + ' , ' + _count;
	
	jsonData={bookNameCn:bookNameCn,
			  seriesName:seriesName,
			  bookContentTypeId:bookContentTypeId,
			  langId:langId,
			  editing:editing,
			  pages:pages,
			  wordage:wordage,
			  printerCount:printerCount,
			  format:format,
			  priceType:priceType,
			  prepareTimeStr:prepareTimeStr
	         };
	return jsonData;
}

function onSubmitValidate(){
	 flag = true;
	$('#bookNameCn').blur();
	$('#bookContentTypeId').blur();
	$('#editing').blur();
	$('#wordage').blur();
	$('#langId').blur();
	$('#pages').blur();
	
	$('#year').blur();
	var editor_error = $('#editor_error').html();
	if(editor_error == '' || editor_error == null){
		$('#month').blur()
	}
	editor_error = $('#editor_error').html();
	if(editor_error == '' || editor_error == null){
		$('#print_count').blur();
	}
	
	$('#hight').blur();
	var kaiben_error = $('#kaiben_error').html()
	if(kaiben_error == '' || kaiben_error == null){
		$('#width').blur();
	}
	kaiben_error = $('#kaiben_error').html();
	if(kaiben_error == '' || kaiben_error == null){
		$('#kaiben').blur();
	}
}

function initFormFunction(){
	var regExpData =  /^\d+$/;  //整数验证
	var yearExp = /^[1|2]{1}\d{3}$/
	var monthExp = /^[1-9]|1[0-2]$/;
	var pointDataExp=/^\d+(\.\d+)?$/;
	
	$('#bookNameCn').blur(function(){
		var bookNameCn = $('#bookNameCn').val();
		if(bookNameCn == null || bookNameCn ==''){
			$('#book_error').html('请输入书名');
			flag = false;
		}else{
			$('#book_error').html('');
		}
	});
	
	$('#bookContentTypeId').blur(function(){
		var bookContentTypeId = $('#bookContentTypeId').val();
		if(bookContentTypeId == null || bookContentTypeId ==''){
			$('#bookContentType_error').html('请输入图书类型');
			flag = false;
		}else{
			$('#bookContentType_error').html('');
		}
	});
	
	$('#editing').blur(function(){
		var editing = $('#editing').val();
		if(editing == null || editing ==''){
			$('#editing_error').html('请输入责任编辑');
			flag = false;
		}else{
			$('#editing_error').html('');
		}
	});
	
	$('#wordage').blur(function(){
		var wordage = $('#wordage').val();
		if(wordage == null || wordage ==''){
			$('#wordage_error').html('请输入数字');
			flag = false;
		}else if(!regExpData.test(wordage)){
			$('#wordage_error').html("请输入正确整数");
			flag = false;
		}else{
			$('#wordage_error').html('');
		}
	});
	
	$('#langId').blur(function(){
		var langId = $('#langId').val();
		if(langId == null || langId ==''){
			$('#langId_error').html('请输入语种');
			flag = false;
		}else{
			$('#langId_error').html('');
		}
	});
	
	
	/**
	 * 印次
	 */
	$('#year').blur(function(){
		var year = $('#year').val();
		if(year == null || year ==''){
			$('#editor_error').html('');
		}else if(!yearExp.test(year)){
			$('#editor_error').html('请输入正确的年份');
			flag = false;
		}else{
			$('#editor_error').html('');
		}
	});
	
	$('#month').blur(function(){
		var month = $('#month').val();
		if(month == null || month ==''){
				$('#editor_error').html('');
		}else if(!monthExp.test(month)){
				$('#editor_error').html('请输入正确的月份');
			flag = false;
		}else{
				$('#editor_error').html('');
		}
	});
	
	$('#print_count').blur(function(){
		var print_count = $('#print_count').val();
		if(print_count == null || print_count ==''){
			$('#editor_error').html('');
		}else if(!regExpData.test(print_count)){
				$('#editor_error').html('请输入正确的印次数');
			flag = false;
		}else{
			$('#editor_error').html('');
		}
	});
	
	$('#hight').blur(function(){
		var hight = $('#hight').val();
		if(hight == null || hight ==''){
			$('#kaiben_error').html('');
		}else if(!pointDataExp.test(hight)){
			$('#kaiben_error').html('请输入正确数字');
			flag = false;
		}else{
			$('#kaiben_error').html('');
		}
	});
	
	
	$('#width').blur(function(){
		var width = $('#width').val();
		if(width == null || width ==''){
			$('#kaiben_error').html('');
		}else if(!pointDataExp.test(width)){
			$('#kaiben_error').html('请输入正确数字');
			flag = false;
		}else{
			$('#kaiben_error').html('');
		}
	});
	
	$('#pages').blur(function(){
		var pages = $('#pages').val();
		if(pages == null || pages ==''){
			$('#pages_error').html('');
		}else if(!regExpData.test(pages)){
			$('#pages_error').html('请输入正确数字');
			flag = false;
		}else{
			$('#pages_error').html('');
		}
	});
	
	$('#kaiben').blur(function(){
		var kaiben = $('#kaiben').val();
		if(kaiben == null || kaiben ==''){
			$('#kaiben_error').html('');
		}else if(!regExpData.test(kaiben)){
			$('#kaiben_error').html('请输入正确数字');
			flag = false;
		}else{
			$('#kaiben_error').html('');
		}
	});
};