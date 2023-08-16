package com.hoanghiep.microservice.productservice.command.interceptor;

import com.hoanghiep.microservice.productservice.command.CreateProductCommand;
import com.hoanghiep.microservice.productservice.core.data.ProductLookupEntity;
import com.hoanghiep.microservice.productservice.core.data.ProductLookupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> list) {
        return (index, command) -> {
            log.info("Intercepted command: " + command.getPayloadType());
            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                // validate create product Command

                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
                if (createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
                    throw new IllegalArgumentException("Price cannot be less or equal than zero");
                }

                if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()) {
                    throw new IllegalArgumentException("Title cannot be empty");
                }

                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(createProductCommand.getProductId(), createProductCommand.getTitle());
                if (productLookupEntity != null) {
                    throw new IllegalStateException(String.format("Product with productId %s or title %s already exist", createProductCommand.getProductId(), createProductCommand.getTitle()));
                }
            }

            return command;
        };
    }
}
