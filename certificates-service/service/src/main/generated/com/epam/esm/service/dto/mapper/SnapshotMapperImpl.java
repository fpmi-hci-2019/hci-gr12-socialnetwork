package com.epam.esm.service.dto.mapper;

import com.epam.esm.entity.CertificateSnapshot;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.dto.SnapshotDto;
import com.epam.esm.service.dto.TagDto;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-20T23:30:58+0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class SnapshotMapperImpl implements SnapshotMapper {

    @Override
    public SnapshotDto toDto(CertificateSnapshot order) {
        if ( order == null ) {
            return null;
        }

        SnapshotDto snapshotDto = new SnapshotDto();

        snapshotDto.setId( order.getId() );
        snapshotDto.setName( order.getName() );
        snapshotDto.setDescription( order.getDescription() );
        snapshotDto.setPrice( order.getPrice() );
        snapshotDto.setCreationDate( order.getCreationDate() );
        if ( order.getDuration() != null ) {
            snapshotDto.setDuration( order.getDuration().shortValue() );
        }
        snapshotDto.setUserId( order.getUserId() );
        snapshotDto.setTags( tagSetToTagDtoSet( order.getTags() ) );

        return snapshotDto;
    }

    @Override
    public CertificateSnapshot toEntity(SnapshotDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        CertificateSnapshot certificateSnapshot = new CertificateSnapshot();

        certificateSnapshot.setId( orderDto.getId() );
        certificateSnapshot.setName( orderDto.getName() );
        certificateSnapshot.setDescription( orderDto.getDescription() );
        certificateSnapshot.setPrice( orderDto.getPrice() );
        certificateSnapshot.setCreationDate( orderDto.getCreationDate() );
        if ( orderDto.getDuration() != null ) {
            certificateSnapshot.setDuration( orderDto.getDuration().intValue() );
        }
        certificateSnapshot.setTags( tagDtoSetToTagSet( orderDto.getTags() ) );
        certificateSnapshot.setUserId( orderDto.getUserId() );

        return certificateSnapshot;
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
