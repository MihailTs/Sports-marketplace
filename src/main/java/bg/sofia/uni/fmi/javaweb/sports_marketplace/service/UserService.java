package bg.sofia.uni.fmi.javaweb.sports_marketplace.service;

import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address.AddressCreateDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.address.AddressDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.dto.user.UserDto;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.EmailAlreadyExistsException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.PasswordMismatchException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.UserDoesntExistException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.exceptions.WrongEmailOrPasswordException;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.jwt_util.JWTUtil;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Address;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.Role;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.models.User;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.AddressRepository;
import bg.sofia.uni.fmi.javaweb.sports_marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleResult;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AddressRepository addressRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder, AddressRepository addressRepository){
        this.userRepository=userRepository;
        this.encoder=encoder;
        this.addressRepository=addressRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public User login(String email, String password){
        Optional<User> user=userRepository.findByEmail(email);
        if(user.isEmpty()||!encoder.matches(password, user.get().getPassword())){
            throw new WrongEmailOrPasswordException();
        }
        return user.get();
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())));
    }

    public User register(String name, String email, String password, String confirmPassword, Role role, String gender, String phoneNumber, AddressCreateDto addressDto, Date birthDate){
        if(userRepository.findByEmail(email).isPresent()){
            throw new EmailAlreadyExistsException(email);
        }
        else if(!password.equals(confirmPassword)){
            throw new PasswordMismatchException();
        }
        User user = new User(email, name, encoder.encode(password), role, gender, phoneNumber, addressDto!=null?AddressCreateDto.toEntity(addressDto):null, birthDate);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public void deleteUser(Optional<User> user){
        if(user.isEmpty()){
            throw new UserDoesntExistException();
        }
        if(user.get().getAddress()!=null&&userRepository.countByAddress(user.get().getAddress())==1){
            addressRepository.delete(user.get().getAddress());
        }
        userRepository.delete(user.get());
    }

    public User updateUser(Optional<User> user, UserDto userDto){
        if(user.isEmpty()){
            throw new UserDoesntExistException();
        }
        User userToChange=user.get();
        if(userDto.address()!=null){
            updateAddress(userToChange, userDto.address());
        }
        if(userDto.name()!=null){
            userToChange.setName(userDto.name());
        }
        if(userDto.phoneNumber()!=null){
            userToChange.setPhoneNumber(userDto.phoneNumber());
        }
        if(userDto.gender()!=null){
            userToChange.setGender(userDto.gender());
        }
        userToChange.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(userToChange);
    }

    private void updateAddress(User user, AddressDto addressDto) {
        Address oldAddress = user.getAddress();


        Optional<Address> newAddress = addressRepository.findByCityAndCountryAndStateAndStreetAndZipCode(addressDto.city(), addressDto.country(), addressDto.state(), addressDto.street(), addressDto.zipCode());

        if (newAddress.isPresent()) {
            if (oldAddress != null && userRepository.countByAddress(oldAddress) == 1) {
                addressRepository.delete(oldAddress);
            }
            user.setAddress(newAddress.get());
            return;
        }

        if (oldAddress != null && userRepository.countByAddress(oldAddress) == 1) {
            if (addressDto.street() != null) {
                oldAddress.setStreet(addressDto.street());
            }
            if (addressDto.country() != null) {
                oldAddress.setCountry(addressDto.country());
            }
            if (addressDto.state() != null) {
                oldAddress.setState(addressDto.state());
            }
            if (addressDto.zipCode() != null) {
                oldAddress.setZipCode(addressDto.zipCode());
            }
            if (addressDto.city() != null) {
                oldAddress.setCity(addressDto.city());
            }
            addressRepository.save(oldAddress);
        } else {
            Address nextAddress = AddressDto.toEntity(addressDto);
            user.setAddress(nextAddress);
            addressRepository.save(nextAddress);
        }
    }


}
