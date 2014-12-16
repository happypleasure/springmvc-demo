$(function(){
    var Request = GetRequest();
    var bookId = Request['id'];
	$.ajax({
	    url : 'publisher/queryBookDetail?id='+bookId,
		type:'GET',
		dataType:'json',
		success:function(result){
			if(result){
				$('#div.listright>p').text('ISBN号：'+result.isbn);
				$('#bookName>dd').text(result.bookNameCn);
				$('#seriesName>dd').text(result.seriesName);
				$('#bookContentTypeId>dd').text(result.bookContentTypeId);
				$('#langId>dd').text(result.langId);
				$('#editing>dd').text(result.editing);
				$('#prepareTimeStr>dd').text(result.prepareTimeStr);
				$('#pages>dd').text(result.pages);
				$('#wordage>dd').text(result.wordage);
				$('#printerCount>dd').text(result.printerCount);
				$('#format>dd').text(result.format);
				$('#priceType>dd').text(result.priceType);
			}
		}
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