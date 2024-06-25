<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Pokémon List</title>
</head>
<body>
    <h1>Pokémon List</h1>
    <ul>
        <c:forEach items="${pokemonData.results}" var="pokemon">
            <li>
                <!-- 포켓몬 이름 출력 -->
                <h2>${pokemon.name}</h2>
                <!-- 포켓몬 이미지 출력 (front_default 스프라이트 사용) -->
                <img src="${pokemon.sprites.front_default}" alt="${pokemon.name} image">
                <!-- 포켓몬 몸무게 출력 -->
                <p><strong>Weight:</strong> ${pokemon.weight}</p>
                <!-- 포켓몬 타입 출력 -->
                <p><strong>Types:</strong>
                    <c:forEach items="${pokemon.types}" var="type">
                        ${type.type.name}
                    </c:forEach>
                </p>
                <p><strong>Description:</strong>
                    <c:forEach items="${pokemon.flavor_text_entries}" var="entry">
                        <c:if test="${entry.language.name == 'kr'}">
                            ${entry.flavor_text}
                            <br>
                        </c:if>
                    </c:forEach>
                </p>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
<%--
          <!DOCTYPE html>
          <html>
          <head>
              <title>Pokémon List</title>
          </head>
          <body>
              <h1>Pokémon List</h1>
              <ul>
                  <c:forEach items="${pokemonData.results}" var="pokemon">
                      <li>${pokemon.name}</li>

                  </c:forEach>
              </ul>
          </body>
          </html>
--%>
