package org.choongang.pokemon.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.choongang.global.services.ApiRequestService;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;

@WebServlet("/pokemon")
public class PokemonController extends HttpServlet {

    private final ApiRequestService apiRequestService = new ApiRequestService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String apiUrl = "https://pokeapi.co/api/v2/pokemon/";
        HttpResponse<String> apiResponse = apiRequestService.request(apiUrl, "GET");
        Map<String, Object> data = apiRequestService.toMap(apiResponse);

        req.setAttribute("pokemonData", data);
        req.getRequestDispatcher("/WEB-INF/templates/pokemon/index.jsp").forward(req, resp);
    }
}

