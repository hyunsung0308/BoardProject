<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

<%@ include file="../includes/header.jsp"%>

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
				<!-- /.panel-heading -->
				<div class="panel-body">

					<div class="form-group">
						<label>번호</label> <input class="form-control" name='bno'
							value='<c:out value= "${board.bno}"/>' readonly="readonly">
					</div>

					<div class="form-group">
						<label>제목</label> <input class="form-control" name='title'
							value='<c:out value= "${board.title}"/>' readonly="readonly">
					</div>

					<div class="form-group">
						<label>글쓰기</label>
						<textarea class="form-control" rows="3" name='content'
							readonly="readonly"><c:out value="${board.content}" /></textarea>
					</div>

					<div class="form-group">
						<label>작성자</label> <input class="form-control" name='writer'
							value='<c:out value= "${board.writer}"/>' readonly="readonly">
					</div>

					<button data-oper="modify" class="btn btn-default"
						onclick="location.href='/board/modify?bno=<c:out value="${board.bno }"/>'">Modify</button>

					<button data-oper="list" class="btn btn-info"
						onclick="location.href='/board/list'">List</button>

					<!-- operForm -->
					<!-- operForm이라는 id를 가진 <form> 태그를 이미 이용했기 때문에 cri라는 이름으로 전달된 Criteria 객체를 이용해서 pageNum과 amount값을 태그로 구성한다. -->
					<form id='operForm' action="/board/modify" method="get">

						<input type='hidden' id='bno' name='bno'
							value='<c:out value="${board.bno }"/>'> <input
							type='hidden' name='pageNum'
							value='<c:out value="${cri.pageNum}"/>'> <input
							type='hidden' name='amount'
							value='<c:out value="${cri.amount}"/>'> <input
							type='hidden' name='keyword'
							value='<c:out value="${cri.keyword}"/>'> <input
							type='hidden' name='type' value='<c:out value="${cri.type}"/>'>

					</form>


				</div>
				<!-- end panel-body -->
			</div>
			<!-- end panel-body -->
		</div>
		<!-- end panel-->
	</div>
	<!-- /.row -->

	<div class='row'>
		<div class="col-lg-12">

			<!-- /.panel -->
			<div class="panel panel-default">
			
			<!-- <div class="panel-heading">
					<i class="fa fa-comments fa-fw"></i> Reply
				</div>
			 -->
			 <div class="panel-heading">
					<i class="fa fa-comments fa-fw"></i> Reply
					<button id='addReplyBtn' class='btn btn-primary btn-xs pull-right'>New Reply</button>
				</div>
			 
				<!-- /.panel-heading -->
				<div class="panel-body">
				
					<ul class="chat">
						<!-- start reply -->
						<li class="left clearfix" data-rno='12'>
							<div>
								<div class="header">
									<strong class="primary-font">user00</strong> 
									<small class="pull-right text-muted">2018-01-01 13:13</small>
								</div>
								<p>Good job!</p>
							</div>
						</li>
						<!-- end reply -->
					</ul>
					<!-- ./end ul -->
				</div>
				<!-- /.panel .chat-panel 추가 -->
				<div class="panel-footer">
				
				
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	 aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dailog">
		<div class="modal-content">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4>		
		</div>
		<div class="modal-body">
			<div class="form-group">
				<label>Reply</label>
				<input class="form-control" name='reply' value="New Reply!!!!">
			</div>
			<div class="form-group">
				<label>Replyer</label>
				<input class="form-control" name='replyer' value='replyer'>
			</div>
			<div class="form-group">
				<label>Reply Date</label>
				<input class="form-control" name='replyDate' value=''>
			</div>
			
		</div>
	<div class="modal-footer">
		<button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
		<button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
		<button id="modalRegisterBtn" type="button" class="btn btn-primary">Register</button>
		<button id="modalCloseBtn" type="button" class="btn btn-default">Close</button>
	</div>
			<!-- /.modal-content --> 
	</div>
			<!-- /.modal-dialog --> 
	</div>
	<!-- /.modal --> 



<%@ include file="../includes/footer.jsp"%>


	<!-- script -->
	<script type="text/javascript" src="/resources/js/reply.js"></script>

	<script>
		console.log("================");
		console.log("JS TEST");

		var bnoValue = '<c:out value="${board.bno}"/>';

		//for replyService add test
		/* replyService.add(
			{reply:"JS Test", replyer : "tester", bno:bnoValue}
			,
			function(result) {
				alert("RESULT: " + result);
			} 
			
			);
		 */

		//reply List Test
		replyService.getList({
			bno : bnoValue,
			page : 1
		}, function(list) {

			for (var i = 0, len = list.length || 0; i < len; i++) {
				console.log(list[i]);
			}

		});

		//23번 댓글 삭제 테스트
		/* replyService.remove(23, function(count) {

			console.log(count);

			if (count === "success") {
				alert("REMOVED");
			}

		}, function(err) {
			alert('ERROR...');
		}); */

		//22번 댓글 수정
		replyService.update({
			rno : 22,
			bno : bnoValue,
			reply : "Modified Reply....."
		}, function(result) {
			alert("수정 완료....");
		});

		//댓글조회 단순히 번호만 전달
		replyService.get(10, function(data) {
			console.log(data);
		});
		
	
	</script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
$(document).ready(function(){
	
	var bnoValue = '<c:out value="${board.bno}"/>';
	var replyUL = $(".chat");

		showList(1);
		
		function showList(page) {
		//showList() 함수는 파라미터로 전달되는 page변수를 이용해서 원하는 댓글 페이지를 가져오게 됩니다
		//이때 만일 page번호가 '-1'로 전달되면 마지막 페이지를 찾아서 다시 호출하게 됩니다. 사용자가 새로운 댓글을 추가하면 showList(-1);을 호출하여 우선 
		//전체 댓글의 숫자를 파악하게 합니다. 이후에 다시 마지막 페이지를 호출해서 이동시키는 방식으로 동작시킵니다.
			console.log("show list " + page);
			
			replyService.getList({bno:bnoValue,page: page|| 1}, function(replyCnt, list) {
			
				
				console.log("replyCnt: " + replyCnt);
				console.log("list: " + list);
				console.log(list);
				
				if (page == -1) {
					pageNum = Math.ceil(replyCnt/10.0);
					showList(pageNum);
					return;
				}
				
				
				var str = "";
				if (list == null || list.length == 0 ) {
					
					return;
				}
				
				for (var i = 0, len = list.length || 0; i < len; i++) {
					
					str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
					str += " <div><div class='header'><strong class='primary-font'>["+list[i].rno+"] "+list[i].replyer+"</strong>";
					str += "   <small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small></div>";
					str += "   <p>"+list[i].reply+"</p></div></li>";
				}
			
				replyUL.html(str);
				
				showReplyPage(replyCnt);
				
		});//end function
		
	} //end showList
	
	//새로운 댓글의 추가 버튼 이벤트 처리
	
	var modal = $(".modal");
	var modalInputReply = modal.find("input[name='reply']");
	var modalInputReplyer = modal.find("input[name='replyer']");
	var modalInputReplyDate = modal.find("input[name='replyDate']");
	
	var modalModBtn = $("#modalModBtn");
	var modalRemoveBtn = $("#modalRemoveBtn");
	var modalRegisterBtn = $("#modalRegisterBtn");
	
	$("#addReplyBtn").on("click", function(e){
	
		modal.find("input").val("");
		modalInputReplyDate.closest("div").hide();
		modal.find("button[id != 'modalCloseBtn']").hide();
		
		modalRegisterBtn.show();
		
		$(".modal").modal("show");
	});
	
	//새로운 댓글 추가 처리
	
	modalRegisterBtn.on("click", function(e){
		
		var reply = {
				reply: modalInputReply.val(),
				replyer:modalInputReplyer.val(),
				bno:bnoValue
			};
		replyService.add(reply, function(result){
			
			alert(result);
			
			modal.find("input").val("");
			modal.madal("hide");
			
			//showList(1);
			showList(-1);
		});	
		
	});
	
	//특정 댓글의 클릭 이벤트 처리
	
	$(".chat").on("click", "li", function(e){
		
		var rno = $(this).data("rno");
		
		replyService.get(rno, function(reply){
			
			modalInputReply.val(reply.reply);
			
			modalInputReplyer.val(reply.replyer);
			
			modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly", "readonly");
			
			modal.data("rno", reply.rno);
			
			modal.find("button[id !='modalCloseBtn']").hide();
			
			modalModBtn.show();
			modalRemoveBtn.show();
			
			$(".modal").modal("show");
		});
		
	});
	
	
	//댓글 수정
	
	modalModBtn.on("click", function(e){
		
		var reply = {rno:modal.data("rno"), reply : modalInputReply.val()};
		
		replyService.update(reply, function(result){
		
			alert(result);
			modal.modal("hide");
			showList(pageNum);
			
		});
	
	});
	
	//댓글 삭제
	
	modalRemoveBtn.on("click", function(e){
		
		var rno = modal.data("rno");
		
		replyService.remove(rno, function(result){
		
			alert(result);
			modal.modal("hide");
			showList(pageNum);
			
		});
	
	});
	
	
	
	//댓글 페이지 번호 출력하는 로직
	
	var pageNum = 1;
	var replyPageFooter = $(".panel-footer");
	
	function showReplyPage(replyCnt){
		
		var endNum = Math.ceil(pageNum / 10.0) * 10;
		var startNum = endNum - 9;
		
		var prev = startNum != 1;
		var next = false;
		
		if (endNum * 10 >= replyCnt) {
			endNum = Math.ceil(replyCnt/10.0);
		}
		
		if (endNum * 10 < replyCnt) {
			next = true;
		}
		
		var str = "<ul class='pagination pull-right'>";
		
		if (prev) {
			str += "<li class='page-item'><a class='page-link' href='"+(startNum - 1)+"'>Previous</a></li>";
		}
		
		for (var i = startNum; i <= endNum; i++) {
			
			var active = pageNum == i? "active":"";
			
			str+="<li class='page-item "+active+" '><a class='page-link' href='"+i+"'>"+i+"</a><li>";
		}
		
		if (next) {
			
			str+="<li class='page-item'><a class='page-link' href='"+(endNum +1)+"'>Next</a><li>";
			
		}
		
		str +="</ul></div>";
		
		console.log(str);
		
		replyPageFooter.html(str);
	}
	
	
	//페이지의 번호를 클릭시 새로운 댓글을 가져오도록 하는 부분
	
	replyPageFooter.on("click","li a", function(e){
		
		e.preventDefault();
		console.log("page click");
		
		var targetPageNum = $(this).attr("href");
		
		console.log("targetPageNum: " + targetPageNum);
		
		pageNum = targetPageNum;
	
		showList(pageNum);
		
		});
	//--------------------------------
	var operForm = $("#operForm");

	$("button[data-oper='modify']").on("click", function(e) {

		operForm.attr("action", "/board/modify").submit();

	});

	$("button[data-oper='list']").on("click", function(e) {

		operForm.find("#bno").remove();
		operForm.attr("action", "/board/list")
		operForm.submit;

	});

	console.log(replyService);
	
});
</script>
</body>

</html>
