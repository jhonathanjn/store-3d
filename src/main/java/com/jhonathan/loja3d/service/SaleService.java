package com.jhonathan.loja3d.service;

import com.jhonathan.loja3d.domain.Auth.User;
import com.jhonathan.loja3d.domain.Product.Product;
import com.jhonathan.loja3d.domain.Sale.*;
import com.jhonathan.loja3d.exception.ResourceNotFoundException;
import com.jhonathan.loja3d.repository.CartRepository;
import com.jhonathan.loja3d.repository.ProductRepository;
import com.jhonathan.loja3d.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public Sale checkout(){

        // Pega o token do usuario logado
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        // Pega o carrinho ligado o id do usuario logado
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));

        // Verifica se o carrinho esta vazio
        if(cart.getItems().isEmpty()){
            throw new RuntimeException("Cart is Empty");
        }

        // Cria a Sale/Venda ligando ao usuario e o status dela
        Sale sale = new Sale();
        sale.setUser(user);
        sale.setStatus(SaleStatus.PENDING);

        //Cria uma lista de produtos da venda
        List<SaleItem> saleItems = new ArrayList<>();

        //Define o valor total
        BigDecimal total = BigDecimal.ZERO;

        // Para cada item do carrinho do usuario se cria um cartItem
        for (CartItem cartItem : cart.getItems()){
            // Verifica a existencia do produto pelo ID
            Product product = productRepository.findById(cartItem.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setProduct(product);
            saleItem.setQuantity(cartItem.getQuantity());
            saleItem.setPrice(product.getPrice());

            BigDecimal itemTotal = product.getPrice()
                    .multiply(BigDecimal.valueOf(cartItem.getQuantity()));

            total = total.add(itemTotal);

            saleItems.add(saleItem);
        }

        sale.setSaleItens(saleItems);
        sale.setTotal(total);
        sale.setCreatedAt(LocalDateTime.now());

        Sale savedSale = saleRepository.save(sale);

        cart.getItems().clear();
        cartRepository.save(cart);

        return savedSale;
    }

}
