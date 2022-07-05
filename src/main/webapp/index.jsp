<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<center>
    <h1>Student Management</h1>
    <h2>
        <a href="/ClassSt?action=class">Show List Class</a>
        <br>
        <br>
        <a href="/Students?action=create">Add New Student</a>
        <br>
        <br>
        <a href="/Students?action=find">find Student by Name</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Students</h2></caption>
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
        <c:forEach var="student" items="${students}">
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