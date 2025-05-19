package bg.sofia.uni.fmi.javaweb.sports_marketplace.controllers;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address.AddressDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController{
    private AddressRepository addressRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository){
        this.addressRepository=addressRepository;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses(){
        return ResponseEntity.ok(addressRepository.findAll().stream().map(AddressDto::fromEntity).toList());
    }
}
