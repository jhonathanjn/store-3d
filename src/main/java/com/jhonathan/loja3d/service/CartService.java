package com.jhonathan.loja3d.service;

import com.jhonathan.loja3d.domain.Auth.User;
import com.jhonathan.loja3d.domain.Product.Product;
import com.jhonathan.loja3d.domain.Sale.Cart;
import com.jhonathan.loja3d.domain.Sale.CartItem;
import com.jhonathan.loja3d.exception.ResourceNotFoundException;
import com.jhonathan.loja3d.repository.CartRepository;
import com.jhonathan.loja3d.repository.ProductRepository;
import com.jhonathan.loja3d.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public Cart getOrCreateCart(){
        User user = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);

                    return cartRepository.save(cart);
                });

    }

    public Cart addProduct(Long productId, Integer quantity){

        if(quantity <= 0){
            throw new RuntimeException("Quantity must be grater  than zero ");
        }

        Cart cart = getOrCreateCart();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found!"));

        for(CartItem item : cart.getItems()){
            if (item.getProduct().getId().equals(productId)){
                item.setQuantity(item.getQuantity() + quantity);
                return cartRepository.save(cart);
            }
        }

        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setProduct(product);
        newItem.setQuantity(quantity);

        cart.getItems().add(newItem);

        return cartRepository.save(cart);
    }

    public Cart removeProduct(Long productId){

        Cart cart = getOrCreateCart();

        cart.getItems().removeIf(item ->
                item.getProduct().getId().equals(productId));

        return cartRepository.save(cart);

    }

    public Cart updateQuantity(Long productId, Integer quantity){

        if(quantity <= 0){
            throw new RuntimeException("Quantity must be grater  than zero ");
        }

        Cart cart = getOrCreateCart();

        for (CartItem item : cart.getItems()){
            if (item.getProduct().getId().equals(productId)){
                item.setQuantity(quantity);
                return cartRepository.save(cart);
            }
        }

        throw new RuntimeException("Product not found in cart");
    }

}
