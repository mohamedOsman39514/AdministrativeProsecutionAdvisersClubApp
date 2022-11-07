package com.example.proxy.service;

import com.example.proxy.entity.Party;
import com.example.proxy.repository.PartyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PartyService {

    private PartyRepository partyRepository;

    public Optional<Party> getById(Long id) {
        return partyRepository.findById(id);
    }
    
    public Page<Party> getAllParties(Integer page, Integer size) {
        return partyRepository.findAll(PageRequest.of(page, size));
    }

    public Party save(Party party) {
        return partyRepository.save(party);
    }

    public void deleteById(Long id) {
        partyRepository.deleteById(id);
    }

}
