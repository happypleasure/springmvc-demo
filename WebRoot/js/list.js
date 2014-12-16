$(function(){
	loadListPage(1);
});

function loadListPage(targetPage_num){
	$.ajax({
	    url : 'publisher/queryBookList',
		data:{
			pageNum:targetPage_num
		},
		type:'post',
		dataType:'json',
		success:function(result){
			if(result && result.errorCode == '000000'){
				var total = result.total;
				if(total < 1){
					$('#datatbody').html('没有任何数据');
				}else{
					var totalPage = result.totalPage;
					var bookList = result.data;
					/**
					 * 列表数据
					 */
					var dataListTr = getBookListTr(bookList);
					
					$('#datatbody').html(dataListTr);
					/**
					 * 页码数据
					 */
					var pageControlHtml = getDigitalLi(totalPage,targetPage_num);
					
					$('#pageControl').html(pageControlHtml);
					
				}
			}
		}
	});
}

function getBookListTr(bookList){
	var bookDataTr = '';
	for(var i=1;i<=bookList.length;i++){
		var bookTr = bookList[i-1];
		bookDataTr += '<tr>' +
					  '<td>'+i+'</td>'+
					  '<td>'+bookTr.isbn+'</td>' +
					  '<td>'+bookTr.bookNameCn+'</td>'+
					  '<td>'+bookTr.priceType+'</td>'+
					  '<td>'+bookTr.prepareTimeStr+'</td>'+
					  '<td><a href="detail.html?id='+bookTr.id+'"><input type="button" value="查看"></a></td>'+
					  '</tr>';
	}
	return bookDataTr;
}

/*
<div id="pageControl">
<span id='first_page' number="1">首页</span>
<span id='prev_page' number="">上一页</span>
<div id="pageNumber">
<a>1</a>
<a>2</a>
<a>3</a>
</div>
<span id='next_page' number="">下一页</span>
<span id='last_page' number="">末页</span>
</div>
*/
function getDigitalLi(total,targetPage_num){
	var pageControlHtml = '';
	if(total < 1){
		return pageControlHtml;
	}
	
	pageControlHtml += "<nav><ul class='pagination'>";
	
	pageControlHtml += "<li><a id='first_page' number='1' href='javascript:loadListPage("+1+")'>首页</a></li>";
	if(targetPage_num > 1){
		pageControlHtml += "<li><a id='prev_page' number="+(targetPage_num-1)+" href='javascript:loadListPage("+(targetPage_num-1)+")'>&laquo;</a></li>";
	}
	if(total > 9){
		if(targetPage_num <= 5){
			/**
			 * 获取1~9页
			 */
			for(var i=1;i<10;i++){
				if(i == targetPage_num){
					pageControlHtml += "<li class='sr-only'><a number="+i+"  href=javascript:loadListPage("+i+")>"+i+"</a></li>";
				}else{
					pageControlHtml += "<li><a number="+i+" href='javascript:loadListPage("+i+")'>"+i+"</a></li>";
				}
			}
		}else if(total - targetPage_num <= 5){
			/**
			 * 获取targetPage_num-10 ~ targetPage_num 页
			 */
			for(var i = total-8 ; i <= total ; i++){
				if(i == targetPage_num){
					pageControlHtml += "<li class='sr-only'><a number="+i+"  href='javascript:loadListPage("+i+")'>"+i+"</a></li>";
				}else{
					pageControlHtml += "<li><a number="+i+" href='javascript:loadListPage("+i+")'>"+i+"</a></li>";
				};
			};
		}else{
			/**
			 * 处于中间状态的页数
			 */
			for(var i = targetPage_num - 4 ; i <= targetPage_num + 4 ; i++){
				if(i == targetPage_num){
					pageControlHtml += "<li class='sr-only'><a number="+i+"  href='javascript:loadListPage("+i+")'>"+i+"</a></li>";
				}else{
					pageControlHtml += "<li><a number="+i+" href='javascript:loadListPage("+i+")'>"+i+"</a></li>";
				}
			}
		}
	}else{
		/**
		 * 获取1~targetPage_num页
		 */
		for(var i=1; i <= total ; i++){
			if(i == targetPage_num){
				pageControlHtml += "<li class='sr-only'><a number="+i+"  href='javascript:loadListPage("+i+")'>"+i+"</a></li>";
			}else{
				pageControlHtml += "<li><a number="+i+" href='javascript:loadListPage("+i+")'>"+i+"</a></li>";
			}
		}
	}
	if(targetPage_num < total){
		pageControlHtml += "<li><a id='next_page' number="+(targetPage_num + 1)+" href='javascript:loadListPage("+(targetPage_num + 1)+")'>&raquo;</a></li>";
	}
	pageControlHtml += "<li><a id='last_page' number="+total+" href='javascript:loadListPage("+total+")'>末页</a></li>";
	
	pageControlHtml += "  </ul></nav>";
	return pageControlHtml;
};