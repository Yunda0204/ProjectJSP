package org.choongang.pokemon.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Controller;
import org.choongang.global.config.annotations.GetMapping;
import org.choongang.global.config.annotations.RequestMapping;

@Controller
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokemonController_YG {
    private final HttpServletRequest request;

    @GetMapping
    public String index() {
        String uri = request.getRequestURI();
        System.out.println(uri);
        return "pokemon/index";
    }
}

//@WebServlet("/pokemon")
//public class PokemonController extends HttpServlet {
//
//    private final ApiRequestService apiRequestService = new ApiRequestService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String apiUrl = "https://pokeapi.co/api/v2/pokemon";
//        HttpResponse<String> apiResponse = apiRequestService.request(apiUrl, "GET");
//        Map<String, Object> data = apiRequestService.toMap(apiResponse);
//
//        req.setAttribute("pokemonData", data);
//        req.getRequestDispatcher("/WEB-INF/templates/pokemon/index.jsp").forward(req, resp);
//    }
//}

//
//private final HttpServletRequest request;
//
//@GetMapping
//public String index() {
//    // Get the ApiRequestService from the BeanContainer
//    ApiRequestService apiRequestService = BeanContainer.getInstance().getBean(ApiRequestService.class);
//
//    String apiUrl = "https://pokeapi.co/api/v2/pokemon";
//    HttpResponse<String> apiResponse = apiRequestService.request(apiUrl, "GET");
//    Map<String, Object> data = apiRequestService.toMap(apiResponse);
//
//    // Add the data to the request attributes
//    request.setAttribute("pokemonData", data);
//
//
//    String uri = request.getRequestURI();
//    System.out.println(uri);
//    return "pokemon/index"; // Forward to the JSP page
//}