package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Product item1 = new Book(1, "Fight Club", 100, "Chuck Palahniuk");
    private Product item2 = new Book(2, "Requiem For A Dream", 200, "Hubert Selby Jr.");
    private Product item3 = new TShirt(3, "White", 500, "Nike");
    private Product item4 = new TShirt(4, "Black", 1000, "New Balance");

    @BeforeEach
    void setUp() {
        repository.save(item1);
        repository.save(item2);
        repository.save(item3);
        repository.save(item4);
    }

    @Test
    void shouldRemoveById() {
        repository.removeById(1);
        Product[] actual = repository.findAll();
        Product[] expected = {
                item2,
                item3,
                item4
        };
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotRemoveById() {
        assertThrows(NotFoundException.class, () -> repository.removeById(42));
    }
}