<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add a Class</title>
</head>
<body>
<center>
    <h1>Add a Class</h1>
    <h2>
        <a href="/Students?action=student">List All Student</a>
        <br>
        <br>
        <a href="/ClassSt?action=class">List All Class</a>

    </h2>
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New ClassSt</h2>
            </caption>
            <tr>
                <th>ClassName:</th>
                <td>
                    <input type="text" name="className" id="className" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Number:</th>
                <td>
                    <input type="text" name="number" id="number" size="45"/>
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

