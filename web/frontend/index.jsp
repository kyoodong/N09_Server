<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></scr
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

  <script src="<c:url value="/frontend/index.js"/>"></script>
  <link rel="stylesheet" href="<c:url value="/frontend/index.css"/>">
</head>

<body>
<div>
  <h1>웨이짱 광고 서버</h1>
  <div>
    <table class="table" striped hover>
      <thead>
      <tr>
        <th>이름</th>
        <th>시작시간</th>
        <th>끝시간</th>
        <th>이미지</th>
        <th>링크</th>
        <th>삭제</th>
      </tr>
      </thead>
      <tbody id="ad-rows">

      </tbody>
    </table>
  </div>
  <button type="button" class="btn btn-primary" data-target="#createAd" data-toggle="modal" id="create-ad-button">추가</button>
</div>

<div class="modal fade" id="createAd" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">광고 추가 및 수정</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="input-group mb-3">
            <div class="input-group-append">
              <span class="input-group-text" id="basic-addon1">광고이름</span>
            </div>
            <input type="text" class="form-control" aria-label="광고이름" aria-describedby="basic-addon1" id="name" required>
          </div>

          <div class="input-group mb-3">
            <div class="input-group-append">
              <span class="input-group-text" id="basic-addon2">시작시간</span>
            </div>
            <input type="number" class="form-control" aria-label="시작시간" aria-describedby="basic-addon2" id="startTime" required>
          </div>

          <div class="input-group mb-3">
            <div class="input-group-append">
              <span class="input-group-text" id="basic-addon3">끝시간</span>
            </div>
            <input type="number" class="form-control" aria-label="끝 시간" aria-describedby="basic-addon3" id="endTime" required>
          </div>

          <div class="input-group mb-3">
            <div class="custom-file">
              <input type="file" class="custom-file-input" id="image">
              <label id="image-label" class="custom-file-label" for="image" aria-describedby="inputGroupFileAddon02">광고 사진을 고르세요</label>
            </div>
          </div>

          <div class="input-group mb-3">
            <div class="input-group-append">
              <span class="input-group-text" id="basic-addon4">링크</span>
            </div>
            <input type="text" class="form-control" aria-label="링크" aria-describedby="basic-addon4" id="linkUrl" required>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
        <button id="submit-button" type="submit" class="btn btn-primary">확인</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>
