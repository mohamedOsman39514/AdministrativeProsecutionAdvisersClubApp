package com.example.proxy.rest.handler;

import com.example.proxy.entity.Request;
import com.example.proxy.entity.Status;
import com.example.proxy.entity.User;
import com.example.proxy.rest.dto.RequestDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.exception.*;
import com.example.proxy.rest.entitymapper.RequestMapper;
import com.example.proxy.security.UserLoggedInUtil;
import com.example.proxy.service.RequestService;
import com.example.proxy.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RequestHandler {

    private RequestMapper requestMapper;
    private RequestService requestService;
    private StatusService statusService;
    private PaginationMapper paginationMapper;
    private UserLoggedInUtil userLoggedInUtil;

    public ResponseEntity<?> create(RequestDto requestDto) {
        Request request = requestMapper.toEntity(requestDto);
        User currentUser = userLoggedInUtil.currentUser();
        if (currentUser == null) {
            return ResponseEntity.ok(new Response("please login"));
        }
        Status status = statusService.getById(1L)
                .orElseThrow(() -> new ResourceNotFoundException(Status.class.getSimpleName(), 1L));
        request.setStatus(status);
        request.setRequester(userLoggedInUtil.currentUser());
        requestService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(requestMapper.toDto(request));
    }

    public ResponseEntity<?> updateRequestStatus(Long requestId, Long statusId) {
        User currentUser = userLoggedInUtil.currentUser();
        Request request = requestService.getById(requestId)
                .orElseThrow(() -> new ResourceNotFoundException(Request.class.getSimpleName(), requestId));
        Status status = statusService.getById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException(Status.class.getSimpleName(), statusId));
        request.setStatus(status);
        request.setUpdatedBy(currentUser.getName());
        requestService.save(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(status);
    }

    public ResponseEntity<RequestDto> getById(Long id) {
        Request request = requestService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Request.class.getSimpleName(), id));
        RequestDto requestDto = requestMapper.toDto(request);
        return ResponseEntity.ok(requestDto);
    }


    public ResponseEntity<?> getAll(Long statusId, Long userId, Integer pageNo, Integer pageSize) {
        Page<Request> requests = requestService.getByStatusAndRequester(statusId, userId, pageNo, pageSize);
        List<RequestDto> content = requestMapper.toDto(requests.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(requests));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> delete(Long id) {
        requestService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Request.class.getSimpleName(), id));
        try {
            requestService.deleteById(id);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Request.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
