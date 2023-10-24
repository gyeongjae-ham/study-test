package sample.cafekiosk.unit.beverages;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmericanoTest {

	@DisplayName("이름이 잘 출력된다")
	@Test
	void getName() {
		Americano americano = new Americano();
		assertThat(americano.getName()).isEqualTo("아메리카노");
	}

	@DisplayName("가격이 정확히 출력된다")
	@Test
	void getPrice() {
		Americano americano = new Americano();
		assertThat(americano.getPrice()).isEqualTo(4000);
	}

}
