package com.doubleo.memberservice.domain.member.grpc.server;

import com.doubleo.memberservice.domain.member.repository.MemberRepository;
import com.doubleo.memberservice.global.exception.CommonException;
import com.doubleo.memberservice.global.exception.errorcode.MemberErrorCode;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MemberGrpcServiceImpl extends MemberServiceGrpc.MemberServiceImplBase {

    private final MemberRepository memberRepository;

    public MemberGrpcServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void getMember(MemberRequest request, StreamObserver<MemberResponse> responseObserver) {
        memberRepository
                .findById(request.getMemberId())
                .ifPresentOrElse(
                        res -> {
                            MemberResponse resp =
                                    MemberResponse.newBuilder()
                                            .setMemberId(res.getId())
                                            .setMemberEmail(res.getEmail())
                                            .setMemberName(res.getName())
                                            .setMemberRegNo(res.getRegNo())
                                            .setMemberContact(res.getContact())
                                            .build();
                            responseObserver.onNext(resp);
                            responseObserver.onCompleted();
                        },
                        () ->
                                responseObserver.onError(
                                        new CommonException(MemberErrorCode.MEMBER_NOT_FOUND)));
    }
}
