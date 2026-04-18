package com.jhonathan.loja3d.service;

import com.jhonathan.loja3d.domain.Product.Category;
import com.jhonathan.loja3d.dto.CategoryDto;
import com.jhonathan.loja3d.exception.ResourceNotFoundException;
import com.jhonathan.loja3d.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    //permite acesso ao banco de dados
    private CategoryRepository repository;

    //Contrutor
    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    //Lista todas as categorias do banco
    public List<Category> getAll(){
        return repository.findAll();
    }

    // Procura Categoria pelo Id
    public Category findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
    }

    // Cria a Categoria e a salva no banco
    public Category create(CategoryDto dto){
        Category newCategory = new Category();

        newCategory.setName(dto.getName());
        return repository.save(newCategory);
    }

    // Busca categoria pelo Id e a salva no banco
    public Category updateById(Long id, CategoryDto dto){
        Category category = findById(id); // Busca o Id

        category.setName(dto.getName()); // Pega o resultado da busca e altera o nome

        return repository.save(category); // Salva no banco
    }

    // Busca a Categoria pelo Id e a deleta
    public void deleteById(Long id){
        Category deleted = findById(id);
        repository.delete(deleted);
    }


}
