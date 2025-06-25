package com.example.demo.mapper;


import com.example.demo.controller.dto.CreateManagerDto;
import com.example.demo.controller.dto.CreateUserDto;
import com.example.demo.domain.User;
import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.enums.RoleName;
import com.example.demo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User fromDto(CreateUserDto createUserDto) {
        User user = new User();
        user.setIme(createUserDto.ime());
        user.setPrezime(createUserDto.prezime());
        user.setUsername(createUserDto.username());
        user.setPassword(passwordEncoder.encode(createUserDto.password()));
        user.setEmail(createUserDto.email());
        user.setRodjendan(createUserDto.rodjendan());
        user.setActive(true);
        user.setBrRez(0);
        user.setRole(roleRepository.findByName(RoleName.USER));
        return user;
    }

    public UserDto toDto(User user) {
        return new UserDto(user.getId(),user.getUsername(),user.getPassword(),
                user.getIme(),user.getPrezime(),user.getEmail(),user.getBrRez(),
                user.getRodjendan(),user.getNazivRestorana(),user.getDatumZaposljavanja(),
                user.getActive());
    }
    public User managerFromDto(CreateManagerDto createManagerDto) {
        User user = new User();
        user.setIme(createManagerDto.ime());
        user.setPrezime(createManagerDto.prezime());
        user.setUsername(createManagerDto.username());
        user.setPassword(passwordEncoder.encode(createManagerDto.password()));
        user.setNazivRestorana(createManagerDto.nazivRestorana());
        user.setDatumZaposljavanja(createManagerDto.datumZaposljavanja());
        user.setEmail(createManagerDto.email());
        user.setRodjendan(createManagerDto.rodjendan());
        user.setActive(true);
        user.setBrRez(0);
        user.setRole(roleRepository.findByName(RoleName.MANAGER));
        return user;
    }
}
