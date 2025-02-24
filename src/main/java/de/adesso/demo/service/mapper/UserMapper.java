package de.adesso.demo.service.mapper;

import de.adesso.demo.dto.UserDTO;
import de.adesso.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDto);
    List<UserDTO> usersToUserDTOList(List<User> users);
}