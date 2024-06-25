<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<layout:main>
    <h1>포켓몬</h1>
    <c:choose>
        <c:when test="${not empty pokemonData.results}">
            <ul>
                <c:forEach var="pokemon" items="${pokemonData.results}">
                    <li>
                        <a href="${pokemon.url}">${pokemon.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>No Pokémon data available.</p>
        </c:otherwise>
    </c:choose>

    <!-- Debugging Information -->
    <h2>Debugging Information</h2>
    <pre>
        <c:out value="${pokemonData}" />
    </pre>
</layout:main>
<%--
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<layout:main>
    <h1>포켓몬</h1>
</layout:main>
--%>
