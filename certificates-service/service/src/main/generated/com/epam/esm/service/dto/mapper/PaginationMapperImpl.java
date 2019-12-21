package com.epam.esm.service.dto.mapper;

import com.epam.esm.entity.CertificateSnapshot;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.service.dto.SnapshotDto;
import com.epam.esm.service.dto.TagDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-12-20T23:30:59+0300",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class PaginationMapperImpl implements PaginationMapper {

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto orderDto = new OrderDto();

        orderDto.setId( order.getId() );
        orderDto.setTimestamp( order.getTimestamp() );
        orderDto.setCertificates( certificateSnapshotListToSnapshotDtoList( order.getCertificates() ) );

        return orderDto;
    }

    @Override
    public Order toEntity(OrderDto orderDto) {
        if ( orderDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDto.getId() );
        order.setTimestamp( orderDto.getTimestamp() );
        order.setCertificates( snapshotDtoListToCertificateSnapshotList( orderDto.getCertificates() ) );

        return order;
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

    protected SnapshotDto certificateSnapshotToSnapshotDto(CertificateSnapshot certificateSnapshot) {
        if ( certificateSnapshot == null ) {
            return null;
        }

        SnapshotDto snapshotDto = new SnapshotDto();

        snapshotDto.setId( certificateSnapshot.getId() );
        snapshotDto.setName( certificateSnapshot.getName() );
        snapshotDto.setDescription( certificateSnapshot.getDescription() );
        snapshotDto.setPrice( certificateSnapshot.getPrice() );
        snapshotDto.setCreationDate( certificateSnapshot.getCreationDate() );
        if ( certificateSnapshot.getDuration() != null ) {
            snapshotDto.setDuration( certificateSnapshot.getDuration().shortValue() );
        }
        snapshotDto.setUserId( certificateSnapshot.getUserId() );
        snapshotDto.setTags( tagSetToTagDtoSet( certificateSnapshot.getTags() ) );

        return snapshotDto;
    }

    protected List<SnapshotDto> certificateSnapshotListToSnapshotDtoList(List<CertificateSnapshot> list) {
        if ( list == null ) {
            return null;
        }

        List<SnapshotDto> list1 = new ArrayList<SnapshotDto>( list.size() );
        for ( CertificateSnapshot certificateSnapshot : list ) {
            list1.add( certificateSnapshotToSnapshotDto( certificateSnapshot ) );
        }

        return list1;
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

    protected CertificateSnapshot snapshotDtoToCertificateSnapshot(SnapshotDto snapshotDto) {
        if ( snapshotDto == null ) {
            return null;
        }

        CertificateSnapshot certificateSnapshot = new CertificateSnapshot();

        certificateSnapshot.setId( snapshotDto.getId() );
        certificateSnapshot.setName( snapshotDto.getName() );
        certificateSnapshot.setDescription( snapshotDto.getDescription() );
        certificateSnapshot.setPrice( snapshotDto.getPrice() );
        certificateSnapshot.setCreationDate( snapshotDto.getCreationDate() );
        if ( snapshotDto.getDuration() != null ) {
            certificateSnapshot.setDuration( snapshotDto.getDuration().intValue() );
        }
        certificateSnapshot.setTags( tagDtoSetToTagSet( snapshotDto.getTags() ) );
        certificateSnapshot.setUserId( snapshotDto.getUserId() );

        return certificateSnapshot;
    }

    protected List<CertificateSnapshot> snapshotDtoListToCertificateSnapshotList(List<SnapshotDto> list) {
        if ( list == null ) {
            return null;
        }

        List<CertificateSnapshot> list1 = new ArrayList<CertificateSnapshot>( list.size() );
        for ( SnapshotDto snapshotDto : list ) {
            list1.add( snapshotDtoToCertificateSnapshot( snapshotDto ) );
        }

        return list1;
    }
}
