<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Edit Class</title>
</head>
<body>
<div align="center">
  <form  method="post">
    <table border="1" cellpadding="5">
      <caption>
        <h2>
          Edit Class
        </h2>
      </caption>
      <c:if test="${classSt != null}">
        <input type="hidden" name="classId" value="<c:out value='${classSt.classId}' />"/>
      </c:if>
      <tr>
        <th>Name:</th>
        <td>
          <input type="text" name="className" size="45"
                 value="<c:out value='${classSt.className}' />"
          />
        </td>
      </tr>
      <tr>
        <th>Number:</th>
        <td>
          <input type="text" name="number" size="45"
                 value="<c:out value='${classSt.number}' />"
          />
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
</body>
</html>

