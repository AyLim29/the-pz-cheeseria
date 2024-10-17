package com.cheeseria.backend.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cheeseria.backend.model.Cheese;

@DataJpaTest
class CheeseRepositoryTest {

	@Autowired
	private CheeseRepository cheeseRepository;

	@Test
	public void testSaveCheese() {
		Cheese newCheese = Cheese.builder().id(6L).name("Fake Cheese").colour("Yellow")
		.pricePerKilo(10000.0).imageURL("https://upload.wikimedia.org/wikipedia/commons/1/1c/Single_wrapped_slice_of_processed_cheese.jpg").build();
		cheeseRepository.save(newCheese);
		Optional<Cheese> savedCheese = cheeseRepository.findById(6L);
		Assertions.assertThat(savedCheese).isPresent();
		Assertions.assertThat(savedCheese.get().getName()).isEqualTo("Fake Cheese");
	}

	@Test 
	public void testFindById() {
		Optional<Cheese> cheese = cheeseRepository.findById(1L);
		Assertions.assertThat(cheese).isPresent();
		Assertions.assertThat(cheese.get().getName()).isEqualTo("Cabrales");
	}

	@Test 
	public void testFindAll() {
		List<Cheese> cheeses = cheeseRepository.findAll();
		Assertions.assertThat(cheeses).isNotEmpty();
		Assertions.assertThat(cheeses).hasSize(5);
		Assertions.assertThat(cheeses.get(0).getName()).isEqualTo("Cabrales");
	}

	@Test
    public void testDelete() {
		Optional<Cheese> cheeseOptional = cheeseRepository.findById(1L);
		Assertions.assertThat(cheeseOptional).isPresent();
		cheeseRepository.deleteById(1L);
        cheeseOptional = cheeseRepository.findById(1L);
        Assertions.assertThat(cheeseOptional).isNotPresent();
    }

}