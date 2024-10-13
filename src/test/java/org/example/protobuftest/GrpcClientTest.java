package org.example.protobuftest;

import com.example.protobuf.Employee;
import com.example.protobuf.EmployeeServiceGrpc;
import com.example.protobuf.GetEmployeeRequest;
import com.example.protobuf.GetEmployeeResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrpcClientTest {
    private ManagedChannel channel;
    private EmployeeServiceGrpc.EmployeeServiceBlockingStub blockingStub;

    @BeforeEach
    public void setUp() {
        // gRPC 채널 생성
        channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext() // TLS 비활성화 (개발용)
                .build();

        // 클라이언트 Stub 생성
        blockingStub = EmployeeServiceGrpc.newBlockingStub(channel);
    }

    @AfterEach
    public void tearDown() {
        // 채널 종료
        channel.shutdown();
    }

    @Test
    public void testGetEmployee() {
        // 요청 메시지 생성
        GetEmployeeRequest request = GetEmployeeRequest.newBuilder()
                .setEmployeeNo("83900")
                .build();

        // 서버 호출
        GetEmployeeResponse response = blockingStub.getEmployee(request);

        // 응답 확인
        Employee employee = response.getEmployee();
        assertEquals("83900", employee.getEmployeeNo());
        assertEquals("신명하", employee.getName());
        assertEquals(Employee.Company.LG_CNS, employee.getType());
        assertEquals(29, employee.getAge());
    }
}
