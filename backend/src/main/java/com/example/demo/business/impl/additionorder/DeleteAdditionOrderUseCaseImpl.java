package com.example.demo.business.impl.additionorder;

import com.example.demo.business.cases.additionorder.DeleteAdditionOrderUseCase;
import com.example.demo.persistence.repository.AdditionOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAdditionOrderUseCaseImpl implements DeleteAdditionOrderUseCase {
    private final AdditionOrderRepository additionOrderRepository;

    /**
     * @param additionOrderId
     * @return
     */
    @Override
    public int deleteAddition(int additionOrderId) {
        additionOrderRepository.deleteById((long) additionOrderId);
        return 0;
    }
}
