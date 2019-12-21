package com.epam.esm.controller;

import com.epam.esm.service.dto.*;
import com.epam.esm.service.order.CreateOrderService;
import com.epam.esm.service.order.OrderSearchService;
import com.epam.esm.service.snapshot.SearchSnapshotService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    private final OrderSearchService searchOrderService;
    private final SearchSnapshotService searchSnapshotService;
    private final CreateOrderService createOrderService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping
    public PaginationDto<OrderDto> ordersByUser(@Positive @RequestParam(required = false, defaultValue = "1") Integer page,
                                       @Positive @RequestParam(required = false, defaultValue = "5") Integer limit,
                                        Authentication authentication){
        PaginationInfoDto<OrderDto> paginationInfoDto =
                searchOrderService.userOrders(page, limit, authentication.getName());
        Integer pageCount = paginationInfoDto.getPageInfo().getPageCount();
        String previous = page == 1 ? null : "/orders?page=" + (page - 1) + "&limit=" + limit;
        String next = page.equals(pageCount == 0 ? 1 : pageCount) ? null : "/orders?page=" + (page + 1) + "&limit=" + limit;
        Links links = new Links("/orders?page=1&limit=" + limit,
                "/orders?page=" + (pageCount == 0 ? 1 : pageCount) + "&limit=" + limit,
                next, previous);
        PaginationDto<OrderDto> paginationDto = new PaginationDto<>();
        paginationDto.setLinks(links);
        paginationDto.setCollection(paginationInfoDto.getCollection());
        return paginationDto;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/certificates")
    public PaginationDto<SnapshotDto> snapshotsByUser(@Positive @RequestParam(required = false, defaultValue = "1") Integer page,
                                                      @Positive @RequestParam(required = false, defaultValue = "5") Integer limit,
                                                      Authentication authentication){
        PaginationInfoDto<SnapshotDto> paginationInfoDto =
                searchSnapshotService.findUserCertificates(page, limit, authentication.getName());
        Integer pageCount = paginationInfoDto.getPageInfo().getPageCount();
        String previous = page == 1 ? null : "/orders/certificates?page=" + (page - 1) + "&limit=" + limit;
        String next = page.equals(pageCount == 0 ? 1 : pageCount) ? null : "/orders/certificates?page=" + (page + 1) + "&limit=" + limit;
        Links links = new Links("/orders/certificates?page=1&limit=" + limit,
                "/orders/certificates?page=" + (pageCount == 0 ? 1 : pageCount) + "&limit=" + limit,
                next, previous);
        PaginationDto<SnapshotDto> paginationDto = new PaginationDto<>();
        paginationDto.setCollection(paginationInfoDto.getCollection());
        paginationDto.setLinks(links);
        return paginationDto;
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{id}")
    public OrderDto findById(@Positive @PathVariable("id") Long id){
        return searchOrderService.findById(id);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> buyCertificates(@RequestBody List<Long> id, Authentication authentication){
        OrderDto created = createOrderService.orderCertificatesByUser(authentication.getName(), id);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uri).body(created);
    }

}
