<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%-- 헤더 파일 넣기 --%>
<%@ include file="../includes/header.jsp" %>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">게시글 등록</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            게시글 입력하기
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <form role="form" action="modify" method="post">
                                    <input type="hidden" name="bno" value="${board.bno}">
                                    <input type="hidden" name="pageNum" value=${cri.pageNum}>
                                    <input type="hidden" name="amount" value=${cri.amount}>
                                        <div class="form-group">
                                            <label>제목</label>
                                            <input class="form-control" placeholder= "제목 입력" name = "title" value="${board.title}" id="title">
                                        </div>
                                        
                                        <div class="form-group">
                                            <label>작성자</label>
                                            <input class="form-control" placeholder= "작성자 입력" name = "writer" value="${board.writer}" readonly>
                                        </div>
                              
                                        <div class="form-group">
                                            <label>내용</label>
                                            <textarea class="form-control" rows="15" placeholder= "내용 입력" name = "content" id="content" >${board.content}</textarea>
                                            
                                        </div>
                                        
                                        <button type="button" class="btn btn-default" data-oper="modify">수정하기</button>
                                        <button type="button" class="btn btn-default" data-oper="remove">삭제하기</button>
                                        <button type="button" class="btn btn-default" data-oper="list">목록보기</button>
                                        
                                    </form>
                                </div>
                                
                                
                                   
                                </div>
                                <!-- /.col-lg-6 (nested) -->
                            </div>
                            <!-- /.row (nested) -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

   <!-- Modal -->
                            <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                            <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                                        </div>
                                        <div class="modal-body">
                                        수정된 내용이 없습니다.
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                                      
                                        </div>
                                    </div>
                                    <!-- /.modal-content -->
                                </div>
                                <!-- /.modal-dialog -->
                            </div>
                            <!-- /.modal -->  
                            
<%--footer 파일 넣기 --%>    
<%@ include file="../includes/footer.jsp" %>
    
 		<script> //p262
	$(function(){  
		var formObj=$("form");
		
		$("button").on("click",function(e){
			e.preventDefault(); //버튼에 걸린 이벤트 없애고
			var operation = $(this).data("oper") // data-oper속성의 값읽기 즉,어떤 버튼인지 확인
			if(operation==="modify"){ //수정 버튼이 눌리면
				//1.원본 제목,내용 읽기
				var title="${board.title}";
				console.log("원본제목:",title);
				var content=`${board.content}`;
				console.log("원본 내용:",content);
				//2.수정 제목,내용 읽기
				var user_title=$("#title").val();
				console.log("수정제목:",user_title);
				var user_content=$("#content").val();
				console.log("수정제목:",user_content);
				if(title==user_title && content==user_content){ //수정안됨
					alert("수정하고 수정버튼 눌러라!!!!");
					return;
				}
			}
			else if(operation==="remove") {//삭제 버튼이 눌리면
				//1. remove.jsp로 보내기
				
				
				formObj.attr("action","/board/remove");
				formobj.attr("method", "get");
			}
			else if(operation==="list"){ //리스트 버튼이 눌리면
				self.location="/board/list";  //board/list 요청
				return; //리턴없으면 아래문장이 실행됨으로
			}
			console.log("submit 동작합니다.");
			formObj.submit(); //submit 전송
		}); //e는 버튼 이벤트
		
		
		
	});
	</script>
    
    
    
 
</body>

</html>
