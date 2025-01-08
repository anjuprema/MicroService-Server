package com.anju.demo.configuration;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anju.demo.service.EmployeeServiveGrpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

@Component
public class MicroGrpcControllerServer {

    @Autowired
    private EmployeeServiveGrpc empService;

    private Server server;

    @PostConstruct
    public void startGrpcEmployeeServer() throws IOException {
        if (empService == null) {
            throw new IllegalStateException("empService is not injected!");
        }

        server = ServerBuilder.forPort(9058)
                              .addService(empService)
                              .build();

        new Thread(() -> {
            try {
                server.start();
                System.out.println("gRPC employee service server started at port " + server.getPort());
                server.awaitTermination();
                System.out.println("gRPC employee service server stopped.. port " + server.getPort());
            } catch (IOException | InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Error starting or running gRPC server: " + e.getMessage());
            }
        }).start();
    }

    @PreDestroy
    public void stopGrpcServer() {
        if (server != null) {
            server.shutdown();
            System.out.println("gRPC employee service server stopped.");
        }
    }
}
