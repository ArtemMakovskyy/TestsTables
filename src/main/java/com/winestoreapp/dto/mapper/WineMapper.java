package com.winestoreapp.dto.mapper;

import com.winestoreapp.config.MapperConfig;
import com.winestoreapp.dto.wine.WineCreateRequestDto;
import com.winestoreapp.dto.wine.WineWithoutReviewsAndPicturesDto;
import com.winestoreapp.model.Wine;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WineMapper {
    WineWithoutReviewsAndPicturesDto toDto(Wine wine);
    Wine toEntityFromCreate(WineCreateRequestDto dto);
}
