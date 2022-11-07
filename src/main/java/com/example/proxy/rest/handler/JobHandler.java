package com.example.proxy.rest.handler;

import com.example.proxy.entity.Job;
import com.example.proxy.entity.Request;
import com.example.proxy.rest.dto.JobDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.entitymapper.JobMapper;
import com.example.proxy.rest.exception.ErrorCodes;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.rest.exception.ResourceRelatedException;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class JobHandler {

    private JobService jobService;
    private JobMapper jobMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(JobDto jobDto) {
        Job job = jobMapper.toEntity(jobDto);
        jobService.save(job);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobMapper.toDto(job));
    }

    public ResponseEntity<?> getById(Long id) {
        Job job = jobService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Job.class.getSimpleName(),id));
        JobDto jobDto = jobMapper.toDto(job);
        return ResponseEntity.ok(jobDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize) {
        Page<Job> jobs = jobService.getAll(pageNo, pageSize);
        List<JobDto> content = jobMapper.toDto(jobs.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(jobs));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> update(Long id, JobDto jobDto) {
         jobService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Job.class.getSimpleName(), id));
        Job job = jobMapper.toEntity(jobDto);
        jobService.save(job);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteById(Long id) {
        jobService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Request.class.getSimpleName(),id));
        try {
            jobService.deleteById(id);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Request.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
