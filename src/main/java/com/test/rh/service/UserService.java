package com.test.rh.service;

import com.test.rh.domain.User;
import com.test.rh.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.nonNull;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    public UserRepository repository;


    public User getUser(Long id) throws NotFoundException {

        try {
            return repository.getOne(id);
        } catch (Exception e){
            throw new NotFoundException("O usuário não foi encontrado!");
        }
    }

    public List<User> findAllUser(){

        return repository.findAll();
    }

    public void updateUser(User user){

        User usrDb = repository.getOne(user.getId());

        if(nonNull(user.getName()) && !user.getName().isEmpty() &&
                !usrDb.getName().equals(user.getName())){
            usrDb.setName(user.getName());
        }

        if(nonNull(user.getGender()) && !user.getGender().isEmpty() &&
                !usrDb.getGender().equals(user.getGender())){
            usrDb.setGender(user.getGender());
        }

        if(nonNull(user.getEmail()) && !user.getEmail().isEmpty()
                && !usrDb.getEmail().equals(user.getEmail())) {
            usrDb.setEmail(user.getEmail());
        }

        if(nonNull(user.getDtBirth()) && !usrDb.getDtBirth().equals(user.getDtBirth())){
            usrDb.setDtBirth(user.getDtBirth());
        }

        if(nonNull(user.getNaturalness()) && !user.getNaturalness().isEmpty() &&
                !usrDb.getNaturalness().equals(user.getNaturalness())){
            usrDb.setNaturalness(user.getNaturalness());
        }

        if(nonNull(user.getNationality()) && !user.getNationality().isEmpty() &&
                !usrDb.getNationality().equals(user.getNationality())){
            usrDb.setNationality(user.getNationality());
        }

        if(!usrDb.getCpf().equals(user.getCpf())){
            usrDb.setCpf(user.getCpf());
        }
        usrDb.setDtUpdate(LocalDateTime.now());
        repository.save(usrDb);
    }

    public void deleteUser(Long id){

        repository.delete(repository.getOne(id));
    }

    public void registerUser(User user){

        String validaEmail = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+"
                + "(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern p = Pattern.compile(validaEmail);
        Matcher userEmail = p.matcher(user.getEmail());


        if((Objects.isNull(user.getDtBirth()) || user.getDtBirth().isEmpty())
                && userEmail.find()){
            repository.save(user);
            return;
        }

        try {
            LocalDate.parse(user.getDtBirth(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            repository.save(user);
            return;
        }catch (DateTimeParseException e){
            e.printStackTrace();
        }
    }

    public UserDetails loadUserByUsername(String name){

        User usr = repository.findByName(name)
                                .orElse(new User());
        return new org.springframework.security.core.userdetails.User(usr.getName(),
                usr.getCpf(), AuthorityUtils.commaSeparatedStringToAuthorityList(usr.getName())
        );

    }


    public String login(String dto) throws LoginException {

        /*LoginResponseDto dto;

        User user = dynamoService.findUser(requestDto.getUsername());

        if(user == null)
            throw new LoginException(messageSource.getMessage("login.error.not.found",
                    null, Locale.getDefault()));

        if(!(new BCryptPasswordEncoder()).matches(requestDto.getPassword(), user.getPassword()))
            throw new LoginException(messageSource.getMessage("login.error.wrong.password",
                    null, Locale.getDefault()));


        final String token = jwtTokenUtil.generateToken(requestDto.getUsername());

        dto = new LoginResponseDto(user.getUsername(), token, user.getRoles().stream()
                .map(Role::getName).collect(Collectors.toList()));*/

        return dto;
    }
}
