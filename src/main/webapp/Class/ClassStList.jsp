<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Class Management Application</title>
</head>
<body>
<center>
    <h1>Class Management</h1>
    <h2>
        <a href="/Students?action=users">List All Student</a>
        <br>
        <br>
        <a href="/ClassSt?action=createClassSt">Add New Class</a>
        <br>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of ClassSt</h2></caption>
        <tr>
            <th>ClassId</th>
            <th>ClassName</th>
            <th>Number</th>
            <th>Adjust</th>

        </tr>
        <c:forEach var="cl" items="${classSts}">
            <tr>
                <td><c:out value="${cl.classId}"/></td>
                <td><c:out value="${cl.className}"/></td>
                <td><c:out value="${cl.number}"/></td>
                <td>
                    <a href="/ClassSt?action=editClassSt&classId=${cl.classId}">Edit</a>
                    <a href="/ClassSt?action=deleteClassSt&classId=${cl.classId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

