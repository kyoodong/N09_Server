<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
  <!-- 개발 버전 -->
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>

  <!-- 상용 버전 -->
  <%--  <script src="https://cdn.jsdelivr.net/npm/vue"></script>--%>
  <!-- vue router -->
  <script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
</head>

<body>
  <div id="app">
    <router-view></router-view>
  </div>
</body>

<%--<script src="<c:url value="/frontend/index.js"/>"></script>--%>
<%--<script src="<c:url value="/build.js"/>"></script>--%>
<script src="dist/build.js"></script>
</html>
