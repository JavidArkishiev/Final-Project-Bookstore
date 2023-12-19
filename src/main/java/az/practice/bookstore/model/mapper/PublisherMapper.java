package az.practice.bookstore.model.mapper;

import az.practice.bookstore.model.dto.PublisherDto;
import az.practice.bookstore.model.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface PublisherMapper {

    Publisher mapToEntity(PublisherDto publisherDto);

    PublisherDto mapToPublisherDto(Publisher publishers);
}
