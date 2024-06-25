//package org.choongang.pokemon.controllers;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.choongang.pokemon.services.PokemonService;
//
//import java.io.IOException;
//
//@WebServlet("/pokemon")public class PokemonServlet extends HttpServlet {
//
//    private PokemonService pokemonService = new PokemonService();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name = req.getParameter("name");
//        JsonNode pokemonData = pokemonService.getPokemon(name);
//
//        req.setAttribute("pokemonData", pokemonData);
//        req.getRequestDispatcher("/WEB-INF/templates/pokemon/index.jsp").forward(req, resp);
//    }
//}