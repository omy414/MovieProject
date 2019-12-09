<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MovieAce</title>
<!--======================상단 메뉴 바 검은색으로 고정 ===========================  -->
<script>
	var headerDesktop = $('.container-menu-desktop');
	var wrapMenu = $('.wrap-menu-desktop');
	$(headerDesktop).addClass('fix-menu-desktop');
	$(window).on('scroll', function() {
		if (true) {
			$(headerDesktop).addClass('fix-menu-desktop');
			$(wrapMenu).css('top', 0);
		}

	});
</script>
<script src="/resources/js/modal_js.js"></script>
<style>
.modal {
	top: 13%;
	margin-top: -50px;
}
</style>
</head>
<body>

	<!-- Modal -->
	<div id="myModal" class="modal fade bd-example-modal-lg" tabindex="-1" role="dialog"
		aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
				</div>
				<div class="modal-body"></div>
				<table>
					<th></th>
					<th></th>
					<tr class="modal-1st-li">
						<td rowspan="5">
							<img src="" id="thumnail">
						</td>
					</tr>
					<tr>
						<td>
							<ul>
								<h2><li class="modal-head-movieNm"></h2>
								<li class="modal-director"></li>
								<li class="modal-actor"></li>	
								<li class="modal-openDT"></li>
								<li class="modal-runtime"></li>
								<li class="modal-genre"></li>											
							</ul>
						<td>	
					</tr>					
				</table>
				<table>
					<tr>
						<td>
							<ul>
								<br>
								<br>
								<li><h3>줄거리</h3><br><br></li>
								<li class="modal-plot"></li>
							</ul>
						</td>
					</tr>
				</table>
				<br>
				<div style="border: 0.3px solid #bcbcbc;"></div>
				<br>
				<div class="rateArea" >
				 별점영역
				
				</div>
				<div class="form-group">
               	<form>
                    <label for="inputComment">Comments</label>
                    <textarea class="form-control" id="inputComment" rows="4" style="width:60%;"></textarea>
                	<button style="background-color:#4CAF50; border:none; border-radius:5px; color:white; width:60px;">test</button>
                </form>
                </div>
				<div class="modal-footer">
						
				</div>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h1 style="text-align: center;">
		<b>통합검색 </b>
	</h1>
	<h3>검색한 단어는 : ${keyword }</h3>
	<%-- <h4> 영화명 검색 결과:${MovieSearchResult }</h4> --%>
	<%-- <h4>감독명 검색 결과:${DirectorSearchResult }</h4> --%>

	<table id="example-table-1" border="1">
		<th>연출작 검색결과:</th>
		<th>${fn:length(DirectorSearchResult)}건</th>
		<c:if test="${not empty DirectorSearchResult }">
			<c:forEach var="dir" items="${DirectorSearchResult }">
				<tr>
					<td rowspan="4">
						<c:if test="${dir.thumnailURL ne null}">
							<a data-toggle="modal" data-target=".bd-example-modal-lg"
								onclick="clicke('${dir.thumnailURL }','${dir.plot }','${dir.movieNm }','${dir.director }','${dir.genres }','${dir.actors }','${dir.typeNm }','${dir.runtime }','${dir.openDate }');">
								<img src="${dir.thumnailURL }">
							</a>
						</c:if> 
						<c:if test= "${dir.thumnailURL eq null}">
							<c:set var = "url" value="/resources/img/basicposter.png"/>
							<a data-toggle="modal" data-target=".bd-example-modal-lg"
								onclick="clicke('${url }','${dir.plot }','${dir.movieNm }','${dir.director }','${dir.genres }','${dir.actors }','${dir.typeNm }','${dir.runtime }','${dir.openDate }');">
							<img src="${pageContext.request.contextPath}/resources/img/basicposter.png">															
							</a>
						</c:if>
					</td>
						
					<td> 영화명: 
					<c:if test="${dir.thumnailURL ne null}">
						<a data-toggle="modal" data-target=".bd-example-modal-lg" 
						onclick="clicke('${dir.thumnailURL }','${dir.plot }','${dir.movieNm }','${dir.director }','${dir.genres }','${dir.actors }','${dir.typeNm }','${dir.runtime }','${dir.openDate }');"> 
						${dir.movieNm }</a>
					</c:if>
					<c:if test= "${dir.thumnailURL eq null}">
						<c:set var = "url" value="/resources/img/basicposter.png"/>
						<a data-toggle="modal" data-target=".bd-example-modal-lg" 
						onclick="clicke('${url }','${dir.plot }','${dir.movieNm }','${dir.director }','${dir.genres }','${dir.actors }','${dir.typeNm }','${dir.runtime }','${dir.openDate }');"> 
						${dir.movieNm }
						</a>
					</c:if>
					</td>
				</tr>
				<tr>
					<td>감독명: ${dir.director }</td>
				</tr>
				<tr>
					<td>장르 : ${dir.genres }</td>
				</tr>
				<tr>
					<td>배우 : ${dir.actors }</td>
				</tr>

				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty DirectorSearchResult }">
			<tr>
				<td></td>
				<td>검색결과가 없습니다</td>
			</tr>
		</c:if>

		<th>영화명 검색결과:</th>
		<th>${fn:length(MovieSearchResult)}건</th>
		<c:if test="${not empty MovieSearchResult }">
			<c:forEach var="m" items="${MovieSearchResult }">
				<tr>
					<td rowspan="4">
					<c:if test="${m.thumnailURL ne null}">
						<a data-toggle="modal" data-target=".bd-example-modal-lg" 
						onclick="clicke('${m.thumnailURL }','${m.plot }','${m.movieNm }','${m.director }','${m.genres }','${m.actors }','${m.typeNm }','${m.runtime }','${m.openDate }');">
						<img src="${m.thumnailURL }" onclick=""></a>
					</c:if>	
					<c:if test= "${m.thumnailURL eq null}">
					<c:set var = "url" value="/resources/img/basicposter.png"/>
							<a data-toggle="modal" data-target=".bd-example-modal-lg"
								onclick="clicke('${url }','${m.plot }','${m.movieNm }','${m.director }','${m.genres }','${m.actors }','${m.typeNm }','${m.runtime }','${m.openDate }');">
								<img src="/resources/img/basicposter.png">								
							</a>
					</c:if>
					</td>
					
					<td>
					<c:if test="${m.thumnailURL ne null}">
						<a data-toggle="modal" data-target=".bd-example-modal-lg" 
						onclick="clicke('${m.thumnailURL }','${m.plot }','${m.movieNm }','${m.director }','${m.genres }','${m.actors }','${m.typeNm }','${m.runtime }','${m.openDate }');">
						영화명: ${m.movieNm }</a>
					</c:if>	
					<c:if test= "${m.thumnailURL eq null}">	
					<c:set var = "url" value="/resources/img/basicposter.png"/>
						<a data-toggle="modal" data-target=".bd-example-modal-lg"
						onclick="clicke('${url }','${m.plot }','${m.movieNm }','${m.director }','${m.genres }','${m.actors }','${m.typeNm }','${m.runtime }','${m.openDate }');">
						영화명: ${m.movieNm }</a>
					</c:if>
					</td>
				</tr>
				<tr>
					<td>감독명: ${m.director }</td>
				</tr>
				<tr>
					<td>장르 : ${m.genres }</td>
				</tr>
				<tr>
					<td>배우 : ${m.actors }</td>
				</tr>
  
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty MovieSearchResult }">
			<tr>
				<td></td>
				<td>검색결과가 없습니다</td>
			</tr>
		</c:if>
	</table>
	
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
</body>
</html>