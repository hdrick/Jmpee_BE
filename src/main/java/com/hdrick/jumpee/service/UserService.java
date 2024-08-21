package com.hdrick.jumpee.service;

import com.hdrick.jumpee.dto.AddressDTO;
import com.hdrick.jumpee.dto.UserCreationDTO;
import com.hdrick.jumpee.exception.ResourceNotFoundException;
import com.hdrick.jumpee.model.Address;
import com.hdrick.jumpee.model.Role;
import com.hdrick.jumpee.model.User;
import com.hdrick.jumpee.repository.RoleRepository;
import com.hdrick.jumpee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User createUser(UserCreationDTO userCreationDTO){
        User user = new User();
        user.setFirstName(userCreationDTO.getFirstName());
        user.setLastName(userCreationDTO.getLastName());
        user.setEmail(userCreationDTO.getEmail());

        String encodedPassword = bCryptPasswordEncoder.encode(userCreationDTO.getPassword());
        user.setPassword(encodedPassword);

        // Handle roles
        List<Role> roles = userCreationDTO.getNewRoles().stream()
                .map(roleDTO -> roleRepository.findById(roleDTO.getId())
                        .orElseThrow(() -> new RuntimeException("Role not found")))
                .collect(Collectors.toList());
        user.setRoles(roles);

        // Save user
        return userRepository.save(user);
    }

    public void addAddressToUser(Long id, AddressDTO addressDTO){

        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }

        User user = optionalUser.get();

        Address address = new Address();
        address.setUser(user);
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());

        user.getAddresses().add(address);

        userRepository.save(user);
    }

    public List<User> getAllUsersWithAddresses() {
        return userRepository.findAll(); // Assuming findAll fetches users with addresses due to eager loading or fetch type configuration
    }


}
