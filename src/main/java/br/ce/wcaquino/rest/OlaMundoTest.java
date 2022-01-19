package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	
	@Test
	public void TestOlaMundo() {
		//método que acessa a api (get)
		Response response = request(Method.GET,"https://restapi.wcaquino.me/ola");
		//procura um texto que é igual à ola mundo
		Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
		//Espera que retorne um status = 200
		Assert.assertTrue(response.statusCode() == 200);
		//Faz uma excessão caso o status não for = 200.
		Assert.assertTrue("o status code deveria ser 200", response.statusCode() == 200);
		Assert.assertEquals(200, response.statusCode());
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}
	
	@Test
	public void DevoConhecerOutrasFormasRestAssured() {
		Response response = request(Method.GET,"https://restapi.wcaquino.me/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
		//busca a url e então verifica se o status code é 200
		get("https://restapi.wcaquino.me/ola").then().statusCode(200);
		
		//método mais legível
		given() //pré condições
		.when() //ação - condição
			.get("https://restapi.wcaquino.me/ola")
		.then() //Assertivas
			.statusCode(200);
	}
		
	@Test
	public void DevoConhecerMatchersHamcrest()	{
		//fazer igualdades
		assertThat("maria", Matchers.is("maria"));
		assertThat(200, Matchers.is(200));
		assertThat(200, Matchers.isA(Integer.class));
		assertThat(200d, Matchers.isA(Double.class));
		assertThat(200d, Matchers.greaterThan(120d));
		
		//listas
		List<Integer> impares = Arrays.asList(1,3,5,7,9);
		assertThat(impares, hasSize(5));
		assertThat(impares, contains(1,3,5,7,9));
		assertThat(impares, containsInAnyOrder(1,3,7,9,5));
		assertThat(impares, hasItem(5));
		assertThat(impares, hasItems(1, 5));
		
		//conectar varias assertivas dentro de uma mesma lógica
		assertThat("maria", not("joão"));
		assertThat("Maria", anyOf(is("Maria"), is("joaquina")));
		assertThat("joaquina", allOf(startsWith("joa"),endsWith("ina"), containsString("qui")));
	}
	
	@Test
	public void devoValidarBody() {
		given()
		.when()
			.get("https://restapi.wcaquino.me/ola")
		.then()
			.statusCode(200)
			.body(is("Ola Mundo!"))
			.body(containsString("Mundo"))
			.body(is(not(nullValue())));

	}
 }