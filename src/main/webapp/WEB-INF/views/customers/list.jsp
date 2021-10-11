<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    table {
        border: 1px solid #000;
    }

    th, td {
        border: 1px dotted #555;
    }
</style>
There are ${customers.size()} customer(s) in list.
<table>
    <caption>Customers List</caption>
    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="c">
        <tr>
            <td>
                ${c.id}
            </td>
            <td>
                <a href="/customer/${c.id}">${c.name}</a>
            </td>
            <td>
                ${c.email}
            </td>
            <td>
               ${c.address}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>