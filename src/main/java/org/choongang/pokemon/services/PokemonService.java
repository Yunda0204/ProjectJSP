//package org.choongang.pokemon.services;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.IOException;
//
//public class PokemonService {
//
//        private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon/";
//
//        public JsonNode getPokemon(String name) throws ClientProtocolException, IOException {
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpGet request = new HttpGet(POKEAPI_URL + name);
//            CloseableHttpResponse response = httpClient.execute(request);
//
//            String jsonResponse = EntityUtils.toString(response.getEntity());
//            ObjectMapper objectMapper = new ObjectMapper();
//            return objectMapper.readTree(jsonResponse);
//        }
//}
