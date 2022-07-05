<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 7/4/2022
  Time: 8:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Find Student By Name</title>
</head>
<body>
<center>
    <h1>Find Student By Name</h1>
    <h2>
        <a href="/Students?action=user">List All Student</a>
    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New User</h2>
            </caption>
            <tr>
                <th>Name:</th>
                <td>
                    <input type="text" name="name" id="name" size="45"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Find</h2></caption>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>BirthDay</th>
            <th>Address</th>
            <th>Phone</th>
            <th>Email</th>
            <th>Class</th>
            <th>Adjust</th>
        </tr>
        <c:forEach var="student" items="${findStudents}">
            <tr>
                <td><c:out value="${student.id}"/></td>
                <td><c:out value="${student.name}"/></td>
                <td><c:out value="${student.birthDay}"/></td>
                <td><c:out value="${student.address}"/></td>
                <td><c:out value="${student.phone}"/></td>
                <td><c:out value="${student.email}"/></td>
                <td><c:out value="${student.classSt.className}"/></td>
                <td>
                    <a href="/Students?action=edit&id=${student.id}">Edit</a>
                    <a href="/Students?action=delete&id=${student.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

