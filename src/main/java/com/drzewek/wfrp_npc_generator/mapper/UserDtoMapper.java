package com.drzewek.wfrp_npc_generator.mapper;

import com.drzewek.wfrp_npc_generator.model.UserDto;
import com.drzewek.wfrp_npc_generator.model.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto dto);
}
