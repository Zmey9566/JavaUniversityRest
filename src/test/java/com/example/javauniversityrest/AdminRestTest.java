//package com.example.javauniversityrest;
//
//import io.restassured.filter.log.RequestLoggingFilter;
//import io.restassured.filter.log.ResponseLoggingFilter;
//import models.Name;
//import models.ReadTestUser;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.*;
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.matchesPattern;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AdminRestTest {
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
//        baseURI= "http://localhost:8080/admins/";
//    }
//
//    @Test
//    void getSingleUserTest() {
//        int userId = 2;
//
//        final var responseReadUserTest = given()
////                .pathParam("userId", userId)
//                .get("/")
//                .then()
//                .statusCode(200)
//                .body("id", equalTo(userId))
////                .body("address.zipcode", matchesPattern("\\d{5}-\\d{4}"))
//                .extract().as(ReadTestUser.class);
//
////        final var responseNameUserTest = given()
////                .pathParam("userId", userId)
////                .get("/users/{userId}")
////                .then()
////                .statusCode(200)
////                .extract()
////                .jsonPath()
////                .getObject("name", Name.class);
//
////        assertAll(
////                () -> assertEquals(responseNameUserTest.getFirstname(), "david"),
////                () -> assertEquals(responseNameUserTest.getLastname(), "morrison"),
////                () -> assertEquals(responseReadUserTest.getEmail(), "morrison@gmail.com")
////        );
//    }
//}
