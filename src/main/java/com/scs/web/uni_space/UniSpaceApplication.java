package com.scs.web.uni_space;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

@MapperScan("com.scs.web.uni_space.mapper")
@EnableSwagger2Doc
public class UniSpaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniSpaceApplication.class, args);
    }

}
