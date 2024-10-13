package com.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
public class ProtobufTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProtobufTestApplication.class, args);
    }

    @PostConstruct
    public void startGrpcServer() throws IOException, InterruptedException {
        // gRPC 서버 시작
        EmployeeServer employeeServer = new EmployeeServer();
        employeeServer.startGrpcServer();
    }
}
