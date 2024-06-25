package org.choongang.pokemon.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Controller;
import org.choongang.global.config.annotations.GetMapping;
import org.choongang.global.config.annotations.RequestMapping;
import org.choongang.global.services.ApiRequestService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokemonControllerasdas extends HttpServlet {
    private final HttpServletRequest request;
    private final ApiRequestService apiRequestService;

    @GetMapping
    public String index() {
        String uri = request.getRequestURI();
        System.out.println(uri);

        // 포켓몬 데이터를 가져옵니다.
        String url = "https://pokeapi.co/api/v2/pokemon?limit=100";
        Map<String, Object> pokemonData = apiRequestService.toMap(apiRequestService.request(url));

        System.out.println(pokemonData);

        // Ensure the results are properly extracted and passed to the JSP
        List<Map<String, Object>> results = (List<Map<String, Object>>) pokemonData.get("results");
        for (Map<String, Object> result : results) {
            System.out.println(result);
        }
        request.setAttribute("pokemonData", results);

        return "pokemon/index";

//        // 포켓몬 데이터를 request 객체에 추가합니다.
//        List<Map<String, Object>> results = (List<Map<String, Object>>) pokemonData.get("results");
//        request.setAttribute("pokemonData", results);
//
//        return "pokemon/index";
    }
}