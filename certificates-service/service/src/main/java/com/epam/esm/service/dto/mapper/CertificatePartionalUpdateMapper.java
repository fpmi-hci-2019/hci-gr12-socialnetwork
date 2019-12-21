package com.epam.esm.service.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CertificatePartionalUpdateMapper extends CertificateMapper{
    CertificatePartionalUpdateMapper INSTANCE = Mappers.getMapper(CertificatePartionalUpdateMapper.class);
}
