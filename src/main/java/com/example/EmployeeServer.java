package com.example;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class EmployeeServer {
    public void startGrpcServer() throws IOException, InterruptedException {
        // gRPC 서버 생성 및 시작
        Server server = ServerBuilder.forPort(8080)
                .addService(new EmployeeServiceImpl())
                .build();

        System.out.println("Server started on port 8080");
        server.start();
        server.awaitTermination();
    }
}