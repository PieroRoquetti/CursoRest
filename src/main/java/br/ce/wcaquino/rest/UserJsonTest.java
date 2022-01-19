package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class UserJsonTest {
	@Test
	public void devoVerificarPrimeiroNivel () {
		given()
		.when()
			.get("https://restapi.wcaquino.me/users/1")
		.then()
			.statusCode(200)
			.body("id", is(1))
			.body("name", containsString("Silva"))
			.body("age", greaterThan(18))
			.body("salary", is(1234.5677))
			;
		}
	
	@Test
	//Outra forma de verificar as ações
	public void devoverificarPrimeiroNivelOutrasFormas() {
		Response response = RestAssured.request(Method.GET,"https://restapi.wcaquino.me/users/1");	
		
		//path
		System.out.println(response.path("id"));
		Assert.assertEquals(1, response.path("id"));
		
	}	
}
