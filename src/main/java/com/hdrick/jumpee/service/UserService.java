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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User createUser(UserCreationDTO userCreationDTO){
        User user = new User();
        user.setFirstName(userCreationDTO.getFirstName());
        user.setLastName(userCreationDTO.getLastName());

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
        Address address = new Address();
        // Find the user by ID
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }

        User user = optionalUser.get();

        address.setUser_id(user.getId());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setZipCode(addressDTO.getZipCode());

        user.getAddresses().add(address);

        userRepository.save(user);
    }


}
