package com.doubleo.memberservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles({"test", "redis", "security"})
class MemberServiceApplicationTests {

    @Test
    void contextLoads() {}
}
