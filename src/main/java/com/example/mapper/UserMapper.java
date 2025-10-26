package com.example.mapper;

import com.example.dto.UserRequestDTO;
import com.example.dto.UserResponseDTO;
import com.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // DTO → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "active", constant = "true")
    User toEntity(UserRequestDTO dto);

    // Entity → DTO
    UserResponseDTO toDto(User user);
}
