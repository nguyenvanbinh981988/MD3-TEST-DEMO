<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Student Management Application</title>
</head>
<body>
<center>
  <h1>Student Management</h1>
  <h2>
    <a href="Students?action=users">List All Student</a>
  </h2>
</center>
<div align="center">
  <form method="post">
    <table border="1" cellpadding="5">
      <caption>
        <h2>Add New Student</h2>
      </caption>
      <tr>
        <th>Name:</th>
        <td>
          <input type="text" name="name" id="name" size="45"/>
        </td>
      </tr>
      <tr>
        <th>BirthDay:</th>
        <td>
          <input type="Date" name="birthDay" id="birthDay" size="45"/>
        </td>
      </tr>
      <tr>
        <th>Address:</th>
        <td>
          <input type="text" name="address" id="address" size="45"/>
        </td>
      </tr>
      <tr>
        <th>Phone:</th>
        <td>
          <input type="text" name="phone" id="phone" size="45"/>
        </td>
      </tr>
      <tr>
        <th>Email:</th>
        <td>
          <input type="text" name="email" id="email" size="45"/>
        </td>
      </tr>
      <tr>
        <td>Class:</td>
        <td><select name="classId" id="classId">
          <c:forEach var="c" items="${classSts}">
            <option value="${c.classId}">${c.className}</option>
          </c:forEach>
        </select></td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <input type="submit" value="Save"/>
        </td>
      </tr>
    </table>
  </form>
</div>
</body>
</html>
