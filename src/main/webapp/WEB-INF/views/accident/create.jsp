<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form  action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <td><label>Заявитель (ФИО)
                <input type='text' name='name'>
            </label></td>
        </tr>

        <tr>
            <td><label>Адрес нарушения
                <input type='text' name='address'>
            </label></td>
        </tr>

        <tr>
            <td><label>ГосНомер
                <input type='text' name='number'>
            </label></td>
        </tr>

        <tr>
            <td><label>Описание нарушения
                <input type='text' name='description'>
            </label></td>
        </tr>

        <tr>
            <td>Тип:</td>
            <td>
                <select name="type.id">
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>
