package com.epam.esm.service.dto.mapper;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.dto.CertificateDto;
import com.epam.esm.service.dto.TagDto;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-20T23:30:58+0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class CertificatePartionalUpdateMapperImpl implements CertificatePartionalUpdateMapper {

    @Override
    public CertificateDto toDto(GiftCertificate certificate) {
        if ( certificate == null ) {
            return null;
        }

        CertificateDto certificateDto = new CertificateDto();

        certificateDto.setId( certificate.getId() );
        certificateDto.setName( certificate.getName() );
        certificateDto.setDescription( certificate.getDescription() );
        certificateDto.setPrice( certificate.getPrice() );
        certificateDto.setCreationDate( certificate.getCreationDate() );
        certificateDto.setModificationDate( certificate.getModificationDate() );
        certificateDto.setDuration( certificate.getDuration() );
        certificateDto.setTags( tagSetToTagDtoSet( certificate.getTags() ) );

        return certificateDto;
    }

    @Override
    public GiftCertificate toEntity(CertificateDto certificateDto) {
        if ( certificateDto == null ) {
            return null;
        }

        GiftCertificate giftCertificate = new GiftCertificate();

        giftCertificate.setId( certificateDto.getId() );
        giftCertificate.setName( certificateDto.getName() );
        giftCertificate.setDescription( certificateDto.getDescription() );
        giftCertificate.setPrice( certificateDto.getPrice() );
        giftCertificate.setCreationDate( certificateDto.getCreationDate() );
        giftCertificate.setModificationDate( certificateDto.getModificationDate() );
        giftCertificate.setDuration( certificateDto.getDuration() );
        giftCertificate.setTags( tagDtoSetToTagSet( certificateDto.getTags() ) );

        return giftCertificate;
    }

    @Override
    public void updateCertificateFromDto(CertificateDto certificateDto, GiftCertificate certificate) {
        if ( certificateDto == null ) {
            return;
        }

        if ( certificateDto.getId() != null ) {
            certificate.setId( certificateDto.getId() );
        }
        if ( certificateDto.getName() != null ) {
            certificate.setName( certificateDto.getName() );
        }
        if ( certificateDto.getDescription() != null ) {
            certificate.setDescription( certificateDto.getDescription() );
        }
        if ( certificateDto.getPrice() != null ) {
            certificate.setPrice( certificateDto.getPrice() );
        }
        if ( certificateDto.getCreationDate() != null ) {
            certificate.setCreationDate( certificateDto.getCreationDate() );
        }
        if ( certificateDto.getModificationDate() != null ) {
            certificate.setModificationDate( certificateDto.getModificationDate() );
        }
        if ( certificateDto.getDuration() != null ) {
            certificate.setDuration( certificateDto.getDuration() );
        }
        if ( certificate.getTags() != null ) {
            Set<Tag> set = tagDtoSetToTagSet( certificateDto.getTags() );
            if ( set != null ) {
                certificate.getTags().clear();
                certificate.getTags().addAll( set );
            }
        }
        else {
            Set<Tag> set = tagDtoSetToTagSet( certificateDto.getTags() );
            if ( set != null ) {
                certificate.setTags( set );
            }
        }
    }

    protected TagDto tagToTagDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setId( tag.getId() );
        tagDto.setName( tag.getName() );

        return tagDto;
    }

    protected Set<TagDto> tagSetToTagDtoSet(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        Set<TagDto> set1 = new HashSet<TagDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Tag tag : set ) {
            set1.add( tagToTagDto( tag ) );
        }

        return set1;
    }

    protected Tag tagDtoToTag(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( tagDto.getId() );
        tag.setName( tagDto.getName() );

        return tag;
    }

    protected Set<Tag> tagDtoSetToTagSet(Set<TagDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Tag> set1 = new HashSet<Tag>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( TagDto tagDto : set ) {
            set1.add( tagDtoToTag( tagDto ) );
        }

        return set1;
    }
}
