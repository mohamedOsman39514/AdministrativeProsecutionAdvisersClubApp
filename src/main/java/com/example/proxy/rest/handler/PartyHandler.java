package com.example.proxy.rest.handler;

import com.example.proxy.entity.Party;
import com.example.proxy.entity.Request;
import com.example.proxy.rest.dto.PartyDto;
import com.example.proxy.rest.dto.common.PaginationReultDto;
import com.example.proxy.rest.entitymapper.common.PaginationMapper;
import com.example.proxy.rest.entitymapper.PartyMapper;
import com.example.proxy.rest.exception.ErrorCodes;
import com.example.proxy.rest.exception.ResourceNotFoundException;
import com.example.proxy.rest.exception.ResourceRelatedException;
import com.example.proxy.rest.exception.Response;
import com.example.proxy.service.PartyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PartyHandler {

    private PartyService partyService;
    private PartyMapper partyMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> save(PartyDto partyDto) {
        Party party = partyMapper.toEntity(partyDto);
        partyService.save(party);
        return ResponseEntity.status(HttpStatus.CREATED).body(partyMapper.toDto(party));
    }

    public ResponseEntity<?> getById(Long id) {
        Party party = partyService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Party.class.getSimpleName(),id));
        PartyDto partyDto = partyMapper.toDto(party);
        return ResponseEntity.ok(partyDto);
    }

    public ResponseEntity<?> getAll(Integer pageNo, Integer pageSize){
        Page<Party> parties = partyService.getAllParties(pageNo, pageSize);
        List<PartyDto> content= partyMapper.toDto(parties.getContent());
        PaginationReultDto paginatedResult = new PaginationReultDto();
        paginatedResult.setData(content);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(parties));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> update(Long id, PartyDto partyDto) {
        partyService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Party.class.getSimpleName(), id));
        Party party = partyMapper.toEntity(partyDto);
        partyService.save(party);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteById(Long id) {
        partyService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Request.class.getSimpleName(),id));
        try {
            partyService.deleteById(id);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Request.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE.getCode());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Response("deleted"));
    }

}
