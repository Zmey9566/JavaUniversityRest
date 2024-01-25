//package com.example.javauniversityrest;
//
//import io.restassured.RestAssured;
//import io.restassured.filter.log.RequestLoggingFilter;
//import io.restassured.filter.log.ResponseLoggingFilter;
//import models.Name;
//import models.ReadTestUser;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.*;
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class FakeApiTest {
//
//    @BeforeAll
//    public static void setUp() {
//        filters(
//                new RequestLoggingFilter(),
//                new ResponseLoggingFilter(),
//                CustomTpl.customLogFiilter()
//                        .withCustomTemplates()
//        );
//
//        baseURI= "https://fakestoreapi.com";
//    }
//
//    @Test
//    void getAllTest() {
//        given()
//                .get("/users")
//                .then()
//                .statusCode(200);
//    }
//
//    @Test
//    void getSingleUserTest() {
//        int userId = 2;
//
////        given()
////                .pathParam("userId", userId)
////                .get("/users/{userId}")
////                .then()
////                .statusCode(200)
////                .body("id", equalTo(userId))
////                .body("address.zipcode", matchesPattern("\\d{5}-\\d{4}"));
//
//        final var responseReadUserTest = given()
//                .pathParam("userId", userId)
//                .get("/users/{userId}")
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(userId))
//                .body("address.zipcode", matchesPattern("\\d{5}-\\d{4}"))
//                .extract().as(ReadTestUser.class);
//
//        final var responseNameUserTest = given()
//                .pathParam("userId", userId)
//                .get("/users/{userId}")
//                .then()
//                .statusCode(200)
//                .extract()
//                        .jsonPath()
//                        .getObject("name", Name.class);
//
//        assertAll(
//                    () -> assertEquals(responseNameUserTest.getFirstname(), "david"),
//                    () -> assertEquals(responseNameUserTest.getLastname(), "morrison"),
//                    () -> assertEquals(responseReadUserTest.getEmail(), "morrison@gmail.com")
//        );
//    }
//}
