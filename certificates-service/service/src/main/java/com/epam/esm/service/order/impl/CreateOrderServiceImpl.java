package com.epam.esm.service.order.impl;

import com.epam.esm.entity.CertificateSnapshot;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.repository.*;
import com.epam.esm.service.order.CreateOrderService;
import com.epam.esm.service.dto.OrderDto;
import com.epam.esm.service.dto.mapper.OrderMapper;
import com.epam.esm.service.exception.EntityNotFoundException;
import com.epam.esm.service.exception.NotEnoughMoneyException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class CreateOrderServiceImpl implements CreateOrderService {
    private UserRepository userRepository;
    private CertificatesRepository certificateRepository;
    private SnapshotRepository snapshotRepository;
    private OrderRepository orderRepository;

    @Transactional
    @Override
    public OrderDto orderCertificatesByUser(String username, List<Long> certificatesIds) {
        User user = userRepository.findUserByLogin(username);
        List<CertificateSnapshot> snapshots = certificatesIds.stream()
                .map(id -> certificateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("certificate.not.found")))
                .map(CertificateSnapshot::new)
                .collect(toList());
        snapshots.forEach(snapshot -> snapshot.setUserId(user.getId()));
        BigDecimal sum = snapshots.stream()
                                            .map(CertificateSnapshot::getPrice)
                                            .reduce(BigDecimal::add)
                                            .orElseThrow(() -> new EntityNotFoundException("price.not.calculated"));
        BigDecimal money = user.getMoney();
        if (money.compareTo(sum) < 0){
            throw new NotEnoughMoneyException("not.enough.money");
        }
        user.setMoney(money.subtract(sum));

        Order order = new Order();
        order.setUser(user);
        snapshots.forEach(snapshotRepository::create);
        order.setCertificates(snapshots);
        orderRepository.create(order);

        return OrderMapper.INSTANCE.toDto(order);
    }
}
