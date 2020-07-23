<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../includes/header.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Board Register</h1>	
		</div>
		<!-- / . col-lg-12  -->
	</div>
	<!-- /.row -->
	
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
			
				<div class="panel-heading">현성이의 일기장</div>
				<br>
				<!-- /.panel-heading -->
				<div class="panel-body">
				
					<form role="form" action="/board/modify" method="post">
					
					
					<div class="form-group">
						<label>번호</label> <input class="form-control" name='bno' value='<c:out value= "${board.bno}"/>' readonly="readonly">
					</div>
					
					<div class="form-group">
						<label>제목</label>  <input class="form-control" name='title' value='<c:out value= "${board.title}"/>'>
					</div>
					
					<div class="form-group">
						<label>글쓰기</label>
						<textarea class="form-control" rows="3" name='content' ><c:out value= "${board.content}"/></textarea>
					</div>
					
					<div class="form-group">
						<label>작성자</label>
						 <input class="form-control" name='writer' value='<c:out value= "${board.writer}"/>' readonly="readonly">
					</div>
					
					<div class="form-group">
						<label>RegDate</label>
						<input class="form-control" name='regDate'
						value='<fmt:formatDate pattern="yyyy/MM/dd" value = "${board.regdate}" />' readonly="readonly">
					</div>
					
					<div class="form-group">
						<label>Update Date</label>
						<input class="form-control" name='updateDate'
							value='<fmt:formatDate pattern="yyyy/MM/dd" value ="${board.updateDate}"/>' readonly="readonly">
					</div>
					
					<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>
					
					<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
					
					<button type="submit" data-oper='list' class="btn btn-info">List</button>


					<!-- 추가 -->
					<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
					
					<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
					
					<input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
					
					<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
					
				</form>
			</div>
			<!-- end panel-body -->
		</div>
			<!-- end panel-body -->
	</div>
	<!-- end panel-->
	</div>
	<!-- /.row -->
	<%@include file="../includes/footer.jsp" %>
	
	<script type="text/javascript">
	$(document).ready(function(){
		
		var formObj = $("form");
		
		$('button').on("click", function(e){
			
			//기본동작을 막는다. 마지막에 직접 submit;
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if (operation === 'remove') {
				formObj.attr("action" , "/board/remove");
			}else if (operation === 'list') {
				//move to list
				formObj.attr("action", "/board/list").attr("method", "get");
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				//modify.jsp에서는 다시 목록으로 이동하는 경우에 필요한 파라미터만 전송하기 위해서 <form> 태그의 모든 내용을 지우고 다시 추가하는 방식을 이용했기에 keyword , type역시 추가해야한다.
				var keywordTag = $("input[name='keyword']").clone();
				var typeTag = $("input[name='type']").clone();
				
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
				formObj.append(keywordTag);
				formObj.append(typeTag);
			}
			formObj.submit();
		});
	});
	</script>
	
	
</body>
</html>