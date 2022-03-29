package com.sparta.post;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // @WebServlet 어노테이션이 동작하게 함
@SpringBootTest
class PostApplicationTests {

    @Test
    void contextLoads() {
    }

}
