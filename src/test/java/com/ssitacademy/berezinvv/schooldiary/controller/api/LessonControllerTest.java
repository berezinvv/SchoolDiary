package com.ssitacademy.berezinvv.schooldiary.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssitacademy.berezinvv.schooldiary.App;
import com.ssitacademy.berezinvv.schooldiary.model.Lesson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class LessonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void givenAllLessons_whenGetLessons_thenStatus200() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/api/lessons");
        this.mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void newLesson_thenStatus200_thenContentTypeJSON() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .post("/api/lessons");
        this.mvc
                .perform(post("/api/lessons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    public void findOne_thenStatus200_thenContentTypeJSON() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/api/lessons/2");
        this.mvc
                .perform(get("/api/lessons/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    public void findOne_thenStatus400() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .get("/api/lessons");
        this.mvc
                .perform(get("/api/lessons/key=2"))
                .andExpect(status().is(400));
    }

    @Test
    public void replaceLesson_thenStatus200() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/api/lessons/4");

        String input_json = new ObjectMapper().writeValueAsString(new Lesson(4L, "English"));
        this.mvc
                .perform(put("/api/lessons/4").content(input_json).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").value(4));
    }

    @Test
    public void deleteLesson_thenStatus200() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .delete("/api/lessons/2");
        this.mvc
                .perform(delete("/api/lessons/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"info\": \"DELETE Response\"}"));
    }

}