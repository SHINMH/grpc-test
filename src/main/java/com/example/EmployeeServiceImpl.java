package com.example;

import com.example.protobuf.Employee;
import com.example.protobuf.EmployeeServiceGrpc;
import com.example.protobuf.GetEmployeeRequest;
import com.example.protobuf.GetEmployeeResponse;
import io.grpc.stub.StreamObserver;

public class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {
    @Override
    public void getEmployee(GetEmployeeRequest request, StreamObserver<GetEmployeeResponse> responseObserver) {
        // 요청받은 employeeNo로 직원 정보를 가져온다 (여기서는 하드코딩된 데이터 사용)
        Employee employee = Employee.newBuilder()
                .setEmployeeNo("83900")
                .setName("신명하")
                .setType(Employee.Company.LG_CNS)
                .setNickname("JORDAN")
                .setAge(29)
                .build();

        // 응답 메시지 생성
        GetEmployeeResponse response = GetEmployeeResponse.newBuilder()
                .setEmployee(employee)
                .build();

        // 응답 전송
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}