<%--
  Created by IntelliJ IDEA.
  User: satsu
  Date: 06/11/2020
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="jumbotron text-center">
        <h1>Customer Portal</h1>
    </div>
    <div>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam
            erat lectus, vehicula feugiat ultricies at, tempus sed ante. Cras
            arcu erat, lobortis vitae quam et, mollis pharetra odio. Nullam sit
            amet congue ipsum. Nunc dapibus odio ut ligula venenatis porta non
            id dui. Duis nec tempor tellus. Suspendisse id blandit ligula, sit
            amet varius mauris. Nulla eu eros pharetra, tristique dui quis,
            vehicula libero. Aenean a neque sit amet tellus porttitor rutrum nec
            at leo.</p>

        <h2>Existing Customers</h2>
        <div class="well">
            <b>Enter the intranet: </b><a href="/users">users</a>
        </div>
    </div>
    <div id="pagefoot" th:include="layout :: footerFragment">Footer
    </div>
</div>
<!-- container -->

</body>
</html>
