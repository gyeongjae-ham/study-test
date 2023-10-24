package sample.cafekiosk.unit.beverages;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmericanoTest {

	@DisplayName("아메리카노 인스턴스를 생성하고 이름을 확인하면 아메리카노가 나온다")
	@Test
	void getName() {
		Americano americano = new Americano();
		assertThat(americano.getName()).isEqualTo("아메리카노");
	}

	@DisplayName("아메리카노 인스턴스를 생성하고 가격을 확인하면 4000이 나온다")
	@Test
	void getPrice() {
		Americano americano = new Americano();
		assertThat(americano.getPrice()).isEqualTo(4000);
	}

}
