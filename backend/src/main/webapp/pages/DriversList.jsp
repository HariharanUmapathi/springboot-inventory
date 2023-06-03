<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <h1>
        ${data}
        Here comes Driver List</h1></br>
        <c:set var="i" value="0">
        <c:out value="{$i+1}" />
        <c:forEach var="entry" items="${drivers}">
            ${i++}
            Name: ${entry.DriverId} <br />
            Value: ${entry.Name} <br />
        </c:forEach>
    </h1>
</body>

</html>